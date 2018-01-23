package application.controller.service.impl;

import application.model.dao.abstraction.UserDAO;
import application.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    private int userID;

    private User user = new User();


    @Test
    public void add() throws Exception {
        // Given
        when(userDAO.create(user)).thenReturn(userID);
        // When
        userService.add(user);
        // Then

    }

    @Test
    public void getAll() throws Exception {
    }

    @Test
    public void getBy() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void authorization() throws Exception {
    }

    @Test
    public void getById() throws Exception {
    }

    @Test
    public void changeInfo() throws Exception {
    }

    @Test
    public void changePassword() throws Exception {
    }

    @Test
    public void changeRole() throws Exception {
    }

}