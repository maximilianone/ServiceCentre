package application.controller.command.impl.userCommand;

import application.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * command to change locale
 */

public class ChangeLocaleCommand implements Command {

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String locale = request.getParameter(LOCALE);

        HttpSession session = request.getSession(true);
        session.setAttribute(LOCALE, locale);

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}