package application.model.entity;


import java.time.LocalDate;

public class Order {
    private int orderID;
    private int userID;
    private int productID;
    private int masterID;
    private int managerID;
    private String problemDescription;
    private String rejectionReason;
    private double price;
    private LocalDate dateOfPlacement;
    private Status status;
    private enum Status{NEW,
            ACCEPTED,
            REJECTED,
            PERFORMED,
            FULFILLED,
            CLOSED}

    public Order() {
    }

    public Order(int userID, String problemDescription, LocalDate dateOfPlacement) {
        this.userID = userID;
        this.problemDescription = problemDescription;
        this.dateOfPlacement = dateOfPlacement;
        this.status = Status.NEW;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getMasterID() {
        return masterID;
    }

    public void setMasterID(int masterID) {
        this.masterID = masterID;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public java.sql.Date getDateOfPlacement() {
        return java.sql.Date.valueOf(dateOfPlacement);
    }

    public void setDateOfPlacement(LocalDate dateOfPlacement) {
        this.dateOfPlacement = dateOfPlacement;
    }

    public String getStatus() {
        return status.name();
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderID != order.orderID) return false;
        if (userID != order.userID) return false;
        if (productID != order.productID) return false;
        if (masterID != order.masterID) return false;
        if (managerID != order.managerID) return false;
        if (Double.compare(order.price, price) != 0) return false;
        if (problemDescription != null ? !problemDescription.equals(order.problemDescription) : order.problemDescription != null)
            return false;
        if (rejectionReason != null ? !rejectionReason.equals(order.rejectionReason) : order.rejectionReason != null)
            return false;
        if (dateOfPlacement != null ? !dateOfPlacement.equals(order.dateOfPlacement) : order.dateOfPlacement != null)
            return false;
        return status == order.status;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderID;
        result = 31 * result + userID;
        result = 31 * result + productID;
        result = 31 * result + masterID;
        result = 31 * result + managerID;
        result = 31 * result + (problemDescription != null ? problemDescription.hashCode() : 0);
        result = 31 * result + (rejectionReason != null ? rejectionReason.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (dateOfPlacement != null ? dateOfPlacement.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", productID=" + productID +
                ", masterID=" + masterID +
                ", managerID=" + managerID +
                ", problemDescription='" + problemDescription + '\'' +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", price=" + price +
                ", dateOfPlacement=" + dateOfPlacement +
                ", status=" + status +
                '}';
    }
}
