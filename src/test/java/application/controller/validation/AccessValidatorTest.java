package application.controller.validation;

import application.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static application.controller.command.CommadList.CHANGE_ORDER_STATUS;
import static application.util.constants.RequestParameters.USER_ROLE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccessValidatorTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    private AccessValidator accessValidator = AccessValidator.getInstance();
    private final static String ROLE = User.Role.CLIENT.name();
    private final static String GUEST_ROLE = User.Role.GUEST.name();

    @Test
    public void shouldReturnAccessFalse() throws Exception {
        assertEquals(accessValidator.checkAccess(request, CHANGE_ORDER_STATUS), false);
    }

    @Test
    public void shouldReturnAccessFalseWithoutRole() throws Exception {
        //Given
        when(request.getSession()).thenReturn(session);
        //Then
        assertEquals(accessValidator.checkAccess(request, CHANGE_ORDER_STATUS), false);
    }

    @Test
    public void shouldReturnAccessFalseInvalidRole() throws Exception {
        //Given
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(USER_ROLE)).thenReturn(GUEST_ROLE);
        //Then
        assertEquals(accessValidator.checkAccess(request, CHANGE_ORDER_STATUS), false);
    }

    @Test
    public void shouldReturnAccessTrue() throws Exception{
        //Given
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(USER_ROLE)).thenReturn(ROLE);
        //Then
        assertEquals(accessValidator.checkAccess(request, CHANGE_ORDER_STATUS), true);
    }

}