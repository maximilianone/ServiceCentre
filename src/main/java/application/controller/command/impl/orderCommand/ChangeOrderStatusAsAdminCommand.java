package application.controller.command.impl.orderCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.OrderService;
import application.controller.validation.OrderStatusChangeValidator;
import application.model.dto.FullOrder;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * change order status as admin
 */

public class ChangeOrderStatusAsAdminCommand implements Command, DBParameters {
    private OrderService orderService;

    public ChangeOrderStatusAsAdminCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws SecurityException, ServletException, IOException {
            int orderID = Integer.parseInt(request.getParameter(ORDER_ID));
            String status = request.getParameter(ORDER_STATUS);
            String oldStatus = request.getParameter(OLD_ORDER_STATUS);

            OrderStatusChangeValidator.validateStatusChange(oldStatus, status);

            changeStatus(orderID, status, oldStatus);

            getOrder(orderID, request, response);
    }

    /**
     * change status of specified order
     *
     * @param orderID order id which status to change
     * @param status new status of order
     * @param oldStatus old status of order
     */

    private void changeStatus(int orderID, String status, String oldStatus) {
        orderService.changeStatus(orderID, status, oldStatus);
    }

    /**
     * show details of specified order
     *
     * @param orderID order id of order to show details
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     * @throws ServletException when failed to get request dispatcher
     */

    private void getOrder(int orderID, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        FullOrder order = orderService.getBy(orderID, DB_ORDER_ID).get(0);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/jsp/orderInfo.jsp")
                .forward(request, response);
    }
}