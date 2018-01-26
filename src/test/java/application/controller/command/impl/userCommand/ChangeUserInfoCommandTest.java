package application.controller.command.impl.userCommand;

import application.controller.service.abstraction.UserService;
import application.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
public class ChangeUserInfoCommandTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private ChangeUserInfoCommand changeUserInfoCommand;

    @Mock
    private HttpSession session;

    @Captor
    private ArgumentCaptor<User> captor;

    private static final String ID = "1";
    private static final int INT_ID = 1;
    private String firstName;
    private String lastName;
    private String phone;

    @Test
    public void shouldChangeUserInfoAndRedirectToPersonalPage() throws Exception {
        //Given
        when(request.getParameter(USER_FIRST_NAME)).thenReturn(firstName);
        when(request.getParameter(USER_LAST_NAME)).thenReturn(lastName);
        when(request.getParameter(USER_PHONE)).thenReturn(phone);
        when(request.getParameter(USER_ID)).thenReturn(ID);
        when(request.getSession()).thenReturn(session);
        //When
        changeUserInfoCommand.execute(request, response);
        //Then
        verify(userService).changeInfo(captor.capture());
        User capturedUser = captor.getValue();
        assertEquals(INT_ID, capturedUser.getId());
        verify(response).sendRedirect(endsWith("personalPage.jsp"));
    }


}