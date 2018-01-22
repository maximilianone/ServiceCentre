package application.controller.command.impl.commentCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.CommentService;
import application.model.dto.FullComment;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetAllCommentsCommand implements Command {
    private CommentService commentService;

    public GetAllCommentsCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {

            showComments(request, response);
    }

    private void showComments(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ModelException {
        List<FullComment> commentList = commentService.getAll();

        request.getSession().setAttribute("comments", commentList);
        response.sendRedirect(request.getContextPath() + "/jsp/comment.jsp");
    }
}