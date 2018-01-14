package application.model.dao.impl;

import application.model.dao.abstraction.CommentDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.Comment;
import application.model.exception.ModelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommentDAOImpl implements CommentDAO{
    private static Logger logger = LogManager.getLogger(CommentDAOImpl.class.getName());

    private static final String CREATE = "INSERT INTO Comments(user_id, order_id, comment_content) " +
            "VALUES(?, ?, ?)";

    @Override
    public Boolean create(Comment comment) {
        try {
            PreparedStatement preparedStatement = TransactionManager.getInstance()
                    .getConnection()
                    .prepareStatement(CREATE);
            boolean isAdded = insert(preparedStatement, comment);
            logger.info("New comment added: " + comment);
            return isAdded;
        }catch (SQLException| ModelException e) {
            logger.error(ADD_COMMENT_ERROR);
            throw new ModelException(ADD_COMMENT_ERROR);
        }
    }

    private boolean insert(PreparedStatement preparedStatement, Comment comment){
        try {
            preparedStatement.setInt(1, comment.getUserID());
            preparedStatement.setInt(2, comment.getOrderID());
            preparedStatement.setString(3, comment.getComment());
            int updateCount = preparedStatement.executeUpdate();
            return (updateCount > 0);
        }catch (SQLException e) {
            throw new ModelException(e);
        }
    }
}
