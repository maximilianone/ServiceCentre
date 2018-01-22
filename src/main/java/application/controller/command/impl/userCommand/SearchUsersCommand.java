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

public class SearchUsersCommand implements Command {
    private UserService userService;

    public SearchUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        try {
            Object param = request.getParameter(SEARCH_VALUE);
            String name = request.getParameter(SEARCH_PARAMETER);

            getUsers(param, name, request, response);
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    private void getUsers(Object param, String name, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<User> userList = userService.getBy(param, name);

        request.getSession().setAttribute("usersFound", userList);
        response.sendRedirect(request.getContextPath() + "/jsp/usersInfo.jsp");
    }
}