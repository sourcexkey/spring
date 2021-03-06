package services;

import org.joda.time.DateTime;

import entity.User;

import java.util.List;

public interface UserService {

    User register(String email, String name, DateTime birthDay);

    void remove(User user);

    User getById(long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

}
