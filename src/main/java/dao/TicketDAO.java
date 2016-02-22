package dao;

import org.joda.time.DateTime;

import entity.Event;
import entity.Session;
import entity.Ticket;
import entity.User;

import java.util.List;

/**
 * @author Denys_Uzhvii
 */
public interface TicketDAO {

    Ticket bookTicket(long userId, int seat, long sessionId);

    List<Ticket> getTicketsForEvent(Event event, DateTime date);

    List<Ticket> getBookedTickets(User user);
}
