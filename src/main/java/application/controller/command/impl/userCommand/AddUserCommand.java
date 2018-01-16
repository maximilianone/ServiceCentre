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
import java.io.IOException;

public class AddUserCommand implements Command {
    private Service<User> userService;
    private Mapper<User, HttpServletRequest> userRequestMapper;

    public AddUserCommand(UserService userService, UserRequestMapper userRequestMapper)
    {
        this.userService = userService;
        this.userRequestMapper = userRequestMapper;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException
    {
        User user = userRequestMapper.map(request);
        try {
            addUser(user);
            authorizeUser(user, request, response);
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
        }
    }

    private int addUser(User user)
            throws IOException, ServletException, ModelException {
        return userService.add(user);
    }

    private void authorizeUser(User user, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ModelException {
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
