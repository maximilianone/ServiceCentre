package application.model.dao.abstraction;

import application.model.dto.FullOrder;
import application.model.entity.Order;

import java.util.List;

public interface OrderDAO extends DAO<Order> {
    @Override
    Integer create(Order order);

    List<FullOrder> getAll();

    List<FullOrder> getGroupBy(Object param, String name);

    boolean checkStatus(int orderID, String status);
}
