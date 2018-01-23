package application.model.dao.impl;

import application.controller.mapper.Mapper;
import application.model.dao.connection.abstraction.DBConnection;
import application.model.dao.transaction.TransactionManager;
import application.model.exception.ModelException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class DAOTemplate {

    /**
     * aet parameters to prepared statement
     * @param parameterMap parameters
     * @param preparedStatement prepared statement
     * @throws SQLException when failed to set parameters to statement
     */

    private static void setSQLParameters(Map<Integer, Object> parameterMap, PreparedStatement preparedStatement)
            throws SQLException {
        for (Integer i : parameterMap.keySet()) {
            preparedStatement.setObject(i, parameterMap.get(i));
        }
    }

    /**
     * execute insert
     *
     * @param query insert query
     * @param parameterMap parameters
     * @return genereted key of inserted entity
     * @throws com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException when fail to insert entity
     */

    static Integer executeInsert(String query, Map<Integer, Object> parameterMap)
            throws com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException {
        DBConnection connection = TransactionManager.getConnection();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setSQLParameters(parameterMap, preparedStatement);

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            throw new com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException();
        } catch (SQLException e) {
            throw new ModelException(e);
        }
    }

    /**
     * select all
     * @param mapper result mapper
     * @param query query
     * @param <T> entity to select
     * @return list of entities
     */

    static <T> List<T> selectAll(Mapper<T, ResultSet> mapper, String query) {
        return selectGroup(mapper, query, new HashMap<>());
    }

    /**
     * select group by parameters
     *
     * @param mapper result mapper
     * @param query query
     * @param parameterMap parameters
     * @param <T> entity to select
     * @return list of entities
     */

    static <T> List<T> selectGroup(Mapper<T, ResultSet> mapper, String query, Map<Integer, Object> parameterMap){
        DBConnection connection = TransactionManager.getConnection();
        List<T> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setSQLParameters(parameterMap, preparedStatement);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet != null) {
                while (resultSet.next()) {
                    T resultEntity = mapper.map(resultSet);
                    result.add(resultEntity);
                }
                resultSet.close();
            }

            return result;
        } catch (SQLException e) {
            throw new ModelException(e);
        }
    }

    /**
     * execute update
     * @param query query
     * @param id id of entity to update
     * @param newValue new value of updated field
     * @return status of update
     */

    static Boolean executeUpdate(String query, int id, Object newValue) {
        Map<Integer, Object> parameterMap = new HashMap<>();
        parameterMap.put(1, newValue);
        parameterMap.put(2, id);
        DBConnection connection = TransactionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setSQLParameters(parameterMap, preparedStatement);
            int updateCount = preparedStatement.executeUpdate();
            return (updateCount > 0);
        } catch (SQLException e) {
            throw new ModelException(e);
        }
    }

    /**
     * select one entity by parameters
     * @param query query
     * @param parameterMap parameters
     * @param mapper result mapper
     * @param <T> entity to select
     * @return selected entity
     */

    static <T> T selectOne(String query, Map<Integer, Object> parameterMap, Mapper<T, ResultSet> mapper){
        DBConnection connection = TransactionManager.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setSQLParameters(parameterMap, preparedStatement);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet != null) {
                    if(resultSet.next()) {
                        T result = mapper.map(resultSet);
                        resultSet.close();
                        return result;
                    }
            }throw new ModelException();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ModelException(e);
        }
    }
}
