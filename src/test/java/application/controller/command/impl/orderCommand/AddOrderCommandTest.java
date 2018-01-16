package application.controller.command.impl.orderCommand;

import application.controller.mapper.request.OrderRequestMapper;
import application.controller.mapper.request.ProductRequestMapper;
import application.controller.service.abstraction.OrderService;
import application.controller.service.abstraction.ProductService;
import application.model.entity.Order;
import application.model.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddOrderCommandTest {
    private static final int PRODUCT_ID = 10;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Mock
    private ProductService productService;
    @Mock
    private OrderService orderService;
    @Mock
    private OrderRequestMapper orderRequestMapper;
    @Mock
    private ProductRequestMapper productRequestMapper;
    @InjectMocks
    private AddOrderCommand addOrderCommand;

    @Captor
    private ArgumentCaptor<Order> captor;

    private Order order = new Order();
    private Product product = new Product();

    @Test
    public void shouldCreateOrderWithProductAndRedirectToIndex() throws Exception {
        // Given
        when(productRequestMapper.map(request)).thenReturn(product);
        when(orderRequestMapper.map(request)).thenReturn(order);
        when(productService.add(product)).thenReturn(PRODUCT_ID);
        // When
        addOrderCommand.execute(request, response);
        // Then
        verify(orderService).add(captor.capture());
        Order capturedOrder = captor.getValue();
        assertEquals(PRODUCT_ID, capturedOrder.getProductID());
        verify(response).sendRedirect(endsWith("successOrderCreation.jsp"));
    }
}