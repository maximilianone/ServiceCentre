package application.controller.service.impl;

import application.controller.service.abstraction.OrderService;
import application.model.dao.abstraction.OrderDAO;
import application.model.dao.abstraction.ProductDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.dto.FullOrder;
import application.model.entity.Order;
import application.model.entity.Product;
import application.model.exception.ModelException;

import java.sql.Connection;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    public OrderServiceImpl(OrderDAO orderDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public Integer add(Product product, Order order) throws ModelException {
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        try {
            order.setProductID(productDAO.create(product));
            int orderID = orderDAO.create(order);
            TransactionManager.commit();
            return orderID;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    @Override
    public List<FullOrder> getAll() {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            List<FullOrder> orderList = orderDAO.getAll();
            TransactionManager.commit();
            return orderList;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    @Override
    public Boolean update(int orderId, Object newValue, String fieldName) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            boolean updateStatus = orderDAO.update(orderId, newValue, fieldName);
            TransactionManager.commit();
            return updateStatus;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    @Override
    public List<FullOrder> getByUserId(int userId) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            List<FullOrder> orderList = orderDAO.getGroupByUserId(userId);
            TransactionManager.commit();
            return orderList;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    @Override
    public List<FullOrder> getByStatus(String status) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            List<FullOrder> orderList = orderDAO.getGroupByStatus(status);
            TransactionManager.commit();
            return orderList;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }
}
