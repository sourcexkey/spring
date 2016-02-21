package dao.map;

import org.joda.time.DateTime;

import dao.UserDAO;
import entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserMapDAO implements UserDAO {

    private Map<Long, User> userRepo = new HashMap<>();
    private volatile long id = 0;

    @Override
    public User register(String email, String name, DateTime birthDay) {
        if (userRepo.values().stream().anyMatch(u -> u.getEmail().equals(email))) {
            return null;
        }
        User u = new User(id, email, name, birthDay);
        userRepo.put(id++, u);
        return u;
    }

    @Override
    public void remove(User user) {
        userRepo.remove(user.getId());
    }

    @Override
    public User getById(long id) {
        return userRepo.get(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.values().stream().filter(u -> u.getEmail().equals(email)).findFirst().get();
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userRepo.values().stream().filter(u -> u.getName().equals(name))
                .collect(Collectors.toList());
    }
}
