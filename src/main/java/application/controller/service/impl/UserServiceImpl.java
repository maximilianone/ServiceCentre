package application.controller.service.impl;

import application.controller.service.abstraction.UserService;
import application.model.dao.abstraction.DAO;
import application.model.dao.abstraction.UserDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.User;

import java.sql.Connection;

public class UserServiceImpl implements UserService{
    private DAO<Boolean, User> userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO=userDAO;
    }

    @Override
    public Boolean add(User user){
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        boolean isAdded = userDAO.create(user);
        TransactionManager.commit();
        return isAdded;
    }
}
