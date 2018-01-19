package application.model.entity;

public class Comment {
    private int id;
    private int userID;
    private int orderID;
    private String comment;
    private CommentStatus status;

    private enum CommentStatus{VALID, BANNED}

    public Comment(Builder builder) {
        this.id = builder.id;
        this.userID = builder.userID;
        this.orderID = builder.orderID;
        this.comment = builder.comment;
        this.status = builder.status;
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

    public String getStatus(){return status.name();}



    public static class Builder{
        private int id;
        private int userID;
        private int orderID;
        private String comment;
        private CommentStatus status;

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

        public Builder setStatus(String status) {
            this.status = CommentStatus.valueOf(status.toUpperCase());
            return this;
        }

        public Comment build(){
            return new Comment(this);
        }
    }
}
