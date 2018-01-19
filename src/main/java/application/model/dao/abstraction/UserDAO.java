package application.model.dao.abstraction;

import application.model.entity.User;

import java.util.List;

public interface UserDAO extends DAO<User> {
    @Override
    Integer create(User user);

    List<User> getAll();

    User authorization(String login, String password);

    User getById(int userID);

    boolean checkPassword(int userID, String password);
}
