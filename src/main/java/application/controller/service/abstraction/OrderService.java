package application.controller.service.abstraction;

import application.model.entity.Order;

public interface OrderService {
    boolean addOrder(Order order);
}
