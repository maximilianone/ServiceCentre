package application.controller.mapper.result;

import application.controller.mapper.Mapper;
import application.model.entity.Comment;
import application.model.dto.FullComment;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentResultMapper implements Mapper<FullComment, ResultSet>, DBParameters {
    @Override
    public FullComment map(ResultSet resultSet){
        try {
            Comment comment = new Comment.Builder()
                    .setId(resultSet.getInt(DB_COMMENT_ID))
                    .setUserID(resultSet.getInt(DB_USER_ID))
                    .setOrderID(resultSet.getInt(DB_ORDER_ID))
                    .setComment(resultSet.getString(COMMENT_CONTENT))
                    .setStatus(resultSet.getString(DB_COMMENT_STATUS))
                    .build();
            String login = resultSet.getString(DB_LOGIN);
            return new FullComment(login, comment);

        } catch (SQLException e) {
            throw new ModelException(e);
        }
    }
}

