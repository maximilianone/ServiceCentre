package application.controller.command;

import application.util.constants.ErrorMessages;
import application.util.constants.RequestParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command extends ErrorMessages, RequestParameters{
    /**
     *execute command
     *
     * @param request request to server
     * @param response response to server
     * @throws SecurityException to indicate a security violation
     * @throws IOException when failed to send redirect
     */
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SecurityException;
}
