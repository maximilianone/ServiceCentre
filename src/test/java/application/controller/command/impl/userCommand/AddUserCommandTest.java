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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AddUserCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserRequestMapper userRequestMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private AddUserCommand addUserCommand;

    @Mock
    private HttpSession session;


    private static final int USER_ID = 1;
    private User user = new User.Builder().build();

    @Test
    public void shouldAddNewUserAndRedirectToIndex() throws Exception {
        //Given
        when(userRequestMapper.map(request)).thenReturn(user);
        when(userService.add(user)).thenReturn(USER_ID);
        when(request.getSession()).thenReturn(session);
        //When
        addUserCommand.execute(request, response);
        //Then
        verify(userService).add(user);
        assertEquals(USER_ID, user.getId());
        verify(response).sendRedirect(endsWith("index.jsp"));
    }

}