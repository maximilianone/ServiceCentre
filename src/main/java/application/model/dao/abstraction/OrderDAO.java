package application.model.dao.abstraction;

import application.model.entity.FullOrder;
import application.model.entity.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order> {
    @Override
    Integer create(Order order);

    List<FullOrder> getAll();
}
