package application.model.entity;

public class Comment {
    private int id;
    private int userID;
    private int orderID;
    private String comment;

    public Comment(Builder builder) {
        this.id = builder.id;
        this.userID = builder.userID;
        this.orderID = builder.orderID;
        this.comment = builder.comment;
    }

    public int getId() {
        return id;
    }

    public int getUserID() {
        return userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment1 = (Comment) o;

        if (id != comment1.id) return false;
        if (userID != comment1.userID) return false;
        if (orderID != comment1.orderID) return false;
        return comment.equals(comment1.comment);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userID;
        result = 31 * result + orderID;
        result = 31 * result + comment.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userID=" + userID +
                ", orderID=" + orderID +
                ", comment='" + comment + '\'' +
                '}';
    }

    public static class Builder{
        private int id;
        private int userID;
        private int orderID;
        private String comment;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUserID(int userID) {
            this.userID = userID;
            return this;
        }

        public Builder setOrderID(int orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Comment build(){
            return new Comment(this);
        }
    }
}
