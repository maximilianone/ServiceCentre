package application.controller.service.impl;

import application.controller.service.abstraction.OrderService;
import application.model.dao.abstraction.DAO;
import application.model.dao.abstraction.OrderDAO;
import application.model.dao.impl.OrderDAOImpl;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.FullOrder;
import application.model.entity.Order;
import application.model.exception.ModelException;

import java.sql.Connection;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Integer add(Order order) throws ModelException {
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        int orderID = orderDAO.create(order);
        TransactionManager.commit();
        return orderID;
    }

    @Override
    public List<FullOrder> getAll(){
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        List<FullOrder> orderList = orderDAO.getAll();
        TransactionManager.commit();
        return orderList;
    }

}
