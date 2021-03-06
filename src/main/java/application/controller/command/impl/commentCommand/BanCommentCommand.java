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

/**
 * command to ban comment
 */

public class BanCommentCommand implements Command, DBParameters {
    private CommentService commentService;

    public BanCommentCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws SecurityException, IOException {
        int commentID = Integer.parseInt(request.getParameter(COMMENT_ID));

        banComment(commentID);

        showComments(request, response);

    }

    /**
     *
     * @param commentID id of a comment to be banned
     */

    private void banComment(int commentID){
        commentService.update(commentID, DB_COMMENT_STATUS_BANNED, DB_COMMENT_STATUS);
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