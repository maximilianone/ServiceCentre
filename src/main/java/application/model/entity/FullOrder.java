package application.model.entity;

import java.time.LocalDate;

public class FullOrder {
    private int orderID;
    private int userID;
    private int masterID;
    private int managerID;
    private String problemDescription;
    private String rejectionReason;
    private double price;
    private LocalDate dateOfPlacement;
    private String status;
    private String productName;
    private String productType;

    public FullOrder(Order order, Product product){
        this.orderID = order.getOrderID();
        this.userID = order.getUserID();
        this.masterID = order.getMasterID();
        this.managerID = order.getManagerID();
        this.problemDescription = order.getProblemDescription();
        this.rejectionReason = order.getRejectionReason();
        this.price = order.getPrice();
        this.dateOfPlacement = order.getDateOfPlacement().toLocalDate();
        this.status = order.getStatus();
        this.productName = product.getProductName();
        this.productType = product.getProductType();
    }

    public int getOrderID() {
        return orderID;
    }

    public int getUserID() {
        return userID;
    }

    public int getMasterID() {
        return masterID;
    }

    public int getManagerID() {
        return managerID;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getDateOfPlacement() {
        return dateOfPlacement;
    }

    public String getStatus() {
        return status;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FullOrder fullOrder = (FullOrder) o;

        if (orderID != fullOrder.orderID) return false;
        if (userID != fullOrder.userID) return false;
        if (masterID != fullOrder.masterID) return false;
        if (managerID != fullOrder.managerID) return false;
        if (Double.compare(fullOrder.price, price) != 0) return false;
        if (problemDescription != null ? !problemDescription.equals(fullOrder.problemDescription) : fullOrder.problemDescription != null)
            return false;
        if (rejectionReason != null ? !rejectionReason.equals(fullOrder.rejectionReason) : fullOrder.rejectionReason != null)
            return false;
        if (dateOfPlacement != null ? !dateOfPlacement.equals(fullOrder.dateOfPlacement) : fullOrder.dateOfPlacement != null)
            return false;
        if (status != null ? !status.equals(fullOrder.status) : fullOrder.status != null) return false;
        if (productName != null ? !productName.equals(fullOrder.productName) : fullOrder.productName != null)
            return false;
        return productType != null ? productType.equals(fullOrder.productType) : fullOrder.productType == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderID;
        result = 31 * result + userID;
        result = 31 * result + masterID;
        result = 31 * result + managerID;
        result = 31 * result + (problemDescription != null ? problemDescription.hashCode() : 0);
        result = 31 * result + (rejectionReason != null ? rejectionReason.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dateOfPlacement != null ? dateOfPlacement.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (productType != null ? productType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FullOrder{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", masterID=" + masterID +
                ", managerID=" + managerID +
                ", problemDescription='" + problemDescription + '\'' +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", price=" + price +
                ", dateOfPlacement=" + dateOfPlacement +
                ", status='" + status + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                '}';
    }
}
