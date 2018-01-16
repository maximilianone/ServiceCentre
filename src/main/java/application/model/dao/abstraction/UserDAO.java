package application.model.dao.abstraction;

import application.model.entity.User;

import java.util.List;

public interface UserDAO extends DAO<User> {
    @Override
    Integer create(User user);

    List<User> getAll();
}
