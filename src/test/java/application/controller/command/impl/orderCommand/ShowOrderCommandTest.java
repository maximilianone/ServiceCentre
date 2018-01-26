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
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static application.util.constants.DBParameters.DB_ORDER_ID;
import static application.util.constants.RequestParameters.ORDER_ID;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShowOrderCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ShowOrderCommand showOrderCommand;

    @Mock
    private RequestDispatcher dispatcher;

    private static final int ID_INT = 1;
    private static final String ID = "1";
    private static final String ADDRESS = "/jsp/orderInfo.jsp";

    private List<FullOrder> orders = new ArrayList<>();
    private FullOrder order = new FullOrder();

    @Test
    public void shouldGetOrderInfoAndRedirectToOrderInfo() throws Exception {
        //Given
        when(request.getRequestDispatcher(ADDRESS)).thenReturn(dispatcher);
        when(request.getParameter(ORDER_ID)).thenReturn(ID);
        when(orderService.getBy(ID_INT, DB_ORDER_ID)).thenReturn(orders);
        orders.add(order);
        //When
        showOrderCommand.execute(request, response);
        //Then
        verify(orderService).getBy(ID_INT, DB_ORDER_ID);
        verify(dispatcher).forward(request, response);
    }
}