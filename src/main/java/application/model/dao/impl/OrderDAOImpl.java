package application.model.dao.impl;

import application.controller.mapper.Mapper;
import application.model.dao.abstraction.OrderDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.FullOrder;
import application.model.entity.Order;
import application.model.exception.ModelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {
    private static Logger logger = LogManager.getLogger(OrderDAOImpl.class.getName());

    private static final String CREATE = "INSERT INTO ORDERS(user_id, product_id, problem_description," +
            " date_of_placement) VALUES(?, ?, ?, ?)";

    private static final String SELECT_ALL = "SELECT * FROM ORDERS INNER JOIN PRODUCT";

    private Mapper<FullOrder, ResultSet> mapper;

    public OrderDAOImpl(Mapper<FullOrder, ResultSet> mapper){
        this.mapper = mapper;
    }

    public OrderDAOImpl() {

    }

    @Override
    public Integer create(Order order) {
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, order.getUserID());
            parameterMap.put(2, order.getProductID());
            parameterMap.put(3, order.getProblemDescription());
            parameterMap.put(4, order.getDateOfPlacement());
            int newID = DAOTemplate.executeInsert(CREATE, parameterMap);
            logger.info("New order added: " + order);
            return newID;
        } catch (SQLException | ModelException e) {
            logger.error(ADD_ORDER_ERROR);
            throw new ModelException(ADD_ORDER_ERROR);
        }
    }

    @Override
    public List<FullOrder> getAll(){
        try {
            List<FullOrder> orderList = DAOTemplate.selectAll(mapper, SELECT_ALL);
            logger.info("All orders were shown");
            return orderList;
        } catch (ModelException e){
            logger.error(ORDER_SELECT_ERROR);
            e.printStackTrace();
            throw new ModelException(ORDER_SELECT_ERROR);
        }
    }
}
