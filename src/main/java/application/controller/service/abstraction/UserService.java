package application.controller.service.abstraction;

import application.model.entity.User;

public interface UserService extends Service<Boolean, User> {
    @Override
    Boolean add(User user);
}
