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

public class ChangeOrderStatusAsMasterCommand implements Command, DBParameters {
    private OrderService orderService;

    public ChangeOrderStatusAsMasterCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
            String status = request.getParameter(ORDER_STATUS);
            String oldStatus = request.getParameter(OLD_ORDER_STATUS);

            OrderStatusChangeValidator.validateStatusChange(oldStatus, status);

            int orderID = Integer.parseInt(request.getParameter(ORDER_ID));
            int masterID = Integer.parseInt(request.getParameter(USER_ID));

            changeStatusAsMaster(orderID, masterID, status, oldStatus);

            List<FullOrder> orderList;
            if (request.getSession().getAttribute(MASTER).equals("current")) {
                int userID = (Integer) request.getSession().getAttribute(USER_ID);
                orderList = getOrdersForMaster(userID, PERFORMED, RESERVED_BY_MASTER);
            } else {
                orderList = getAllOrders(WAITING_FOR_MASTER);
            }

            sendRedirect(orderList, request, response);
    }

    private void changeStatusAsMaster(int orderID, int masterID, String status, String oldStatus){
        orderService.changeStatusAsMaster(orderID, masterID, status, oldStatus);
    }

    private List<FullOrder> getAllOrders(String status) {
        return orderService.getBy(status, DB_ORDER_STATUS);
    }

    private List<FullOrder> getOrdersForMaster(int masterID, String... statuses) {
        return orderService.getByStatus(masterID, DB_MASTER_ID, statuses);
    }

    private void sendRedirect(List<FullOrder> orderList, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().setAttribute("ordersFound", orderList);
        response.sendRedirect(request.getContextPath() + "/jsp/masterOrders.jsp");
    }

}