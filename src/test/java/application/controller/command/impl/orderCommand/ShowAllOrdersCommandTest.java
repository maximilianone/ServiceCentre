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
        import static application.util.constants.DBParameters.DB_ORDER_STATUS;
        import static application.util.constants.RequestParameters.AS_ADMIN;
        import static application.util.constants.RequestParameters.ORDER_STATUS_NEW;
        import static application.util.constants.RequestParameters.USER_ID;
        import static org.junit.Assert.*;
        import static org.mockito.ArgumentMatchers.endsWith;
        import static org.mockito.Mockito.verify;
        import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowAllOrdersCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ShowAllOrdersCommand showAllOrdersCommand;

    @Mock
    private HttpSession session;

    private static final int ID_INT = 1;
    private static final String AS_ADMIN_TRUE = "true";
    private static final String AS_ADMIN_FALSE = "false";

    @Test
    public void ShouldGetAllOrdersAndRedirectToOrders() throws Exception{
        //Given
        when(request.getParameter(AS_ADMIN)).thenReturn(AS_ADMIN_FALSE);
        when(request.getSession()).thenReturn(session);
        //When
        showAllOrdersCommand.execute(request, response);
        //Then
        verify(orderService).getBy(ORDER_STATUS_NEW, DB_ORDER_STATUS);
        verify(response).sendRedirect(endsWith("orders.jsp"));
    }

    @Test
    public void ShouldGetAllAdminedOrdersAndRedirectToOrders() throws Exception{
        //Given
        when(request.getParameter(AS_ADMIN)).thenReturn(AS_ADMIN_TRUE);
        when(session.getAttribute(USER_ID)).thenReturn(ID_INT);
        when(request.getSession()).thenReturn(session);
        //When
        showAllOrdersCommand.execute(request, response);
        //Then
        verify(orderService).getBy(ID_INT, DB_MANAGER_ID);
        verify(response).sendRedirect(endsWith("orders.jsp"));
    }

}