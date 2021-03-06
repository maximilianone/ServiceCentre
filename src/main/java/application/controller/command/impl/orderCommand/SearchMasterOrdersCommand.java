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
import java.util.ArrayList;
import java.util.List;

/**
 * search orders which masters can execute or executing
 */

public class SearchMasterOrdersCommand implements Command, DBParameters {
    private OrderService orderService;

    public SearchMasterOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

            Object param = request.getParameter(SEARCH_VALUE);
            String name = request.getParameter(SEARCH_PARAMETER);

            List<FullOrder> orderList;
            if (request.getSession().getAttribute(MASTER).equals("current")) {
                int masterID = (Integer) request.getSession().getAttribute(USER_ID);
                orderList = getOrdersForMaster(masterID, param, name, PERFORMED, RESERVED_BY_MASTER);
            } else {
                orderList = getOrders(param, name, WAITING_FOR_MASTER);
            }

            sendRedirect(orderList, request, response);

    }

    /**
     * search orders which masters can execute
     *
     * @param param search parameter value
     * @param name  search parameter name
     * @param status status of orders which can be executed
     * @return list of found orders
     */

    private List<FullOrder> getOrders(Object param, String name, String status) {
        return orderService.getByStatus(param, name, status);
    }

    /**
     * search orders which are executed by specified master
     *
     * @param masterID master id whose orders to search
     * @param param search parameter value
     * @param name search parameter name
     * @param statuses statuses of master's orders
     * @return list of found orders
     */

    private List<FullOrder> getOrdersForMaster(int masterID, Object param, String name, String... statuses) {
        List<FullOrder> orderList = new ArrayList<>();

        for (FullOrder order : orderService.getByStatus(param, name, statuses)) {
            if (order.getMasterID() == masterID) {
                orderList.add(order);
            }
        }
        return orderList;
    }

    /**
     * send redirect to order list page
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