package impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import dao.UserDAO;
import entity.User;
import services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User register(String email, String name, DateTime birthDay) {
        return userDAO.register(email, name, birthDay);
    }

    @Override
    public void remove(User user) {
        userDAO.remove(user);
    }

    @Override
    public User getById(long id) {
        return userDAO.getById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userDAO.getUsersByName(name);
    }
}
