package application.controller.service.impl;

import application.controller.service.abstraction.UserService;
import application.model.dao.abstraction.DAO;
import application.model.dao.abstraction.UserDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.User;

import java.sql.Connection;
import java.util.List;

public class UserServiceImpl implements UserService{
    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO=userDAO;
    }

    @Override
    public Integer add(User user){
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        int userID = userDAO.create(user);
        TransactionManager.commit();
        return userID;
    }

    @Override
    public List<User> getAll(){
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        List<User> userList = userDAO.getAll();
        TransactionManager.commit();
        return userList;
    }
}
