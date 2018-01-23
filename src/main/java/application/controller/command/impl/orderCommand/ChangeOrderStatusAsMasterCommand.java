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
 * command to change status as master
 */

public class ChangeOrderStatusAsMasterCommand implements Command, DBParameters {
    private OrderService orderService;

    public ChangeOrderStatusAsMasterCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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

    /**
     * change status as master
     *
     * @param orderID order id of order to change status
     * @param masterID master id to add information about master to order
     * @param status new status of order
     * @param oldStatus old status of order
     */


    private void changeStatusAsMaster(int orderID, int masterID, String status, String oldStatus){
        orderService.changeStatusAsMaster(orderID, masterID, status, oldStatus);
    }

    /**
     * get all orders with specified status
     *
     * @param status status of orders to find
     * @return list of found orders
     */

    private List<FullOrder> getAllOrders(String status) {
        return orderService.getBy(status, DB_ORDER_STATUS);
    }

    /**
     * get orders of specified master which he executing
     *
     * @param masterID master id of orders to find
     * @param statuses statuses of orders to find
     * @return list of found orders
     */

    private List<FullOrder> getOrdersForMaster(int masterID, String... statuses) {
        return orderService.getByStatus(masterID, DB_MASTER_ID, statuses);
    }

    /**
     * send redirect to order list page
     *
     * @param orderList order list to show
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */

    private void sendRedirect(List<FullOrder> orderList, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().setAttribute("ordersFound", orderList);
        response.sendRedirect(request.getContextPath() + "/jsp/masterOrders.jsp");
    }

}