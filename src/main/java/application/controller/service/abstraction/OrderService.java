package application.controller.service.abstraction;

import application.model.entity.Order;

public interface OrderService extends Service<Boolean, Order>{
    @Override
    Boolean add(Order order);
}
