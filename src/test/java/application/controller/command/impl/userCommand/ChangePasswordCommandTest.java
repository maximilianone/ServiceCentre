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

import static application.util.constants.RequestParameters.USER_ID;
import static application.util.constants.RequestParameters.USER_OLD_PASSWORD;
import static application.util.constants.RequestParameters.USER_PASSWORD;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangePasswordCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private ChangePasswordCommand changePasswordCommand;

    @Mock
    private HttpSession session;


    private static final String ID = "1";
    private static final int INT_ID = 1;
    private String password;
    private String oldPassword;

    @Test
    public void shouldChangePasswordAndRedirectToPersonalPage() throws Exception {
        //Given
        when(request.getParameter(USER_PASSWORD)).thenReturn(password);
        when(request.getParameter(USER_OLD_PASSWORD)).thenReturn(oldPassword);
        when(request.getParameter(USER_ID)).thenReturn(ID);
        //When
        changePasswordCommand.execute(request, response);
        //Then
        verify(userService).changePassword(INT_ID, oldPassword, password);
        verify(response).sendRedirect(endsWith("personalPage.jsp"));
    }


}