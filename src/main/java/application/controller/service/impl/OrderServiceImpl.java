package application.controller.service.impl;

import application.controller.service.abstraction.OrderService;
import application.model.dao.abstraction.DAO;
import application.model.dao.abstraction.OrderDAO;
import application.model.dao.impl.OrderDAOImpl;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.Order;
import application.model.exception.ModelException;

import java.sql.Connection;

public class OrderServiceImpl implements OrderService {
    private DAO<Boolean, Order> orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Boolean add(Order order) throws ModelException {
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        boolean isAdded = orderDAO.create(order);
        TransactionManager.commit();
        return isAdded;
    }

}
