package application.controller.command;

import application.util.constants.ErrorMessages;
import application.util.constants.RequestParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command extends ErrorMessages, RequestParameters{
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
