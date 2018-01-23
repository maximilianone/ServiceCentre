package application.controller.command.impl.orderCommand;

import application.controller.command.Command;
import application.controller.mapper.Mapper;
import application.controller.mapper.request.OrderRequestMapper;
import application.controller.mapper.request.ProductRequestMapper;
import application.controller.service.abstraction.OrderService;
import application.controller.service.abstraction.ProductService;
import application.controller.service.abstraction.Service;
import application.model.entity.Order;
import application.model.entity.Product;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * command to add order to database
 */

public class AddOrderCommand implements Command {
    private OrderService orderService;
    private Mapper<Product, HttpServletRequest> productRequestMapper;
    private Mapper<Order, HttpServletRequest> orderRequestMapper;

    public AddOrderCommand(OrderService orderService,
                           ProductRequestMapper productRequestMapper, OrderRequestMapper orderRequestMapper) {
        this.orderService = orderService;
        this.productRequestMapper = productRequestMapper;
        this.orderRequestMapper = orderRequestMapper;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Product product = productRequestMapper.map(request);
        Order order = orderRequestMapper.map(request);
        addOrder(request, response, product, order);

    }

    /**
     * adds new order, product and send redirect to successful added page
     * @param request request to server
     * @param response response to server
     * @param product product to be added
     * @param order order to be added
     * @throws IOException when failed to sent redirect
     * @throws ModelException when failed yo add new order
     */

    private void addOrder(HttpServletRequest request, HttpServletResponse response, Product product, Order order)
            throws IOException{
        orderService.add(product, order);

        response.sendRedirect(request.getContextPath() + "/jsp/successOrderCreation.jsp");
    }
}
