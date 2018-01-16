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

    private Mapper<User, ResultSet> mapper;

    public UserDAOImpl(Mapper<User, ResultSet> mapper){
        this.mapper = mapper;
    }

    public UserDAOImpl() {

    }

    @Override
    public Integer create(User user) {
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, user.getFirstName());
            parameterMap.put(2,user.getLastName());
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
    public List<User> getAll(){
        try {
            List<User> userList = DAOTemplate.selectAll(mapper, SELECT_ALL);
            logger.info("All users were shown");
            return userList;
        } catch (ModelException e){
            logger.error(USER_SELECT_ERROR);
            throw new ModelException(USER_SELECT_ERROR);
        }
    }
}
