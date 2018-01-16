package application.model.dao.impl;

import application.controller.mapper.Mapper;
import application.model.dao.abstraction.CommentDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.Comment;
import application.model.exception.ModelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentDAOImpl implements CommentDAO{
    private static Logger logger = LogManager.getLogger(CommentDAOImpl.class.getName());

    private static final String CREATE = "INSERT INTO Comments(user_id, order_id, comment_content) " +
            "VALUES(?, ?, ?)";

    private static final String SELECT_ALL = "SELECT * FROM Comments";

    private Mapper<Comment, ResultSet> mapper;

    public CommentDAOImpl(Mapper<Comment, ResultSet> mapper){
        this.mapper = mapper;
    }

    public CommentDAOImpl(){}

    @Override
    public Integer create(Comment comment) {
        try {
            Map<Integer, Object> parameterMap = new HashMap<>();
            parameterMap.put(1, comment.getUserID());
            parameterMap.put(2, comment.getOrderID());
            parameterMap.put(3, comment.getComment());
            int newID = DAOTemplate.executeInsert(CREATE, parameterMap);
            logger.info("New comment added: " + comment);
            return newID;
        }catch (SQLException| ModelException e) {
            logger.error(ADD_COMMENT_ERROR);
            throw new ModelException(ADD_COMMENT_ERROR);
        }
    }

    @Override
    public List<Comment> getAll(){
        try {
            List<Comment> commentList = DAOTemplate.selectAll(mapper, SELECT_ALL);
            logger.info("All comments were shown");
            return commentList;
        } catch (ModelException e){
            logger.error(COMMENT_SELECT_ERROR);
            throw new ModelException(COMMENT_SELECT_ERROR);
        }
    }
}
