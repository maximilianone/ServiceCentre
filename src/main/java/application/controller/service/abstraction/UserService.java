package application.controller.service.abstraction;

import application.model.entity.User;

import java.util.List;

public interface UserService extends Service {
    Integer add(User user);

    List<User> getAll();

    List<User> getBy(Object param, String name);

    User authorization(String login, String password);

    User getById(int userID);

    Boolean changeInfo(User user);

    Boolean changePassword(int userID, String oldPassword, String password);

    void changeRole(int userID, String newRole, String oldRole);
}
