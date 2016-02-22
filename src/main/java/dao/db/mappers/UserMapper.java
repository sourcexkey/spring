package dao.db.mappers;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import dao.Constants;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {

    @Override
    public User map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong(Constants.ID_PLACEHOLDER));
        user.setEmail(resultSet.getString(Constants.EMAIL_PLACEHOLDER));
        user.setName(resultSet.getString(Constants.NAME_PLACEHOLDER));
        user.setBirthDay(new DateTime(resultSet.getDate(Constants.DATE_PLACEHOLDER)));
        return user;
    }
}