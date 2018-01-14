package application.controller.service.impl;

import application.controller.service.abstraction.CommentService;
import application.model.dao.abstraction.CommentDAO;
import application.model.dao.abstraction.DAO;
import application.model.dao.transaction.TransactionManager;
import application.model.entity.Comment;
import application.model.exception.ModelException;

import java.sql.Connection;

public class CommentServiceImpl implements CommentService{
    private DAO<Boolean, Comment> commentDAO;

    public CommentServiceImpl(CommentDAO commentDAO){
        this.commentDAO = commentDAO;
    }

    @Override
    public Boolean add(Comment comment) throws ModelException {
        TransactionManager.runTransaction(Connection.TRANSACTION_READ_COMMITTED);
        boolean isAdded = commentDAO.create(comment);
        TransactionManager.commit();
        return isAdded;
    }
}
