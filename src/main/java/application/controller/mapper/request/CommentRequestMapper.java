package application.controller.mapper.request;

import application.controller.mapper.Mapper;
import application.model.entity.Comment;
import application.util.PropertyReader;
import application.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class CommentRequestMapper implements Mapper<Comment, HttpServletRequest>, RequestParameters {
    @Override
    public Comment map(HttpServletRequest request){

        int user_id = Integer.parseInt(request.getParameter(USER_ID));
        int order_id = Integer.parseInt(request.getParameter(ORDER_ID));
        String commentContent = request.getParameter(COMMENT_CONTENT);
        return new Comment.Builder()
                .setUserID(user_id)
                .setOrderID(order_id)
                .setComment(commentContent)
                .build();
    }
}
