package application.controller.command.impl.userCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.UserService;
import application.model.entity.User;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * command to change user password
 */

public class ChangePasswordCommand implements Command {
    private UserService userService;

    public ChangePasswordCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        changePassword(request, response);

    }

    /**
     * change user password
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */

    private void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        String password = request.getParameter(USER_PASSWORD);
        String oldPassword = request.getParameter(USER_OLD_PASSWORD);
        int userID = Integer.parseInt(request.getParameter(USER_ID));

        userService.changePassword(userID, oldPassword, password);

        response.sendRedirect(request.getContextPath() + "/jsp/personalPage.jsp");
    }
}
