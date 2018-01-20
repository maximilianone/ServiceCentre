package application.controller.command.impl.orderCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.OrderService;
import application.model.dto.FullOrder;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProcessNewOrderCommand implements Command, DBParameters {
    private OrderService orderService;

    public ProcessNewOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        try {
            String status = request.getParameter(ORDER_STATUS);
            int orderID = Integer.parseInt(request.getParameter(ORDER_ID));
            int userID = Integer.parseInt(request.getParameter(USER_ID));
            Object value;
            String param;
            if (status.equals(ACCEPTED)) {
                param = DB_PRICE;
                value = Double.parseDouble(request.getParameter(ORDER_PRICE));
            } else {
                param = DB_REJECTION_REASON;
                value = request.getParameter(ORDER_REJECTION_REASON);
            }
            processNewOrder(orderID, userID, param, value, status);
            getOrder(orderID, request, response);
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    private void processNewOrder(int orderID, int userID, String param, Object value, String status)
            throws IOException {
        orderService.processNewOrder(orderID, userID, param, value, status);
    }

    private void getOrder(int orderID, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        FullOrder order = orderService.getBy(orderID, DB_ORDER_ID).get(0);
        System.out.println(order);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/jsp/orderInfo.jsp").forward(request,response);
    }
}