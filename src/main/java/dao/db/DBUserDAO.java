package dao.db;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.GeneratedKeys;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.IDBI;
import org.skife.jdbi.v2.Update;
import org.skife.jdbi.v2.spring.DBIUtil;
import org.skife.jdbi.v2.util.LongMapper;
import org.springframework.beans.factory.annotation.Autowired;

import dao.Constants;
import dao.UserDAO;
import dao.db.mappers.UserMapper;
import entity.User;

import java.util.List;

public class DBUserDAO implements UserDAO {

    public static final UserMapper USER_MAPPER = new UserMapper();
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id=:id";
    private static final String GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email=:email";
    private static final String GET_USER_BY_NAME = "SELECT * FROM users WHERE name=:name";
    private static final String INSERT_USER =
            "INSERT INTO users VALUES(default,:email,:name,:birthday)";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=:id";


    @Autowired
    private IDBI dbi;

    @Override
    public User register(String email, String name, DateTime birthDay) {
        Handle h = DBIUtil.getHandle(dbi);
        Update stmt = h.createStatement(INSERT_USER)
                .bind(Constants.EMAIL_PLACEHOLDER, email)
                .bind(Constants.NAME_PLACEHOLDER, name)
                .bind(Constants.BIRTHDAY_PLACEHOLDER, birthDay.toDate());
        GeneratedKeys<Long> keys = stmt.executeAndReturnGeneratedKeys(LongMapper.FIRST);
        return new User(keys.first(), email, name, birthDay);
    }

    @Override
    public User getById(long id) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_USER_BY_ID)
                .map(USER_MAPPER)
                .bind(Constants.ID_PLACEHOLDER, id)
                .first();
    }

    @Override
    public void remove(User user) {
        Handle h = DBIUtil.getHandle(dbi);
        h.createStatement(DELETE_USER)
                .bind(Constants.ID_PLACEHOLDER, user.getId())
                .execute();
    }

    @Override
    public User getUserByEmail(String email) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_USER_BY_EMAIL)
                .map(USER_MAPPER)
                .bind(Constants.EMAIL_PLACEHOLDER, email)
                .first();
    }

    @Override
    public List<User> getUsersByName(String name) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_USER_BY_NAME)
                .map(USER_MAPPER)
                .bind(Constants.NAME_PLACEHOLDER, name)
                .list();
    }
}
