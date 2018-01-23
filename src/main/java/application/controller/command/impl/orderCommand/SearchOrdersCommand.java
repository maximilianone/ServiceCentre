package application.controller.command.impl.orderCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.OrderService;
import application.model.dto.FullOrder;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * command to search orders
 */

public class SearchOrdersCommand implements Command {
    private OrderService orderService;

    public SearchOrdersCommand(OrderService orderService) {
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
            if (request.getSession().getAttribute(AS_ADMIN).equals("true")) {
                int adminID =(Integer) request.getSession().getAttribute(USER_ID);
                orderList = getOrdersForAdmin(adminID, param, name);
            } else {
                orderList = getOrders(param, name);
            }

            sendRedirect(orderList, request, response);
    }

    /**
     * search orders
     *
     * @param param search parameter value
     * @param name search parameter name
     * @return list of found orders
     */

    private List<FullOrder> getOrders(Object param, String name){
        return orderService.getBy(param, name);
    }

    /**
     * search orders which are managed by specified admin
     *
     * @param adminID admin id whose orders to search
     * @param param search parameter value
     * @param name search parameter name
     * @return return list of found orders
     */

    private List<FullOrder> getOrdersForAdmin(int adminID, Object param, String name){
        List<FullOrder> orderList = new ArrayList<>();
        for (FullOrder order: orderService.getBy(param, name)){
            if(order.getManagerID()==adminID){
                orderList.add(order);
            }
        }
        return orderList;
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
        request.getSession().setAttribute("ordersFound",orderList);
        response.sendRedirect(request.getContextPath() + "/jsp/orders.jsp");
    }
}
