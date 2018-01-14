package application.model.dao.abstraction;

import application.model.entity.Comment;

public interface CommentDAO extends DAO<Boolean, Comment> {
    @Override
    Boolean create(Comment comment);
}
