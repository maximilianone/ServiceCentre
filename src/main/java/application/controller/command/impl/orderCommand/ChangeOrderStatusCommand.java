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

public class ChangeOrderStatusCommand implements Command, DBParameters {
    private OrderService orderService;

    public ChangeOrderStatusCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {

            int orderID = Integer.parseInt(request.getParameter(ORDER_ID));
            String status = request.getParameter(ORDER_STATUS);
            String oldStatus = request.getParameter(OLD_ORDER_STATUS);

            OrderStatusChangeValidator.validateStatusChange(oldStatus, status);

            changeStatus(orderID, status, oldStatus);

            int userID = Integer.parseInt(request.getParameter(USER_ID));
            getUsersOrdersInfo(userID, request, response);

    }

    private void changeStatus(int orderID, String status, String oldStatus) {
        orderService.changeStatus(orderID, status, oldStatus);
    }

    private void getUsersOrdersInfo(int userID, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<FullOrder> orderList = orderService.getByUserId(userID);

        request.getSession().setAttribute("userOrders", orderList);
        response.sendRedirect(request.getContextPath() + "/jsp/personalPage.jsp");
    }
}
