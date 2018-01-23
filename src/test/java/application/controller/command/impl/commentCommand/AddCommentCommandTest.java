package application.controller.command.impl.commentCommand;

import application.controller.mapper.request.CommentRequestMapper;
import application.controller.service.abstraction.CommentService;
import application.model.dto.FullComment;
import application.model.entity.Comment;
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

import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AddCommentCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private CommentService commentService;

    @Mock
    private CommentRequestMapper commentRequestMapper;

    @InjectMocks
    private AddCommentCommand addCommentCommand;

    @Mock
    private HttpSession session;


    private Comment comment = new Comment();
    private List<FullComment> comments = new ArrayList<>();

    @Test
    public void shouldCreateOrderAndRedirectToComment() throws Exception {
        // Given
        when(commentRequestMapper.map(request)).thenReturn(comment);
        // When
        when(commentService.getAll()).thenReturn(comments);
        when(request.getSession()).thenReturn(session);
        addCommentCommand.execute(request, response);
        // Then
        verify(response).sendRedirect(endsWith("comment.jsp"));
    }
}