package application.model.dao.impl;

import application.model.dao.abstraction.UserDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.User;
import application.model.exception.ModelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private static Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

    private static final String CREATE = "INSERT INTO USERS(first_name,last_name,login, user_password, email, phone) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    @Override
    public Boolean create(User user) {
        try {
            PreparedStatement preparedStatement = TransactionManager.getInstance()
                    .getConnection()
                    .prepareStatement(CREATE);
            boolean isAdded = insert(preparedStatement, user);
            logger.info("New user added: " + user);
            return isAdded;
        } catch (ModelException e){
            logger.error(e.getMessage());
            throw new ModelException(e.getMessage());
        }
        catch (SQLException e) {
            logger.error(ADD_USER_ERROR);
            throw new ModelException(ADD_USER_ERROR);
        }
    }

    private boolean insert(PreparedStatement preparedStatement, User user){
        try {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPhone());
            int updateCount = preparedStatement.executeUpdate();
            return (updateCount > 0);
        }catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
            throw new ModelException(LOGIN_EXIST_ERROR
            );
        } catch (SQLException e) {
            throw new ModelException(ADD_USER_ERROR);
        }
    }
}
