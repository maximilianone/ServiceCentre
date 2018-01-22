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

public class ShowAllUsersCommand implements Command {
    private UserService userService;

    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {

        getUsers(request, response);

    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<User> userList = userService.getAll();

        request.getSession().setAttribute("usersFound", userList);
        response.sendRedirect(request.getContextPath() + "/jsp/usersInfo.jsp");
    }
}