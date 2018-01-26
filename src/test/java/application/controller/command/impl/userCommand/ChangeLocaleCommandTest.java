package application.controller.command.impl.userCommand;

import application.controller.service.abstraction.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static application.util.constants.RequestParameters.LOCALE;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangeLocaleCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private ChangeLocaleCommand changeLocaleCommand;

    @Mock
    private HttpSession session;

    private String locale;

    @Test
    public void shouldChangeLocaleAndRedirectToIndex() throws Exception{
        //Given
        when(request.getParameter(LOCALE)).thenReturn(locale);
        when(request.getSession(true)).thenReturn(session);
        //When
        changeLocaleCommand.execute(request, response);
        //Then
        verify(response).sendRedirect(endsWith("index.jsp"));
    }

}