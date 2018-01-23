package application.controller.command.impl;

import application.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * invalid command
 */

public class InvalidCommand implements Command {

    /**
     *@inheritDoc
     */

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, SecurityException, IOException{
        request.setAttribute("error", INVALID_COMMAND);
        request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
    }
}
