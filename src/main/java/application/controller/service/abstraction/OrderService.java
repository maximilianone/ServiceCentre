package application.controller.service.abstraction;

import application.model.dto.FullOrder;
import application.model.entity.Order;
import application.model.entity.Product;

import java.util.List;

public interface OrderService extends Service{
    /**
     * add new order snd product
     *
     * @param product product to add
     * @param order order to add
     * @return id of added order
     */

    Integer add(Product product, Order order);

    /**
     * get all orders
     *
     * @return list of all orders
     */

    List<FullOrder> getAll();

    /**
     * get specified uses's orders
     *
     * @param userId user id whose orders to get
     * @return list of found orders
     */

    List<FullOrder> getByUserId(int userId);

    /**
     * get orders by parameter
     * @param param search parameter value
     * @param name search parameter name
     * @return list of found orders
     */

    List<FullOrder> getBy(Object param, String name);

    /**
     * get by parameter and statuses
     * @param param search parameter value
     * @param name search parameter name
     * @param statuses statuses of orders to search
     * @return list of found orders
     */

    List<FullOrder> getByStatus(Object param, String name, String... statuses);

    /**
     * process order with status - new
     *
     * @param orderID order id to process
     * @param userID admin id who is processing order
     * @param param parameter value to set
     * @param value parameter name to set
     * @param status new order status
     */

    void processNewOrder(int orderID, int userID, String param, Object value, String status);

    /**
     * change order status
     *
     * @param orderID order id of order to change
     * @param status new status
     * @param oldStatus old status
     */

    void changeStatus(int orderID, String status, String oldStatus);

    /**
     * change status as master
     *
     * @param orderID order id of order to change
     * @param masterID master id to set
     * @param status new status
     * @param oldStatus old status
     */

    void changeStatusAsMaster(int orderID, int masterID, String status, String oldStatus);
}
