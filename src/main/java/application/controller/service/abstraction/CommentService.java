package application.controller.service.abstraction;

import application.model.entity.Comment;

public interface CommentService extends Service<Boolean, Comment>{
    @Override
    Boolean add(Comment comment);

}
