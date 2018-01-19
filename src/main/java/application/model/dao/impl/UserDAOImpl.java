package application.model.dao.impl;

import application.controller.mapper.Mapper;
import application.model.dao.abstraction.UserDAO;
import application.model.entity.User;
import application.model.exception.ModelException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
    private static Logger logger = LogManager.getLogger(UserDAOImpl.class.getName());

    private static final String CREATE = "INSERT INTO USERS(first_name,last_name,login, user_password, email, phone) " +
            "VALUES(?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL = "SELECT * FROM USERS";

    private static final String AUTHORIZATION = "SELECT * FROM USERS WHERE login = ? and user_password = ?";

    private static final String GET_BY_ID = "SELECT * FROM USERS WHERE user_id= ?";

    private static final String CHECK_PASSWORD = "Select * From users where user_id=? and user_password = ?";

    private Mapper<User, ResultSet> mapper;

    public UserDAOImpl(Mapper<User, ResultSet> mapper) {
        this.mapper = mapper;
    }

    public UserDAOImpl() {

    }

    @Override
    public Integer create(User user) {
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, user.getFirstName());
            parameterMap.put(2, user.getLastName());
            parameterMap.put(3, user.getLogin());
            parameterMap.put(4, user.getPassword());
            parameterMap.put(5, user.getEmail());
            parameterMap.put(6, user.getPhone());
            int newID = DAOTemplate.executeInsert(CREATE, parameterMap);
            logger.info("New user added: " + user);
            return newID;
        } catch (ModelException e) {
            logger.error(ADD_USER_ERROR);
            throw new ModelException(ADD_USER_ERROR);
        } catch (MySQLIntegrityConstraintViolationException e) {
            logger.error(LOGIN_EXIST_ERROR);
            throw new ModelException(LOGIN_EXIST_ERROR);
        }

    }

    @Override
    public List<User> getAll() {
        try {
            List<User> userList = DAOTemplate.selectAll(mapper, SELECT_ALL);
            logger.info("All users were shown");
            return userList;
        } catch (ModelException e) {
            logger.error(USER_SELECT_ERROR);
            throw new ModelException(USER_SELECT_ERROR);
        }
    }

    @Override
    public Boolean update(int userID, Object newValue, String fieldName) {
        try {
            boolean updateStatus = DAOTemplate.executeUpdate(createUpdateQuery(fieldName), userID, newValue);
            logger.info("User with id " + userID + " updated");
            return updateStatus;
        } catch (ModelException e) {
            logger.error(USER_UPDATE_ERROR);
            throw new ModelException(USER_UPDATE_ERROR);
        }
    }

    private String createUpdateQuery(String fieldName) {
        return "Update USERS SET " + fieldName + " = ? WHERE user_id = ?";
    }

    @Override
    public User authorization(String login, String password) {
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, login);
            parameterMap.put(2, password);
            User user = DAOTemplate.selectOne(AUTHORIZATION, parameterMap, mapper);
            logger.info("user " + user.getLogin() + " authorized");
            return user;
        } catch (ModelException e) {
            logger.info(FAILED_AUTHORIZATION);
            throw new ModelException(WRONG_AUTHORIZATION_INFO);
        }
    }

    @Override
    public User getById(int userID) {
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, userID);
            User user = DAOTemplate.selectOne(GET_BY_ID, parameterMap, mapper);
            logger.info("user information for id " + userID + "  was shown");
            return user;
        } catch (ModelException e) {
            logger.info(FAILED_AUTHORIZATION);
            throw new ModelException(WRONG_AUTHORIZATION_INFO);
        }
    }

    @Override
    public boolean checkPassword(int userID, String password){
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, userID);
            parameterMap.put(2, password);
            User user = DAOTemplate.selectOne(CHECK_PASSWORD, parameterMap, mapper);
            logger.info("user " + userID + " is changing password");
            return (user!=null);
        } catch (ModelException e) {
            logger.info(FAILED_ATTEMPT_CHANGE_PASSWORD);
            throw new ModelException(WRONG_PASSWORD);
        }
    }
}
