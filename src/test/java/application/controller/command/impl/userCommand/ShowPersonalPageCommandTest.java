package application.controller.command.impl.userCommand;

import application.controller.service.abstraction.OrderService;
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

import static application.util.constants.RequestParameters.USER_ID;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowPersonalPageCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ShowPersonalPageCommand showPersonalPageCommand;

    @Mock
    private HttpSession session;

    private static final int ID_INT = 1;

    private User user = new User();

    @Test
    public void shouldGetUserInfoAndRedirectToPersonalPage() throws Exception {
        //Given
        when(session.getAttribute(USER_ID)).thenReturn(ID_INT);
        when(request.getSession()).thenReturn(session);
        when(userService.getById(ID_INT)).thenReturn(user);
        //When
        showPersonalPageCommand.execute(request, response);
        //Then
        verify(userService).getById(ID_INT);
        verify(orderService).getByUserId(ID_INT);
        verify(response).sendRedirect(endsWith("personalPage.jsp"));
    }

}