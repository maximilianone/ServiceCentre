package application.controller.mapper.result;

import application.controller.mapper.Mapper;
import application.model.entity.Comment;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentResultMapper implements Mapper<Comment, ResultSet>, DBParameters {
    @Override
    public Comment map(ResultSet resultSet){
        try {
            return new Comment.Builder()
                    .setId(resultSet.getInt(COMMENT_ID))
                    .setUserID(resultSet.getInt(USER_ID))
                    .setOrderID(resultSet.getInt(ORDER_ID))
                    .setComment(resultSet.getString(COMMENT_CONTENT))
                    .build();
        } catch (SQLException e) {
            throw new ModelException(e);
        }
    }
}

