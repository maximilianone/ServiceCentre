package application.model.dao.impl;

import application.controller.mapper.Mapper;
import application.model.dao.abstraction.OrderDAO;
import application.model.dto.FullOrder;
import application.model.entity.Order;
import application.model.exception.ModelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {
    private static Logger logger = LogManager.getLogger(OrderDAOImpl.class.getName());

    private static final String CREATE = "INSERT INTO ORDERS(user_id, product_id, problem_description," +
            " date_of_placement) VALUES(?, ?, ?, ?)";

    private static final String SELECT_ALL = "SELECT * FROM ORDERS " +
            "INNER JOIN PRODUCT ON Orders.product_id=Product.product_id " +
            "INNER JOIN USERS ON USERS.user_id=orders.user_id " +
            "Left JOIN admin on orders.manager_id = admin.admin_id " +
            "Left Join masters on orders.master_id = masters.master_id " +
            "ORDER BY date_of_placement LIMIT 10";

    private static final String CHECK_STATUS = "Select * From Orders " +
            "INNER JOIN PRODUCT ON Orders.product_id=Product.product_id " +
            "INNER JOIN USERS ON USERS.user_id=orders.user_id " +
            "Left JOIN admin on orders.manager_id = admin.admin_id " +
            "Left Join masters on orders.master_id = masters.master_id " +
            "where order_id = ? and order_status = ?";

    private Mapper<FullOrder, ResultSet> mapper;

    public OrderDAOImpl(Mapper<FullOrder, ResultSet> mapper) {
        this.mapper = mapper;
    }

    public OrderDAOImpl() {

    }

    /**
     * @inheritDoc
     */

    @Override
    public Integer create(Order order) {
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, order.getUserID());
            parameterMap.put(2, order.getProductID());
            parameterMap.put(3, order.getProblemDescription());
            parameterMap.put(4, order.getDateOfPlacement());
            int newID = DAOTemplate.executeInsert(CREATE, parameterMap);
            logger.info("New order added");
            return newID;
        } catch (SQLException | ModelException e) {
            logger.error(ADD_ORDER_ERROR);
            throw new ModelException(ADD_ORDER_ERROR);
        }
    }

    /**
     * @inheritDoc
     */

    @Override
    public Boolean update(int orderID, Object newValue, String fieldName) {
        try {
            boolean updateStatus = DAOTemplate.executeUpdate(createUpdateQuery(fieldName), orderID, newValue);
            logger.info("Order with id " + orderID + " updated");
            return updateStatus;
        } catch (ModelException e) {
            logger.error(ORDER_UPDATE_ERROR);
            throw new ModelException(ORDER_UPDATE_ERROR);
        }
    }

    /**
     * @inheritDoc
     */

    @Override
    public List<FullOrder> getAll() {
        try {
            List<FullOrder> orderList = DAOTemplate.selectAll(mapper, SELECT_ALL);
            logger.info("All orders were shown");
            return orderList;
        } catch (ModelException e) {
            logger.error(ORDER_SELECT_ERROR);
            throw new ModelException(ORDER_SELECT_ERROR);
        }
    }

    /**
     * @inheritDoc
     */

    @Override
    public List<FullOrder> getGroupBy(Object param, String name) {
        String query = createSelectQuery(name);
        Map<Integer, Object> parameterMap = new HashMap<>();
        parameterMap.put(1, param);
        try {
            List<FullOrder> orderList = DAOTemplate.selectGroup(mapper, query, parameterMap);
            logger.info("All found orders were shown");
            return orderList;
        } catch (ModelException e) {
            logger.error(ORDER_SELECT_ERROR);
            throw new ModelException(ORDER_SELECT_ERROR);
        }
    }

    /**
     * @inheritDoc
     */

    @Override
    public List<FullOrder> getGroupByStatus(Object param, String name, String status) {
        String query = createSelectQueryWithStatus(name);
        Map<Integer, Object> parameterMap = new HashMap<>();
        parameterMap.put(1, status);
        parameterMap.put(2, param);
        try {
            List<FullOrder> orderList = DAOTemplate.selectGroup(mapper, query, parameterMap);
            logger.info("All found master orders were shown");
            return orderList;
        } catch (ModelException e) {
            logger.error(ORDER_SELECT_ERROR);
            throw new ModelException(ORDER_SELECT_ERROR);
        }
    }

    /**
     * create parametrized update query
     *
     * @param fieldName parameter name
     * @return query
     */

    private String createUpdateQuery(String fieldName) {
        return "Update Orders SET " + fieldName + " = ? WHERE order_id = ?";
    }

    /**
     * create parametrized select query
     *
     * @param fieldName parameter name
     * @return query
     */

    private String createSelectQuery(String fieldName) {
        String query = "Select * from Orders INNER JOIN PRODUCT ON Orders.product_id=Product.product_id " +
                "INNER JOIN USERS ON USERS.user_id=orders.user_id " +
                "Left JOIN admin on orders.manager_id = admin.admin_id " +
                "Left Join masters on orders.master_id = masters.master_id ";
        if (fieldName.equals(DB_LOGIN)) {
            query += "WHERE " + fieldName + " = ?";
        } else {
            query += "WHERE orders." + fieldName + " = ?";
        }
        query+=" ORDER BY date_of_placement LIMIT 10";
        return query;
    }

    /**
     * create parametrized select query with status parameter
     *
     * @param fieldName parameter name
     * @return query
     */

    private String createSelectQueryWithStatus(String fieldName) {
        String query = "Select * from Orders INNER JOIN PRODUCT ON Orders.product_id=Product.product_id " +
                "INNER JOIN USERS ON USERS.user_id=orders.user_id " +
                "Left JOIN admin on orders.manager_id = admin.admin_id " +
                "Left Join masters on orders.master_id = masters.master_id " +
                "Where orders.order_status=? ";
        switch (fieldName) {
            case DB_LOGIN:
                query += "and " + fieldName + " = ?";
                break;
            case DB_PRODUCT_TYPE:
                query += "and products." + fieldName + " = ?";
                break;
            default:
                query += "and orders." + fieldName + " = ?";
                break;
        }
        query+=" ORDER BY date_of_placement LIMIT 10";
        return query;
    }

    /**
     * @inheritDoc
     */

    @Override
    public boolean checkStatus(int orderID, String status) {
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, orderID);
            parameterMap.put(2, status);
            FullOrder order = DAOTemplate.selectOne(CHECK_STATUS, parameterMap, mapper);
            logger.info("status of order " + orderID + " has been changed");
            return (order != null);
        } catch (ModelException e) {
            logger.info(FAILED_ATTEMPT_CHANGE_STATUS);
            throw new ModelException(ALREADY_CHANGED);
        }
    }
}
