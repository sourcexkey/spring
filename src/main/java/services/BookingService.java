package services;

import entity.Event;
import entity.Ticket;
import entity.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface BookingService {
    BigDecimal getTicketPrice(Event event, Date date, Date time, int seat, User user);

    void bookTicket(User user, Ticket ticket);

    List<Ticket> getTicketsForEvent(Event event, Date date);
}
