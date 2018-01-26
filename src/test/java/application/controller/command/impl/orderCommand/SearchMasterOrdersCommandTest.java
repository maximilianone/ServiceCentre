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
public class SearchMasterOrdersCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private SearchMasterOrdersCommand searchMasterOrdersCommand;

    @Mock
    private HttpSession session;

    private String param;
    private String name;

    private static final String MASTER_CURRENT = "current";
    private static final String MASTER_ALL = "all";
    private static final int ID_INT = 1;
    private static final String WAITING_FOR_MASTER = "waiting_for_master";
    private static final String RESERVED_BY_MASTER = "reserved_by_master";
    private static final String PERFORMED = "performed";

    @Test
    public void ShouldGetAllFreeOrdersAndRedirectToMasterOrders() throws Exception{
        //Given
        when(request.getParameter(SEARCH_VALUE)).thenReturn(param);
        when(request.getParameter(SEARCH_PARAMETER)).thenReturn(name);
        when(session.getAttribute(MASTER)).thenReturn(MASTER_ALL);
        when(request.getSession()).thenReturn(session);
        //When
        searchMasterOrdersCommand.execute(request, response);
        //Then
        verify(orderService).getByStatus(param, name, WAITING_FOR_MASTER);
        verify(response).sendRedirect(endsWith("masterOrders.jsp"));
    }

    @Test
    public void ShouldGetAllReservedOrdersAndRedirectToMasterOrders() throws Exception{
        //Given
        when(request.getParameter(SEARCH_VALUE)).thenReturn(param);
        when(request.getParameter(SEARCH_PARAMETER)).thenReturn(name);
        when(session.getAttribute(MASTER)).thenReturn(MASTER_CURRENT);
        when(session.getAttribute(USER_ID)).thenReturn(ID_INT);
        when(request.getSession()).thenReturn(session);
        //When
        searchMasterOrdersCommand.execute(request, response);
        //Then
        verify(orderService).getByStatus(param, name, PERFORMED, RESERVED_BY_MASTER);
        verify(response).sendRedirect(endsWith("masterOrders.jsp"));
    }

}