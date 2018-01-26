package application.controller.command.impl.userCommand;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogOutCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private LogOutCommand logOutCommand;

    @Mock
    private HttpSession session;

    @Test
    public void shouldLogOutAndRedirectToIndex() throws Exception {
        //Given
        when(request.getSession()).thenReturn(session);
        //When
        logOutCommand.execute(request, response);
        //Then
        verify(session).invalidate();
        verify(response).sendRedirect(endsWith("index.jsp"));
    }

}