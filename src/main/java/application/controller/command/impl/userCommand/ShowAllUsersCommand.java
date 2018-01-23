package application.controller.command.impl.userCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.UserService;
import application.model.entity.User;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * command to show all users
 */

public class ShowAllUsersCommand implements Command {
    private UserService userService;

    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * @inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        getUsers(request, response);

    }

    /**
     * get all users
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */

    private void getUsers(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<User> userList = userService.getAll();

        request.getSession().setAttribute("usersFound", userList);
        response.sendRedirect(request.getContextPath() + "/jsp/usersInfo.jsp");
    }
}