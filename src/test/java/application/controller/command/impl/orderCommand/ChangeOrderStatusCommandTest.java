package application.controller.command.impl.orderCommand;

import application.controller.service.abstraction.OrderService;
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
public class ChangeOrderStatusCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ChangeOrderStatusCommand changeOrderStatusCommand;

    @Mock
    private HttpSession session;

    private static final int ID_INT = 1;
    private static final String ID = "1";
    private static final String STATUS = "AGREED";
    private static final String OLD_STATUS = "ACCEPTED";

    @Test
    public void shouldChangeStatusAndRedirectToPersonalPage() throws Exception{
        //Given
        when(request.getParameter(ORDER_ID)).thenReturn(ID);
        when(request.getParameter(ORDER_STATUS)).thenReturn(STATUS);
        when(request.getParameter(OLD_ORDER_STATUS)).thenReturn(OLD_STATUS);
        when(request.getParameter(USER_ID)).thenReturn(ID);
        when(request.getSession()).thenReturn(session);
        //When
        changeOrderStatusCommand.execute(request, response);
        //Then
        verify(orderService).changeStatus(ID_INT, STATUS, OLD_STATUS);
        verify(orderService).getByUserId(ID_INT);
        verify(response).sendRedirect(endsWith("personalPage.jsp"));
    }
}