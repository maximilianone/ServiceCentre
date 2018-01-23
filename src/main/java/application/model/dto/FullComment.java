package application.model.dto;

import application.model.entity.Comment;

public class FullComment {
    private String login;
    private Comment comment;

    public FullComment(String login, Comment comment){
        this.login = login;
        this.comment = comment;
    }

    public String getLogin() {
        return login;
    }

    public String getContent(){
        return comment.getComment();
    }

    public String getStatus(){
        return comment.getStatus();
    }

    public int getId(){return comment.getId();}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FullComment that = (FullComment) o;

        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        return comment != null ? comment.equals(that.comment) : that.comment == null;
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FullComment{" +
                "login='" + login + '\'' +
                ", comment=" + comment +
                '}';
    }
}
