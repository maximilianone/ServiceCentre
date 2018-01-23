package application.model.dao.abstraction;

import application.model.entity.Comment;
import application.model.dto.FullComment;

import java.util.List;

public interface CommentDAO extends DAO<Comment> {
    /**
     * create new comment
     * @param comment comment to create
     * @return id of new comment
     */
    @Override
    Integer create(Comment comment);

    /**
     * get all comments
     * @return list of all comments
     */

    List<FullComment> getAll();
}
