package application.controller.requestMapper.impl;

import application.controller.requestMapper.RequestMapper;
import application.model.entity.User;
import application.util.PropertyReader;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class UserRequestMapper implements RequestMapper<User> {
    @Override
    public User map(HttpServletRequest request){
        Properties properties = PropertyReader.readProperties(PARAMETERS);

        String firstName = request.getParameter(properties.getProperty(USER_FIRST_NAME));
        String lastName = request.getParameter(properties.getProperty(USER_LAST_NAME));
        String login = request.getParameter(properties.getProperty(USER_LOGIN));
        String password = request.getParameter(properties.getProperty(USER_PASSWORD));
        String email = request.getParameter(properties.getProperty(USER_EMAIL));
        String phone = request.getParameter(properties.getProperty(USER_PHONE));

        return new User(firstName, lastName, email, phone, login, password);

    }
}
