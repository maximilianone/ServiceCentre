package application.controller.command.impl.userCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.UserService;
import application.model.entity.User;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationCommand implements Command {
    private UserService userService;

    public AuthorizationCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {

        authorizeUser(request, response);

    }

    private void authorizeUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ModelException {
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);

        User user = userService.authorization(login, password);

        HttpSession session = request.getSession();
        session.setAttribute(USER_LOGIN, user.getLogin());
        session.setAttribute(USER_ID, user.getId());
        session.setAttribute(USER_ROLE, user.getRole());

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
