package application.model.dao.abstraction;

import application.model.entity.User;

public interface UserDAO extends DAO<Boolean, User> {
    @Override
    Boolean create(User user);
}
