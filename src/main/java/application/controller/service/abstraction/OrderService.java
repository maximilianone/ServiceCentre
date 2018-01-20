package application.controller.service.abstraction;

import application.model.dto.FullOrder;
import application.model.entity.Order;
import application.model.entity.Product;

import java.util.List;

public interface OrderService extends Service<Object>{
    Integer add(Product product, Order order);

    List<FullOrder> getAll();

    List<FullOrder> getByUserId(int userId);

    List<FullOrder> getBy(Object param, String name);

    boolean processNewOrder(int orderID, int userID, String param, Object value, String status);
}
