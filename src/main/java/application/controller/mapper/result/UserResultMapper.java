package application.controller.mapper.result;

import application.controller.mapper.Mapper;
import application.model.entity.User;
import application.model.exception.ModelException;
import application.util.constants.DBParameters;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultMapper implements Mapper<User, ResultSet>, DBParameters {

    @Override
    public User map(ResultSet resultSet) {
        try {
            return new User.Builder()
                    .setId(resultSet.getInt(DB_USER_ID))
                    .setFirstName(resultSet.getString(DB_FIRST_NAME))
                    .setLastName(resultSet.getString(DB_LAST_NAME))
                    .setLogin(resultSet.getString(DB_LOGIN))
                    .setPassword(resultSet.getString(DB_PASSWORD))
                    .setEmail(resultSet.getString(EMAIL))
                    .setPhone(resultSet.getString(DB_PHONE))
                    .setRole(resultSet.getString(DB_ROLE))
                    .build();
        } catch (SQLException e) {
            throw new ModelException(e);
        }

    }
}
