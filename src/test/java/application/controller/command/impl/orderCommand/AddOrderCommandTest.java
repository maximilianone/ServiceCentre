package application.controller.command.impl.orderCommand;

import application.controller.mapper.request.OrderRequestMapper;
import application.controller.mapper.request.ProductRequestMapper;
import application.controller.service.abstraction.OrderService;
import application.model.entity.Order;
import application.model.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddOrderCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private OrderService orderService;
    @Mock
    private OrderRequestMapper orderRequestMapper;
    @Mock
    private ProductRequestMapper productRequestMapper;
    @InjectMocks
    private AddOrderCommand addOrderCommand;

    private Order order = new Order();
    private Product product = new Product();

    @Test
    public void shouldCreateOrderAndRedirectToSuccessOrderCreation() throws Exception {
        // Given
        when(productRequestMapper.map(request)).thenReturn(product);
        when(orderRequestMapper.map(request)).thenReturn(order);
        // When
        addOrderCommand.execute(request, response);
        // Then
        verify(orderService).add(product, order);
        verify(response).sendRedirect(endsWith("successOrderCreation.jsp"));
    }
}