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
import java.util.List;

/**
 * change order status as client
 */

public class ChangeOrderStatusCommand implements Command, DBParameters {
    private OrderService orderService;

    public ChangeOrderStatusCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *
     * @inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

            int orderID = Integer.parseInt(request.getParameter(ORDER_ID));
            String status = request.getParameter(ORDER_STATUS);
            String oldStatus = request.getParameter(OLD_ORDER_STATUS);

            OrderStatusChangeValidator.validateStatusChange(oldStatus, status);

            changeStatus(orderID, status, oldStatus);

            int userID = Integer.parseInt(request.getParameter(USER_ID));
            getUsersOrdersInfo(userID, request, response);

    }

    /**
     * change order status
     *
     * @param orderID order id of order to change
     * @param status new order status
     * @param oldStatus old order status
     */

    private void changeStatus(int orderID, String status, String oldStatus) {
        orderService.changeStatus(orderID, status, oldStatus);
    }

    /**
     * get new information about user's orders
     * @param userID user whose orders to show
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */

    private void getUsersOrdersInfo(int userID, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<FullOrder> orderList = orderService.getByUserId(userID);

        request.getSession().setAttribute("userOrders", orderList);
        response.sendRedirect(request.getContextPath() + "/jsp/personalPage.jsp");
    }
}
