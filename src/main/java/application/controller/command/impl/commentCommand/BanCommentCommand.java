package application.controller.command.impl.commentCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.CommentService;
import application.model.dto.FullComment;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BanCommentCommand implements Command, DBParameters {
    private CommentService commentService;

    public BanCommentCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        try {
            int commentID = Integer.parseInt(request.getParameter(COMMENT_ID));
            banComment(commentID);
            showComments(request, response);

        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    private void banComment(int commentID)
            throws IOException, ServletException, ModelException {
        commentService.update(commentID,DB_COMMENT_STATUS_BANNED, DB_COMMENT_STATUS);
    }

    private void showComments(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ModelException {
        List<FullComment> commentList = commentService.getAll();
        request.getSession().setAttribute("comments", commentList);
        response.sendRedirect(request.getContextPath() + "/jsp/comment.jsp");
    }
}