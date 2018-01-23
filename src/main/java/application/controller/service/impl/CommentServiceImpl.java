package application.controller.service.impl;

import application.controller.service.abstraction.CommentService;
import application.model.dao.abstraction.CommentDAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.Comment;
import application.model.dto.FullComment;
import application.model.exception.ModelException;

import java.sql.Connection;
import java.util.List;

public class CommentServiceImpl implements CommentService{
    private CommentDAO commentDAO;

    public CommentServiceImpl(CommentDAO commentDAO){
        this.commentDAO = commentDAO;
    }

    /**
     *@inheritDoc
     */

    @Override
    public Integer add(Comment comment) throws ModelException {
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            int commentId = commentDAO.create(comment);
            TransactionManager.commit();
            return commentId;
        }catch (ModelException e){
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public List<FullComment> getAll(){
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            List<FullComment> commentList = commentDAO.getAll();
            TransactionManager.commit();
            return commentList;
        }catch (ModelException e){
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }

    /**
     *@inheritDoc
     */

    @Override
    public Boolean update(int commentId, Object newValue, String fieldName){
        try {
            TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
            boolean updateStatus = commentDAO.update(commentId, newValue, fieldName);
            TransactionManager.commit();
            return updateStatus;
        }
        catch (ModelException e){
            TransactionManager.rollback();
            throw new ModelException(e);
        }
    }
}
