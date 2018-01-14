package application.model.entity;

public class Comment {
    private int id;
    private int userID;
    private int orderID;
    private String comment;

    public Comment(int userID, int orderID, String comment) {
        this.userID = userID;
        this.orderID = orderID;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
