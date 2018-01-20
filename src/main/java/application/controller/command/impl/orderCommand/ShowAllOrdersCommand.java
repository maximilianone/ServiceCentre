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

public class ShowAllOrdersCommand implements Command, DBParameters {
    private OrderService orderService;

    public ShowAllOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        try {
            List<FullOrder> orderList;
            if(request.getParameter(FOR_ADMIN).equals("true")){
                int adminID = (Integer) request.getSession().getAttribute(USER_ID);
                orderList = getOrdersForAdmin(adminID, request);
            }else {
                orderList = getAllOrders(request);
            }
            sendRedirect(orderList, request, response);
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
        }
    }

    private List<FullOrder> getAllOrders(HttpServletRequest request){
        request.getSession().setAttribute(FOR_ADMIN, "false");
        return orderService.getAll();
    }

    private List<FullOrder> getOrdersForAdmin(int adminID, HttpServletRequest request){
        request.getSession().setAttribute(FOR_ADMIN, "true");
        return orderService.getBy(adminID, DB_MANAGER_ID);
    }

    private void sendRedirect(List<FullOrder> orders, HttpServletRequest request, HttpServletResponse response)
    throws IOException{
        request.getSession().setAttribute("ordersFound",orders);
        response.sendRedirect(request.getContextPath() + "/jsp/orders.jsp");
    }
}
