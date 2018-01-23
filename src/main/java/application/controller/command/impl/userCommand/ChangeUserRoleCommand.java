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
 * command to change user role
 */

public class ChangeUserRoleCommand implements Command {
    private UserService userService;

    public ChangeUserRoleCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int userID = Integer.parseInt(request.getParameter(UPDATED_USER_ID));
        String oldRole = request.getParameter(OLD_USER_ROLE);
        String newRole = request.getParameter(NEW_USER_ROLE);

        changeRole(userID, oldRole, newRole);

        showUsers(request, response);

    }

    /**
     * chamge user role
     * @param userID user id whose role to change
     * @param oldRole old role
     * @param newRole new role
     */

    private void changeRole(int userID, String oldRole, String newRole) {
        userService.changeRole(userID, newRole, oldRole);
    }

    /**
     * show all users
     *
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */

    private void showUsers(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<User> userList = userService.getAll();

        request.getSession().setAttribute("usersFound", userList);
        response.sendRedirect(request.getContextPath() + "/jsp/usersInfo.jsp");
    }
}