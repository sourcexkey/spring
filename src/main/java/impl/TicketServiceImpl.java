package impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import dao.TicketDAO;
import entity.Event;
import entity.Session;
import entity.Ticket;
import entity.User;
import services.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public void bookTicket(User user, int seat, Session session) {
        ticketDAO.bookTicket(user.getId(), seat, session);
    }

    @Override
    public List<Ticket> getTicketsForEvent(Event event, DateTime date) {
        return ticketDAO.getTicketsForEvent(event, date);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        return ticketDAO.getBookedTickets(user);
    }
}
