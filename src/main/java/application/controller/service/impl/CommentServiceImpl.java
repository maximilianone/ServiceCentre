package application.controller.service.impl;

import application.controller.service.abstraction.CommentService;
import application.model.dao.abstraction.CommentDAO;
import application.model.dao.abstraction.DAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.Comment;
import application.model.exception.ModelException;

import java.sql.Connection;
import java.util.List;

public class CommentServiceImpl implements CommentService{
    private CommentDAO commentDAO;

    public CommentServiceImpl(CommentDAO commentDAO){
        this.commentDAO = commentDAO;
    }

    @Override
    public Integer add(Comment comment) throws ModelException {
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        int commentId = commentDAO.create(comment);
        TransactionManager.commit();
        return commentId;
    }

    @Override
    public List<Comment> getAll(){
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        List<Comment> commentList = commentDAO.getAll();
        TransactionManager.commit();
        return commentList;
    }
}
