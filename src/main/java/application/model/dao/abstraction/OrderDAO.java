package application.model.dao.abstraction;

import application.model.dto.FullOrder;
import application.model.entity.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order> {
    /**
     * create new order
     * @param order order to create
     * @return id of new order
     */
    @Override
    Integer create(Order order);

    /**
     * get all orders
     * @return list of all orders
     */

    List<FullOrder> getAll();

    /**
     * get group by parameter
     * @param param parameter value
     * @param name parameter name
     * @return orders group list
     */

    List<FullOrder> getGroupBy(Object param, String name);

    /**
     * get group by parameter and status
     * @param param parameter value
     * @param name parameter name
     * @param status status
     * @return orders group list
     */

    List<FullOrder> getGroupByStatus(Object param, String name, String status);

    /**
     * check if status didn't change
     * @param orderID order id of order to check
     * @param status order status
     * @return tru if status didn't change
     */

    boolean checkStatus(int orderID, String status);
}
