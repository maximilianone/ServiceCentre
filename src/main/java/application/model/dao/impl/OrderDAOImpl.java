package application.model.dao.impl;

import application.model.dao.abstraction.OrderDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.Order;
import application.model.exception.ModelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDAOImpl implements OrderDAO {
    private static Logger logger = LogManager.getLogger(OrderDAOImpl.class.getName());

    private static final String CREATE = "INSERT INTO ORDERS(user_id, product_id, problem_description," +
            " date_of_placement, order_status) VALUES(?, ?, ?, ?, ?)";

    @Override
    public Boolean create(Order order) {
        try {
            PreparedStatement preparedStatement = TransactionManager.getInstance().getConnection().prepareStatement(CREATE);
            boolean isAdded = insert(preparedStatement, order);
            if (isAdded) {
                logger.info("New order added: " + order);
            }else throw new ModelException(ADD_ORDER_ERROR);
            return isAdded;
        } catch (SQLException | ModelException e) {
            logger.error(ADD_ORDER_ERROR);
            throw new ModelException(ADD_ORDER_ERROR);
        }
    }

    private boolean insert(PreparedStatement preparedStatement, Order order) {
        try {
            preparedStatement.setInt(1, order.getUserID());
            preparedStatement.setInt(2, order.getProductID());
            preparedStatement.setString(3, order.getProblemDescription());
            preparedStatement.setDate(4, order.getDateOfPlacement());
            preparedStatement.setString(5, order.getStatus());
            int updateCount = preparedStatement.executeUpdate();
            return (updateCount > 0);
        } catch (SQLException e) {
            throw new ModelException(ADD_ORDER_ERROR);
        }
    }
}
