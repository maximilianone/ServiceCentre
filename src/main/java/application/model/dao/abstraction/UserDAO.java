package application.model.dao.abstraction;

import application.model.entity.User;

import java.util.List;

public interface UserDAO extends DAO<User> {
    /**
     * create new user
     * @param user user to create
     * @return id of created user
     */
    @Override
    Integer create(User user);

    /**
     * get all users
     * @return list of all users
     */

    List<User> getAll();

    /**
     * get group by parameter
     * @param param parameter value
     * @param name parameter name
     * @return users group list
     */

    List<User> getGroupBy(Object param, String name);

    /**
     * check authorization values
     * @param login user login
     * @param password user password
     * @return information about authorized user
     */

    User authorization(String login, String password);

    /**
     * get user by id
     * @param userID user id of user to find
     * @return found user
     */

    User getById(int userID);

    /**
     * check old password before changing
     * @param userID user id of user whose password to check
     * @param password user password
     * @return true if password is correct
     */

    boolean checkPassword(int userID, String password);

    /**
     * check old role before changing
     * @param userID user id of user whose role to check
     * @param role user role
     * @return true if role didn't change
     */

    boolean checkRole(int userID, String role);
}
