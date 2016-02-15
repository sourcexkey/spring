package services.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import entity.Event;
import entity.EventRating;
import entity.Session;
import entity.Ticket;
import entity.User;
import services.BookingService;
import services.DiscountService;
import services.SessionService;
import services.TicketService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class BookingServiceImpl implements BookingService {

    private final Map<EventRating, Float> ticketPriceByRating;
    private final BigDecimal vipSeatCost;

    @Autowired
    private TicketService ticketService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private DiscountService discountService;

    public BookingServiceImpl(BigDecimal vipSeatCost,
                              Map<EventRating, Float> ticketPriceByRating) {
        this.vipSeatCost = vipSeatCost;
        this.ticketPriceByRating = ticketPriceByRating;
    }

    @Override
    public BigDecimal getTicketPrice(Event event, DateTime date, int seat, User user) {
        BigDecimal price = event.getPrice();
        price = price.multiply(BigDecimal.valueOf(ticketPriceByRating.get(event.getRating())));
        if (event.getAuditorium().getVipSeats().contains(seat)) {
            price = price.multiply(vipSeatCost);
        }
        float discount =
                discountService.getDiscount(user, sessionService.getByEventAndDate(event, date));
        if (discount > 0.) {
            price = price.multiply(new BigDecimal(1).subtract(BigDecimal.valueOf(discount)));
        }
        return price;
    }

    @Override
    public void bookTicket(User user, int seat, Session session) {
        ticketService.bookTicket(user, seat, session);
    }

    @Override
    public List<Ticket> getTicketsForEvent(Event event, DateTime date) {
        return ticketService.getTicketsForEvent(event, date);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        return ticketService.getBookedTickets(user);
    }
}
