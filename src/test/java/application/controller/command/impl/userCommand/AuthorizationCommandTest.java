package application.controller.command.impl.userCommand;

import application.controller.mapper.request.UserRequestMapper;
import application.controller.service.abstraction.UserService;
import application.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static application.util.constants.RequestParameters.USER_LOGIN;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AuthorizationCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserRequestMapper userRequestMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthorizationCommand authorizationCommand;

    @Mock
    private HttpSession session;

    private String login;
    private String password;

    private User user = new User.Builder().build();

    @Test
    public void shouldGetUserInfoAndRedirectToIndex() throws Exception{
        //Given
        when(request.getParameter(USER_LOGIN)).thenReturn(login);
        when(request.getParameter(USER_LOGIN)).thenReturn(login);
        when(request.getSession()).thenReturn(session);
        when(userService.authorization(login, password)).thenReturn(user);
        //When
        authorizationCommand.execute(request, response);
        //Then
        verify(userService).authorization(login, password);
        verify(response).sendRedirect(endsWith("index.jsp"));
    }
}