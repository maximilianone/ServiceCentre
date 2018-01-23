package application.model.dao.impl;

import application.controller.mapper.result.CommentResultMapper;
import application.model.dao.abstraction.CommentDAO;
import application.model.entity.Comment;
import application.model.dto.FullComment;
import application.model.exception.ModelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDAOImpl implements CommentDAO{
    private static Logger logger = LogManager.getLogger(CommentDAOImpl.class.getName());

    private static final String CREATE = "INSERT INTO Comments(user_id, order_id, comment_content) " +
            "VALUES(?, ?, ?)";

    private static final String SELECT_ALL = "SELECT * FROM Comments " +
            "INNER JOIN USERS ON USERS.user_id = COMMENTS.user_id WHERE comments.comment_status='valid'";

    private CommentResultMapper mapper;

    public CommentDAOImpl(CommentResultMapper mapper){
        this.mapper = mapper;
    }

    public CommentDAOImpl(){}

    /**
     *@inheritDoc
     */

    @Override
    public Integer create(Comment comment) {
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, comment.getUserID());
            parameterMap.put(2, comment.getOrderID());
            parameterMap.put(3, comment.getComment());
            int newID = DAOTemplate.executeInsert(CREATE, parameterMap);
            logger.info("New comment added");
            return newID;
        }catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
            logger.error("Failed attempt to add comment by user with id "+comment.getUserID());
            throw new ModelException(ADD_COMMENT_WRONG_ORDER);
        }catch (ModelException e) {
            logger.error(ADD_COMMENT_ERROR);
            throw new ModelException(ADD_COMMENT_ERROR);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public List<FullComment> getAll(){
        try {
            List<FullComment> commentList = DAOTemplate.selectAll(mapper, SELECT_ALL);
            logger.info("All comments were shown");
            return commentList;
        } catch (ModelException e){
            logger.error(COMMENT_SELECT_ERROR);
            throw new ModelException(COMMENT_SELECT_ERROR);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public Boolean update(int commentID, Object newValue, String fieldName) {
        try {
            boolean updateStatus = DAOTemplate.executeUpdate(createUpdateQuery(fieldName), commentID, newValue);
            logger.info("Comment with id " + commentID + " updated");
            return updateStatus;
        } catch (ModelException e) {
            logger.error(COMMENT_UPDATE_ERROR);
            throw new ModelException(COMMENT_UPDATE_ERROR);
        }
    }

    /**
     * create parametrized update query
     * @param fieldName parameter name
     * @return query
     */

    private String createUpdateQuery(String fieldName) {
        return "Update Comments SET " + fieldName + " = ? WHERE comment_id = ?";
    }

}
