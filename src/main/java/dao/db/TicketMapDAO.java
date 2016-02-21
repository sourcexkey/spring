package dao.db;

import dao.TicketDAO;
import entity.Event;
import entity.Session;
import entity.Ticket;
import entity.User;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketMapDAO implements TicketDAO {

    private Map<Long, Ticket> ticketRepo = new HashMap<>();
    private volatile long id = 0;

    @Override
    public Ticket bookTicket(long userId, int seat, Session session) {
        Ticket t = new Ticket(id, userId, seat, session);
        ticketRepo.put(id++, t);
        return t;
    }

    @Override
    public List<Ticket> getTicketsForEvent(Event event, DateTime date) {
        return ticketRepo.values().stream().filter(t -> t.getSession().equals(event))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        return ticketRepo.values().stream().filter(t -> t.getUserId() == (user.getId()))
                .collect(Collectors.toList());
    }
}
