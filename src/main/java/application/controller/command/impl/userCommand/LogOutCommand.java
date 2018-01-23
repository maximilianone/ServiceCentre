package application.controller.command.impl.userCommand;

import application.controller.command.Command;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * logout command
 */

public class LogOutCommand implements Command {

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        logOutUser(request, response);
    }

    /**
     * logout (invalidate session)
     * @param request request to server
     * @param response response to server
     * @throws IOException when failed to send redirect
     */

    private void logOutUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException{
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
