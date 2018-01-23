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

/**
 * command to show user's personal page
 */

public class ShowPersonalPageCommand implements Command {
    private UserService userService;
    private OrderService orderService;

    public ShowPersonalPageCommand(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    /**
     * @inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        int userID = (Integer) request.getSession().getAttribute(USER_ID);

        getUserInfo(userID, request);

        getOrdersInfo(userID, request, response);

    }

    /**
     * get user info
     *
     * @param userID user id of user whose info to show
     * @param request request to server
     */

    private void getUserInfo(int userID, HttpServletRequest request) {
        User userInfo = userService.getById(userID);

        request.getSession().setAttribute("firstName", userInfo.getFirstName());
        request.getSession().setAttribute("lastName", userInfo.getLastName());
        request.getSession().setAttribute("phone", userInfo.getPhone());
        request.getSession().setAttribute("email", userInfo.getEmail());
    }

    /**
     * get user's orders
     * @param userID user id of user whose orders to show
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */

    private void getOrdersInfo(int userID, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<FullOrder> orderList = orderService.getByUserId(userID);

        request.getSession().setAttribute("userOrders", orderList);
        response.sendRedirect(request.getContextPath() + "/jsp/personalPage.jsp");
    }
}
