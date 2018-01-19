package application.controller.command.impl.commentCommand;

import application.controller.command.Command;
import application.controller.mapper.Mapper;
import application.controller.mapper.request.CommentRequestMapper;
import application.controller.service.abstraction.CommentService;
import application.controller.service.abstraction.Service;
import application.model.entity.Comment;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCommentCommand implements Command {
    private CommentService commentService;
    private Mapper<Comment, HttpServletRequest> commentRequestMapper;

    public AddCommentCommand(CommentService commentService, CommentRequestMapper commentRequestMapper) {
        this.commentService = commentService;
        this.commentRequestMapper = commentRequestMapper;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        Comment comment = commentRequestMapper.map(request);
        try {
            addComment(comment, request, response);
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    private void addComment(Comment comment, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ModelException {
        commentService.add(comment);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
