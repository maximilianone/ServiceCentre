package application.controller.command.impl.userCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.OrderService;
import application.controller.service.abstraction.UserService;
import application.model.dto.FullOrder;
import application.model.entity.User;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowPersonalPageCommand implements Command {
    private UserService userService;
    private OrderService orderService;

    public ShowPersonalPageCommand(UserService userService, OrderService orderService)
    {
        this.userService = userService;
        this.orderService=orderService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException
    {
        try {
            int userID = (Integer) request.getSession().getAttribute(USER_ID);
            getUserInfo(userID, request, response);
            getOrdersInfo(userID, request, response);
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
        }
    }

    private void getUserInfo(int userID, HttpServletRequest request, HttpServletResponse response){
        User userInfo = userService.getById(userID);
        request.getSession().setAttribute("firstName", userInfo.getFirstName());
        request.getSession().setAttribute("lastName", userInfo.getLastName());
        request.getSession().setAttribute("phone", userInfo.getPhone());
        request.getSession().setAttribute("email", userInfo.getEmail());
    }

    private void getOrdersInfo(int userID, HttpServletRequest request, HttpServletResponse response)
    throws IOException{
        List<FullOrder> orderList = orderService.getByUserId(userID);
        request.getSession().setAttribute("orders",orderList);
        response.sendRedirect(request.getContextPath() + "/jsp/personalPage.jsp");
    }
}
