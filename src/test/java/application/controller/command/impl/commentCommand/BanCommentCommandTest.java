package application.controller.command.impl.commentCommand;

import application.controller.service.abstraction.CommentService;
import application.model.dto.FullComment;
import application.util.constants.RequestParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BanCommentCommandTest implements RequestParameters{
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private CommentService commentService;

    @InjectMocks
    private BanCommentCommand banCommentCommand;

    @Mock
    private HttpSession session;

    private List<FullComment> comments = new ArrayList<>();

    @Test
    public void shouldBanCommentAndRedirectToComment() throws Exception {
        // Given
        when(request.getParameter(COMMENT_ID)).thenReturn("1");
        when(commentService.getAll()).thenReturn(comments);
        when(request.getSession()).thenReturn(session);
        // When
        banCommentCommand.execute(request, response);
        // Then
        verify(response).sendRedirect(endsWith("comment.jsp"));
    }

}