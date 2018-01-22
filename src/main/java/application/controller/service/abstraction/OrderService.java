package application.controller.service.abstraction;

import application.model.dto.FullOrder;
import application.model.entity.Order;
import application.model.entity.Product;

import java.util.List;

public interface OrderService extends Service{
    Integer add(Product product, Order order);

    List<FullOrder> getAll();

    List<FullOrder> getByUserId(int userId);

    List<FullOrder> getBy(Object param, String name);

    List<FullOrder> getByStatus(Object param, String name, String... statuses);

    void processNewOrder(int orderID, int userID, String param, Object value, String status);

    void changeStatus(int orderID, String status, String oldStatus);

    void changeStatusAsMaster(int orderID, int masterID, String status, String oldStatus);
}
