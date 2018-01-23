package application.controller.command.impl.userCommand;

import application.controller.command.Command;
import application.controller.mapper.Mapper;
import application.controller.mapper.request.UserRequestMapper;
import application.controller.service.abstraction.Service;
import application.controller.service.abstraction.UserService;
import application.model.entity.User;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * command to add new user
 */

public class AddUserCommand implements Command {
    private UserService userService;
    private Mapper<User, HttpServletRequest> userRequestMapper;

    public AddUserCommand(UserService userService, UserRequestMapper userRequestMapper) {
        this.userService = userService;
        this.userRequestMapper = userRequestMapper;
    }

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        User user = userRequestMapper.map(request);

        user.setId(addUser(user));

        authorizeUser(user, request, response);

    }

    /**
     * add new user
     *
     * @param user user to add
     * @return id of a user who was added
     */

    private int addUser(User user) {
        return userService.add(user);
    }

    /**
     * authorize new user
     *
     * @param user user to authorize
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */

    private void authorizeUser(User user, HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        HttpSession session = request.getSession();
        session.setAttribute(USER_LOGIN, user.getLogin());
        session.setAttribute(USER_ID, user.getId());
        session.setAttribute(USER_ROLE, user.getRole());

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
