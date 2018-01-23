package application.controller.service.impl;

import application.controller.service.abstraction.OrderService;
import application.model.dao.abstraction.OrderDAO;
import application.model.dao.abstraction.ProductDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.dto.FullOrder;
import application.model.entity.Order;
import application.model.entity.Product;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService, DBParameters {
    private OrderDAO orderDAO;
    private ProductDAO productDAO;

    public OrderServiceImpl(OrderDAO orderDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    /**
     *@inheritDoc
     */

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

    /**
     *@inheritDoc
     */

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

    /**
     *@inheritDoc
     */

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

    /**
     *@inheritDoc
     */

    @Override
    public List<FullOrder> getByUserId(int userId) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            List<FullOrder> orderList = orderDAO.getGroupBy(userId, DB_USER_ID);
            TransactionManager.commit();
            return orderList;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public List<FullOrder> getBy(Object param, String name) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            List<FullOrder> orderList = orderDAO.getGroupBy(param, name);
            TransactionManager.commit();
            return orderList;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public List<FullOrder> getByStatus(Object param, String name, String... statuses) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            List<FullOrder> orderList = new ArrayList<>();
            for (String status:statuses){
                orderList.addAll(orderDAO.getGroupByStatus(param, name, status));
            }
            TransactionManager.commit();
            return orderList;
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */


    @Override
    public void processNewOrder(int orderID, int userID, String param, Object value, String status) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_SERIALIZABLE);
            orderDAO.checkStatus(orderID, DB_ORDER_STATUS_NEW);
            orderDAO.update(orderID, userID, DBParameters.DB_MANAGER_ID);
            orderDAO.update(orderID, value, param);
            orderDAO.update(orderID, status, DB_ORDER_STATUS);
            TransactionManager.commit();
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public void changeStatus(int orderID, String status, String oldStatus) {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_SERIALIZABLE);
            orderDAO.checkStatus(orderID, oldStatus);
            orderDAO.update(orderID, status, DB_ORDER_STATUS);
            TransactionManager.commit();
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public void changeStatusAsMaster(int orderID, int masterID, String status, String oldStatus){
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_SERIALIZABLE);
            orderDAO.checkStatus(orderID, oldStatus);
            orderDAO.update(orderID, status, DB_ORDER_STATUS);
            orderDAO.update(orderID, masterID, DB_MASTER_ID);
            TransactionManager.commit();
        } catch (ModelException e) {
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }
}
