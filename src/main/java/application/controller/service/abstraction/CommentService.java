package application.controller.service.abstraction;

import application.model.entity.Comment;

import java.util.List;

public interface CommentService extends Service<Comment>{
    @Override
    Integer add(Comment comment);

    List<Comment> getAll();
}
