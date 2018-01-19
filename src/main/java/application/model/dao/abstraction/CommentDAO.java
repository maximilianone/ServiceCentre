package application.model.dao.abstraction;

import application.model.entity.Comment;
import application.model.dto.FullComment;

import java.util.List;

public interface CommentDAO extends DAO<Comment> {
    @Override
    Integer create(Comment comment);

    List<FullComment> getAll();
}
