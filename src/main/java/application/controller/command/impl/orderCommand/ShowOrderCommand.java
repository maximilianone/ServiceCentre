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

/**
 * command to show order details
 */

public class ShowOrderCommand implements Command, DBParameters {

    private OrderService orderService;

    public ShowOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderID = Integer.parseInt(request.getParameter(ORDER_ID));

        getOrder(orderID, request, response);

    }

    /**
     * show information about specified order
     *
     * @param orderID order id of order to show
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     * @throws ServletException when failed to get request dispatcher
     */

    private void getOrder(int orderID, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        FullOrder order = orderService.getBy(orderID, DB_ORDER_ID).get(0);

        request.setAttribute("order", order);
        request.getRequestDispatcher("/jsp/orderInfo.jsp").forward(request, response);
    }
}
