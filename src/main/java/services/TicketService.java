package services;

import org.joda.time.DateTime;

import entity.Event;
import entity.Session;
import entity.Ticket;
import entity.User;

import java.util.List;

public interface TicketService {

    void bookTicket(User user, int seat, Session session);

    List<Ticket> getTicketsForEvent(Event event, DateTime date);

    List<Ticket> getBookedTickets(User user);
}
