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

public class ShowAllMasterOrdersCommand implements Command, DBParameters {
    private OrderService orderService;

    public ShowAllMasterOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {

            List<FullOrder> orderList;

            if (request.getParameter(MASTER).equals("current")) {
                int masterID = (Integer) request.getSession().getAttribute(USER_ID);
                orderList = getOrdersForMaster(masterID, request, RESERVED_BY_MASTER, PERFORMED);
            } else {
                orderList = getAllOrders(WAITING_FOR_MASTER, request);
            }

            sendRedirect(orderList, request, response);

    }

    private List<FullOrder> getAllOrders(String status, HttpServletRequest request) {
        request.getSession().setAttribute(MASTER, "all");
        return orderService.getBy(status, DB_ORDER_STATUS);
    }

    private List<FullOrder> getOrdersForMaster(int masterID, HttpServletRequest request, String... status) {
        request.getSession().setAttribute(MASTER, "current");
        return orderService.getByStatus(masterID, DB_MASTER_ID, status);
    }

    private void sendRedirect(List<FullOrder> orders, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession().setAttribute("ordersFound", orders);
        response.sendRedirect(request.getContextPath() + "/jsp/masterOrders.jsp");
    }
}