package application.controller.command.impl.userCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.UserService;
import application.model.entity.User;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordCommand implements Command {
    private UserService userService;

    public ChangePasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {

        changePassword(request, response);

    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ModelException {
        String password = request.getParameter(USER_PASSWORD);
        String oldPassword = request.getParameter(USER_OLD_PASSWORD);
        int userID = Integer.parseInt(request.getParameter(USER_ID));

        userService.changePassword(userID, oldPassword, password);

        response.sendRedirect(request.getContextPath() + "/jsp/personalPage.jsp");
    }
}
