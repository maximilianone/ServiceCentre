package application.model.dao.abstraction;

import application.model.entity.Order;

public interface OrderDAO extends DAO<Boolean, Order> {
    @Override
    Boolean create(Order order);
}
