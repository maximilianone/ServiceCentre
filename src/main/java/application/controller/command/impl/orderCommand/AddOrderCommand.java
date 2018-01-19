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

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        Product product = productRequestMapper.map(request);
        Order order = orderRequestMapper.map(request);
        try {
            addOrder(request, response, product, order);
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
        }
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response, Product product, Order order)
            throws IOException, ServletException, ModelException {
        orderService.add(product, order);
        response.sendRedirect(request.getContextPath() + "/jsp/successOrderCreation.jsp");
    }
}
