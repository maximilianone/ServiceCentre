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

import static application.util.constants.RequestParameters.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ChangeUserRoleCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private ChangeUserRoleCommand changeUserRoleCommand;

    @Mock
    private HttpSession session;

    private static final String ID = "1";
    private static final int INT_ID = 1;
    private String oldRole;
    private String newRole;

    @Test
    public void shouldChangeUserRoleAndRedirectToUsersInfo() throws Exception {
        //Given
        when(request.getParameter(UPDATED_USER_ID)).thenReturn(ID);
        when(request.getParameter(OLD_USER_ROLE)).thenReturn(oldRole);
        when(request.getParameter(NEW_USER_ROLE)).thenReturn(newRole);
        when(request.getSession()).thenReturn(session);
        //When
        changeUserRoleCommand.execute(request, response);
        //Then
        verify(userService).changeRole(INT_ID, newRole, oldRole);
        verify(userService).getAll();
        verify(response).sendRedirect(endsWith("usersInfo.jsp"));
    }
}