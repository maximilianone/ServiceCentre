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

public class ShowOrderCommand implements Command, DBParameters {

    private OrderService orderService;

    public ShowOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {

        int orderID = Integer.parseInt(request.getParameter(ORDER_ID));

        getOrder(orderID, request, response);

    }

    private void getOrder(int orderID, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        FullOrder order = orderService.getBy(orderID, DB_ORDER_ID).get(0);

        request.setAttribute("order", order);
        request.getRequestDispatcher("/jsp/orderInfo.jsp").forward(request, response);
    }
}
