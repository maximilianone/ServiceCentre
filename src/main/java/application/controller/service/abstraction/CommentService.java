package application.controller.service.abstraction;

import application.model.entity.Comment;
import application.model.dto.FullComment;

import java.util.List;

public interface CommentService extends Service<Comment>{
    Integer add(Comment comment);

    List<FullComment> getAll();
}
