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
 * command to search users
 */

public class SearchUsersCommand implements Command {
    private UserService userService;

    public SearchUsersCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Object param = request.getParameter(SEARCH_VALUE);
        String name = request.getParameter(SEARCH_PARAMETER);

        getUsers(param, name, request, response);

    }

    /**
     * search users
     *
     * @param param search parameter value
     * @param name search parameter name
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */


    private void getUsers(Object param, String name, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<User> userList = userService.getBy(param, name);

        request.getSession().setAttribute("usersFound", userList);
        response.sendRedirect(request.getContextPath() + "/jsp/usersInfo.jsp");
    }
}