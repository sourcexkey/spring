package services;

import entity.Ticket;
import entity.User;

import java.util.List;

public interface UserService {
    void register(User user);

    void remove(User user);

    User getById(long id);

    User getUserByEmail(String email);

    User getUsersByName(String name);

    List<Ticket> getBookedTickets(User user);
}
