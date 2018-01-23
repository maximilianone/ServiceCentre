package application.controller.service.impl;

import application.controller.service.abstraction.UserService;
import application.model.dao.abstraction.DAO;
import application.model.dao.abstraction.UserDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.User;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;
import application.util.constants.ErrorMessages;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService, DBParameters {
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     *@inheritDoc
     */

    @Override
    public Integer add(User user) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            int userID = userDAO.create(user);
            TransactionManager.commit();
            return userID;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public List<User> getAll() {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            List<User> userList = userDAO.getAll();
            TransactionManager.commit();
            return userList;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public List<User> getBy(Object param, String name) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            List<User> userList = userDAO.getGroupBy(param, name);
            TransactionManager.commit();
            return userList;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public Boolean update(int userId, Object newValue, String fieldName) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            boolean updateStatus = userDAO.update(userId, newValue, fieldName);
            TransactionManager.commit();
            return updateStatus;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public User authorization(String login, String password) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            User authorizedUser = userDAO.authorization(login, password);
            TransactionManager.commit();
            return authorizedUser;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public User getById(int userID) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            User userInfo = userDAO.getById(userID);
            TransactionManager.commit();
            return userInfo;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public Boolean changeInfo(User user) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            userDAO.update(user.getId(), user.getFirstName(), DB_FIRST_NAME);
            userDAO.update(user.getId(), user.getLastName(), DB_LAST_NAME);
            userDAO.update(user.getId(), user.getPhone(), DB_PHONE);
            TransactionManager.commit();
            return true;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public Boolean changePassword(int userID, String oldPassword, String password) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            userDAO.checkPassword(userID, oldPassword);
            userDAO.update(userID, password, DB_PASSWORD);
            TransactionManager.commit();
            return true;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public void changeRole(int userID, String newRole, String oldRole){
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_SERIALIZABLE);
            userDAO.checkRole(userID, oldRole);
            userDAO.update(userID, newRole, DB_ROLE);
            TransactionManager.commit();
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }
}
