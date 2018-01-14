package application.controller.requestMapper.impl;

import application.controller.requestMapper.RequestMapper;
import application.model.entity.Comment;
import application.util.PropertyReader;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class CommentRequestMapper implements RequestMapper<Comment> {
    @Override
    public Comment map(HttpServletRequest request){
        Properties properties = PropertyReader.readProperties(PARAMETERS);

        int user_id = Integer.parseInt(request.getParameter(properties.getProperty(USER_ID)));
        int order_id = Integer.parseInt(request.getParameter(properties.getProperty(ORDER_ID)));
        String commentContent = request.getParameter(properties.getProperty(COMMENT_CONTENT));
        return new Comment(user_id, order_id, commentContent);
    }
}
