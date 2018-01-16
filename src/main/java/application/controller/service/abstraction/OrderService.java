package application.controller.service.abstraction;

import application.model.entity.FullOrder;
import application.model.entity.Order;

import java.util.List;

public interface OrderService extends Service<Order>{
    @Override
    Integer add(Order order);

    List<FullOrder> getAll();
}
