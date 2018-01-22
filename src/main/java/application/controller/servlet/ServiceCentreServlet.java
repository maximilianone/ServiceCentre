package application.controller.servlet;

import application.controller.command.CommandFactory;
import application.controller.validation.AccessValidator;
import application.model.exception.ModelException;
import application.util.constants.ErrorMessages;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServiceCentreServlet extends HttpServlet implements ErrorMessages {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        try {
            if (AccessValidator.getInstance().checkAccess(request, command)) {
                CommandFactory.getInstance().getCommand(command).execute(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + ERROR_PAGE);
            }
        } catch (ModelException e) {
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }
}
