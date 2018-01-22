package application.controller.command.impl.userCommand;

import application.controller.command.Command;
import application.controller.service.abstraction.UserService;
import application.model.entity.User;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;
import java.io.IOException;

public class ChangeUserInfoCommand implements Command {
    private UserService userService;

    public ChangeUserInfoCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        User user = new User.Builder()
                .setId(Integer.parseInt(request.getParameter(USER_ID)))
                .setFirstName(request.getParameter(USER_FIRST_NAME))
                .setLastName(request.getParameter(USER_LAST_NAME))
                .setPhone(request.getParameter(USER_PHONE))
                .build();

        changeInfo(user, request, response);

    }

    private void changeInfo(User user, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ModelException {
        userService.changeInfo(user);

        request.getSession().setAttribute(USER_FIRST_NAME, user.getFirstName());
        request.getSession().setAttribute(USER_LAST_NAME, user.getLastName());
        request.getSession().setAttribute(USER_PHONE, user.getPhone());

        response.sendRedirect(request.getContextPath() + "/jsp/personalPage.jsp");
    }
}