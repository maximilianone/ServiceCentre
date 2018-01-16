package application.controller.service.abstraction;

import application.model.entity.User;

import java.util.List;

public interface UserService extends Service<User> {
    @Override
    Integer add(User user);

    List<User> getAll();
}
