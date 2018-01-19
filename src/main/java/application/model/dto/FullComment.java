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
}
