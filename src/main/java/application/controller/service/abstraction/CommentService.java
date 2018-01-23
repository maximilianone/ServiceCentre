package application.controller.service.abstraction;

import application.model.entity.Comment;
import application.model.dto.FullComment;

import java.util.List;

public interface CommentService extends Service{
    /**
     * add new comment
     * @param comment comment to add
     * @return id of added comment
     */

    Integer add(Comment comment);

    /**
     * get all comments
     *
     * @return list of all comments
     */

    List<FullComment> getAll();
}
