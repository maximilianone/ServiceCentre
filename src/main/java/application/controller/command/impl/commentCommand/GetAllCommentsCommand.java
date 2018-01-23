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

/**
 * command to get all comments from database
 */

public class GetAllCommentsCommand implements Command {
    private CommentService commentService;

    public GetAllCommentsCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
            showComments(request, response);
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