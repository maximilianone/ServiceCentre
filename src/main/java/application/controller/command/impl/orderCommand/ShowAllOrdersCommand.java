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

/**
 * command to show all orders
 */

public class ShowAllOrdersCommand implements Command, DBParameters {
    private OrderService orderService;

    public ShowAllOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {

        List<FullOrder> orderList;
        if (request.getParameter(AS_ADMIN).equals("true")) {
            int adminID = (Integer) request.getSession().getAttribute(USER_ID);
            orderList = getOrdersForAdmin(adminID, request);
        } else {
            orderList = getAllOrders(request);
        }

        sendRedirect(orderList, request, response);

    }

    /**
     * get all orders
     *
     * @return list of found orders
     */

    private List<FullOrder> getAllOrders(HttpServletRequest request) {
        request.getSession().setAttribute(AS_ADMIN, "false");
        return orderService.getBy(ORDER_STATUS_NEW, DB_ORDER_STATUS);
    }

    /**
     * get all orders which are managed by specified admin
     *
     * @param adminID admin id whose orders to search
     * @return return list of found orders
     */

    private List<FullOrder> getOrdersForAdmin(int adminID, HttpServletRequest request) {
        request.getSession().setAttribute(AS_ADMIN, "true");
        return orderService.getBy(adminID, DB_MANAGER_ID);
    }

    /**
     * send redirect to order list page
     *
     * @param orders   order list to show
     * @param request  request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */

    private void sendRedirect(List<FullOrder> orders, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().setAttribute("ordersFound", orders);
        response.sendRedirect(request.getContextPath() + "/jsp/orders.jsp");
    }
}
