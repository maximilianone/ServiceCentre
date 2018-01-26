package application.controller.command.impl.orderCommand;

import application.controller.service.abstraction.OrderService;
import application.model.dto.FullOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static application.util.constants.DBParameters.DB_ORDER_ID;
import static application.util.constants.RequestParameters.OLD_ORDER_STATUS;
import static application.util.constants.RequestParameters.ORDER_ID;
import static application.util.constants.RequestParameters.ORDER_STATUS;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ChangeOrderStatusAsAdminCommandTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ChangeOrderStatusAsAdminCommand changeOrderStatusAsAdminCommand;

    @Mock
    private RequestDispatcher requestDispatcher;

    private static final String OLD_STATUS = "NEW";
    private static final String STATUS = "ACCEPTED";
    private static final String ADDRESS = "/jsp/orderInfo.jsp";
    private FullOrder order = new FullOrder();
    private List<FullOrder> orderList = new ArrayList<>();


    @Test
    public void shouldChangeStatusGetOrderAndRedirectToOrderInfo() throws Exception{
        // Given
        when(request.getParameter(ORDER_ID)).thenReturn("1");
        when(request.getParameter(ORDER_STATUS)).thenReturn(STATUS);
        when(request.getParameter(OLD_ORDER_STATUS)).thenReturn(OLD_STATUS);
        when(orderService.getBy(1, DB_ORDER_ID)).thenReturn(orderList);
        orderList.add(order);
        when(request.getRequestDispatcher(ADDRESS)).thenReturn(requestDispatcher);
//        when(requestDispatcher.forward(request, response))
        // When
        changeOrderStatusAsAdminCommand.execute(request, response);
        // Then
        verify(orderService).changeStatus(1, STATUS, OLD_STATUS);
        verify(orderService).getBy(1, DB_ORDER_ID);
        verify(requestDispatcher).forward(request, response);
    }

}