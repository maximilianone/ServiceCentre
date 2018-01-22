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

public class ChangeUserRoleCommand implements Command {
    private UserService userService;

    public ChangeUserRoleCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        try {
            int userID = Integer.parseInt(request.getParameter(UPDATED_USER_ID));
            String oldRole = request.getParameter(OLD_USER_ROLE);
            String newRole = request.getParameter(NEW_USER_ROLE);

            changeRole(userID, oldRole, newRole);

            showUsers(request, response);
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

    private void changeRole(int userID, String oldRole, String newRole) {
        userService.changeRole(userID, newRole, oldRole);
    }

    private void showUsers(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<User> userList = userService.getAll();

        request.getSession().setAttribute("usersFound", userList);
        response.sendRedirect(request.getContextPath() + "/jsp/usersInfo.jsp");
    }
}