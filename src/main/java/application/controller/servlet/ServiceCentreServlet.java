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

/**
 * main servlet
 */

public class ServiceCentreServlet extends HttpServlet implements ErrorMessages {

    /**
     * process get command
     * @param request request to server
     * @param response response to server
     * @throws ServletException when failed to get information from request
     * @throws IOException when failed yo send redirect
     */

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * process post command
     * @param request request to server
     * @param response response to server
     * @throws ServletException when failed to get information from request
     * @throws IOException when failed yo send redirect
     */

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    /**
     * process command
     * @param request request to server
     * @param response response to server
     */

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
