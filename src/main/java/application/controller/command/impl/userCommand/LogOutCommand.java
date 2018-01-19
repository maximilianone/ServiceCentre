package application.controller.command.impl.userCommand;

import application.controller.command.Command;
import application.model.exception.ModelException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException {
        logOutUser(request, response);
    }

    private void logOutUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException, ModelException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
