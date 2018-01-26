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

import static application.util.constants.DBParameters.DB_MANAGER_ID;
import static application.util.constants.RequestParameters.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchOrdersCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private SearchOrdersCommand searchOrdersCommand;

    @Mock
    private HttpSession session;

    private String param;
    private String name;
    private static final int ID_INT = 1;
    private static final String AS_ADMIN_TRUE = "true";
    private static final String AS_ADMIN_FALSE = "false";

    @Test
    public void ShouldGetAllOrdersAndRedirectToOrders() throws Exception{
        //Given

        when(session.getAttribute(AS_ADMIN)).thenReturn(AS_ADMIN_FALSE);
        when(request.getParameter(SEARCH_VALUE)).thenReturn(param);
        when(request.getParameter(SEARCH_PARAMETER)).thenReturn(name);
        when(request.getSession()).thenReturn(session);
        //When
        searchOrdersCommand.execute(request, response);
        //Then
        verify(orderService).getBy(param, name);
        verify(response).sendRedirect(endsWith("orders.jsp"));
    }

    @Test
    public void ShouldGetAllAdminedOrdersAndRedirectToOrders() throws Exception{
        //Given
        when(session.getAttribute(AS_ADMIN)).thenReturn(AS_ADMIN_TRUE);
        when(request.getParameter(SEARCH_VALUE)).thenReturn(param);
        when(request.getParameter(SEARCH_PARAMETER)).thenReturn(name);
        when(session.getAttribute(USER_ID)).thenReturn(ID_INT);
        when(request.getSession()).thenReturn(session);
        //When
        searchOrdersCommand.execute(request, response);
        //Then
        verify(orderService).getBy(param, name);
        verify(response).sendRedirect(endsWith("orders.jsp"));
    }

}