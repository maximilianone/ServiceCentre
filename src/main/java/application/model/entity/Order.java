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

    private enum Status {
        NEW,
        ACCEPTED,
        REJECTED,
        PERFORMED,
        FULFILLED,
        CLOSED
    }

    public Order() {
    }

    public Order(Builder builder) {
        this.orderID = builder.orderID;
        this.userID = builder.userID;
        this.productID = builder.productID;
        this.masterID = builder.masterID;
        this.managerID = builder.managerID;
        this.problemDescription = builder.problemDescription;
        this.rejectionReason = builder.rejectionReason;
        this.price = builder.price;
        this.dateOfPlacement = builder.dateOfPlacement;
        this.status = builder.status;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getUserID() {
        return userID;
    }

    public int getProductID() {
        return productID;
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

    public java.sql.Date getDateOfPlacement() {
        return java.sql.Date.valueOf(this.dateOfPlacement);
    }

    public String getStatus() {
        return status.name();
    }

    public void setProductID(int productID) {
        this.productID = productID;
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

    public static class Builder {
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

        public Builder setOrderID(int orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder setUserID(int userID) {
            this.userID = userID;
            return this;
        }

        public Builder setProductID(int productID) {
            this.productID = productID;
            return this;
        }

        public Builder setMasterID(int masterID) {
            this.masterID = masterID;
            return this;
        }

        public Builder setManagerID(int managerID) {
            this.managerID = managerID;
            return this;
        }

        public Builder setProblemDescription(String problemDescription) {
            this.problemDescription = problemDescription;
            return this;
        }

        public Builder setRejectionReason(String rejectionReason) {
            this.rejectionReason = rejectionReason;
            return this;
        }

        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Builder setDateOfPlacement(LocalDate dateOfPlacement) {
            this.dateOfPlacement = dateOfPlacement;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = Status.valueOf(status.toUpperCase());
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }
}
