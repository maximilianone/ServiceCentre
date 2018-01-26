package application.controller.command.impl.userCommand;

import application.controller.service.abstraction.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static application.util.constants.RequestParameters.SEARCH_PARAMETER;
import static application.util.constants.RequestParameters.SEARCH_VALUE;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchUsersCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private UserService userService;

    @InjectMocks
    private SearchUsersCommand searchUsersCommand;

    @Mock
    private HttpSession session;

    private String searchParam;
    private String searchValue;


    @Test
    public void shouldGetUsersByParameterAndRedirectToUsersInfo() throws Exception {
        //Given
        when(request.getParameter(SEARCH_VALUE)).thenReturn(searchValue);
        when(request.getParameter(SEARCH_PARAMETER)).thenReturn(searchParam);
        when(request.getSession()).thenReturn(session);
        //When
        searchUsersCommand.execute(request, response);
        //Then
        verify(userService).getBy(searchValue, searchParam);
        verify(response).sendRedirect(endsWith("usersInfo.jsp"));
    }

}