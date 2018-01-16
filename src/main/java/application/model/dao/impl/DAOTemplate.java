package application.model.dao.impl;

import application.controller.mapper.Mapper;
import application.model.dao.connection.abstraction.DBConnection;
import application.model.dao.transaction.TransactionManager;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class DAOTemplate {
    private static void   prepareStatement(Map<Integer, Object> parameterMap, PreparedStatement preparedStatement)
    throws SQLException{
        for(Integer i: parameterMap.keySet()){
            preparedStatement.setObject(i, parameterMap.get(i));
        }
    }

    static Integer executeInsert(String query, Map<Integer, Object> parameterMap)
    throws com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException{
        DBConnection connection =TransactionManager.getConnection();
        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
            prepareStatement(parameterMap, preparedStatement);

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        }catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e) {
            throw new com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException();
        }catch (SQLException e){
            throw new ModelException(e);
        }
    }

    public static <T> List<T> selectAll(Mapper<T, ResultSet> mapper, String query){
        DBConnection connection = TransactionManager.getConnection();
        List<T> result = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet!=null){
                while (resultSet.next()){
                    T resultEntity = mapper.map(resultSet);
                    result.add(resultEntity);
                }
                resultSet.close();
            }

            return result;
        } catch (SQLException e) {
            throw new  ModelException(e);
        }
    }


}
