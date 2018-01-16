package application.controller.mapper.request;

import application.controller.mapper.Mapper;
import application.model.entity.User;
import application.util.PropertyReader;
import application.util.constants.RequestParameters;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

public class UserRequestMapper implements Mapper<User, HttpServletRequest>, RequestParameters {
    @Override
    public User map(HttpServletRequest request){

        String firstName = request.getParameter(USER_FIRST_NAME);
        String lastName = request.getParameter(USER_LAST_NAME);
        String login = request.getParameter(USER_LOGIN);
        String password = request.getParameter(USER_PASSWORD);
        String email = request.getParameter(USER_EMAIL);
        String phone = request.getParameter(USER_PHONE);

        return new User.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setLogin(login)
                .setPassword(password)
                .setEmail(email)
                .setPhone(phone)
                .build();

    }
}
