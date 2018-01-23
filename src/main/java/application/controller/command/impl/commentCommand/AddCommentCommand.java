package application.controller.command.impl.commentCommand;

import application.controller.command.Command;
import application.controller.mapper.Mapper;
import application.controller.mapper.request.CommentRequestMapper;
import application.controller.service.abstraction.CommentService;
import application.controller.service.abstraction.Service;
import application.model.dto.FullComment;
import application.model.entity.Comment;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * command to add new order
 */

public class AddCommentCommand implements Command {
    private CommentService commentService;
    private Mapper<Comment, HttpServletRequest> commentRequestMapper;

    public AddCommentCommand(CommentService commentService, CommentRequestMapper commentRequestMapper) {
        this.commentService = commentService;
        this.commentRequestMapper = commentRequestMapper;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws SecurityException, IOException {
        Comment comment = commentRequestMapper.map(request);
        addComment(comment);

        showComments(request, response);
    }

    /**
     * add new comment to database
     *
     * @param comment comment to be added
     */

    private void addComment(Comment comment){
        commentService.add(comment);
    }

    /**
     * get all comments from db and show them on page
     *
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */

    private void showComments(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        List<FullComment> commentList = commentService.getAll();
        request.getSession().setAttribute("comments", commentList);
        response.sendRedirect(request.getContextPath() + "/jsp/comment.jsp");
    }
}
