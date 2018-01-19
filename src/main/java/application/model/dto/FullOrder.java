package application.model.dto;

import application.model.entity.Order;
import application.model.entity.Product;

import java.time.LocalDate;

public class FullOrder {
    private Order order;
    private Product product;

    public FullOrder(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public int getOrderID() {
        return order.getOrderID();
    }

    public int getUserID() {
        return order.getUserID();
    }

    public int getMasterID() {
        return order.getMasterID();
    }

    public int getManagerID() {
        return order.getManagerID();
    }

    public String getProblemDescription() {
        return order.getProblemDescription();
    }

    public String getRejectionReason() {
        return order.getRejectionReason();
    }

    public double getPrice() {
        return order.getPrice();
    }

    public LocalDate getDateOfPlacement() {
        return order.getDateOfPlacement().toLocalDate();
    }

    public String getStatus() {
        return order.getStatus();
    }

    public String getProductName() {
        return product.getProductName();
    }

    public String getProductType() {
        return product.getProductType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FullOrder fullOrder = (FullOrder) o;

        if (order != null ? !order.equals(fullOrder.order) : fullOrder.order != null) return false;
        return product != null ? product.equals(fullOrder.product) : fullOrder.product == null;
    }

    @Override
    public int hashCode() {
        int result = order != null ? order.hashCode() : 0;
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FullOrder{" +
                "order=" + order +
                ", product=" + product +
                '}';
    }
}
