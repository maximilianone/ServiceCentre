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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowAllUsersCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private ShowAllUsersCommand showAllUsersCommand;

    @Mock
    private HttpSession session;

    @Test
    public void shouldGetUsersAndRedirectToUsersInfo() throws Exception {
        //Given
        when(request.getSession()).thenReturn(session);
        //When
        showAllUsersCommand.execute(request, response);
        //Then
        verify(userService).getAll();
        verify(response).sendRedirect(endsWith("usersInfo.jsp"));
    }

}