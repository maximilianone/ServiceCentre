package application.controller.command.impl.orderCommand;

import application.controller.command.Command;
import application.controller.requestMapper.RequestMapper;
import application.controller.requestMapper.impl.OrderRequestMapper;
import application.controller.requestMapper.impl.ProductRequestMapper;
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
    private Service<Integer, Product> productService;
    private Service<Boolean, Order> orderService;
    private RequestMapper<Product> productRequestMapper;
    private RequestMapper<Order> orderRequestMapper;

    public AddOrderCommand(ProductService productService, OrderService orderService,
                           ProductRequestMapper productRequestMapper, OrderRequestMapper orderRequestMapper) {
        this.productService = productService;
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
            int productID = addProduct(product);
            order.setProductID(productID);
            addOrder(request, response, order);
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
        }
    }

    private int addProduct(Product product) throws IOException, ServletException, ModelException {
        return productService.add(product);
    }

    private void addOrder(HttpServletRequest request, HttpServletResponse response, Order order)
            throws IOException, ServletException, ModelException {
        orderService.add(order);
        response.sendRedirect(request.getContextPath() + "/jsp/successOrderCreation.jsp");
    }
}
