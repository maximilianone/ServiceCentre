package application.controller.command.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static application.util.constants.ErrorMessages.ERROR_PAGE;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvalidCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @InjectMocks
    private InvalidCommand invalidCommand;

    private final static String ADDRESS = ERROR_PAGE;

    @Test
    public void shouldRedirectToError() throws Exception{
        //Given
        when(request.getRequestDispatcher(ADDRESS)).thenReturn(dispatcher);
        //When
        invalidCommand.execute(request, response);
        //Then
        verify(dispatcher).forward(request, response);
    }

}