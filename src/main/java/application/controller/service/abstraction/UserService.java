package application.controller.service.abstraction;

import application.model.entity.User;

import java.util.List;

public interface UserService extends Service {
    /**
     * add new user
     *
     * @param user to add
     * @return id of added user
     */
    Integer add(User user);

    /**
     * get all users
      * @return users list
     */

    List<User> getAll();

    /**
     * get users by parameter
     *
     * @param param search parameter value
     * @param name search parameter name
     * @return found users
     */

    List<User> getBy(Object param, String name);

    /**
     * authorization
     *
     * @param login user login
     * @param password user password
     * @return user info
     */

    User authorization(String login, String password);

    /**
     * get user by id
     * @param userID user id of user to find
     * @return found user
     */

    User getById(int userID);

    /**
     * change user info
     * @param user new user info
     * @return update status
     */

    Boolean changeInfo(User user);

    /**
     * change user password
     *
     * @param userID user id of user whose password to change
     * @param oldPassword old password
     * @param password new password
     * @return status of update
     */

    Boolean changePassword(int userID, String oldPassword, String password);

    /**
     * change user role
     * @param userID user id of user to change
     * @param newRole new role
     * @param oldRole old role
     */

    void changeRole(int userID, String newRole, String oldRole);
}
