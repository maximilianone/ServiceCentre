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

public class ChangeOrderStatusCommand implements Command, DBParameters {
    private OrderService orderService;

    public ChangeOrderStatusCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        try {
            int orderID = Integer.parseInt(request.getParameter(ORDER_ID));
            int userID = Integer.parseInt(request.getParameter(USER_ID));
            String status = request.getParameter(ORDER_STATUS);
            changeStatus(orderID, status);
            getOrdersInfo(userID, request, response);
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
        }
    }

    private void changeStatus(int orderID, String status){
        orderService.update(orderID, status, DB_ORDER_STATUS);
    }

    private void getOrdersInfo(int userID, HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        List<FullOrder> orderList = orderService.getByUserId(userID);
        request.getSession().setAttribute("userOrders",orderList);
        response.sendRedirect(request.getContextPath() + "/jsp/personalPage.jsp");
    }
}
