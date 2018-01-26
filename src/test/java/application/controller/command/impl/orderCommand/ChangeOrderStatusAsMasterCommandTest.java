package application.controller.command.impl.orderCommand;

import application.controller.service.abstraction.OrderService;
import application.model.dto.FullOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static application.util.constants.DBParameters.DB_MASTER_ID;
import static application.util.constants.DBParameters.DB_ORDER_STATUS;
import static application.util.constants.RequestParameters.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ChangeOrderStatusAsMasterCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ChangeOrderStatusAsMasterCommand changeOrderStatusAsMasterCommand;

    @Mock
    private HttpSession session;

    private static final String OLD_STATUS = "WAITING_FOR_MASTER";
    private static final String WAITING_FOR_MASTER = "waiting_for_master";
    private static final String RESERVED_BY_MASTER = "reserved_by_master";
    private static final String PERFORMED = "performed";
    private static final String STATUS = "RESERVED_BY_MASTER";
    private static final String MASTER_CURRENT = "current";
    private static final String MASTER_ALL = "all";
    private static final String ID = "1";
    private static final int ID_INT = 1;


    @Test
    public void shouldChangeExecutedStatusAndRedirectToMasterOrders() throws Exception{
        //Given
        when(request.getParameter(ORDER_STATUS)).thenReturn(STATUS);
        when(request.getParameter(OLD_ORDER_STATUS)).thenReturn(OLD_STATUS);
        when(request.getParameter(ORDER_ID)).thenReturn(ID);
        when(request.getParameter(USER_ID)).thenReturn(ID);
        when(session.getAttribute(MASTER)).thenReturn(MASTER_ALL);
        when(request.getSession()).thenReturn(session);
        //When
        changeOrderStatusAsMasterCommand.execute(request, response);
        //Then
        verify(orderService).changeStatusAsMaster(ID_INT, ID_INT, STATUS, OLD_STATUS);
        verify(orderService).getBy(WAITING_FOR_MASTER, DB_ORDER_STATUS);
        verify(response).sendRedirect(endsWith("masterOrders.jsp"));
    }

    @Test
    public void shouldChangeReservedStatusAndRedirectToMasterOrders() throws Exception{
        //Given
        when(request.getParameter(ORDER_STATUS)).thenReturn(STATUS);
        when(request.getParameter(OLD_ORDER_STATUS)).thenReturn(OLD_STATUS);
        when(request.getParameter(ORDER_ID)).thenReturn(ID);
        when(request.getParameter(USER_ID)).thenReturn(ID);
        when(session.getAttribute(MASTER)).thenReturn(MASTER_CURRENT);
        when(session.getAttribute(USER_ID)).thenReturn(ID_INT);
        when(request.getSession()).thenReturn(session);
        //When
        changeOrderStatusAsMasterCommand.execute(request, response);
        //Then
        verify(orderService).changeStatusAsMaster(ID_INT, ID_INT, STATUS, OLD_STATUS);
        verify(orderService).getByStatus(ID_INT, DB_MASTER_ID, PERFORMED, RESERVED_BY_MASTER);
        verify(response).sendRedirect(endsWith("masterOrders.jsp"));
    }

}