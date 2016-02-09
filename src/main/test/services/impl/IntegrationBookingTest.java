package services.impl;

import org.joda.time.DateTime;
import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import entity.Auditorium;
import entity.Event;
import entity.EventRating;
import entity.Session;
import entity.Ticket;
import entity.User;
import services.AuditoriumService;
import services.BookingService;
import services.EventService;
import services.SessionService;
import services.UserService;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:context.xml")
public class IntegrationBookingTest {

    public static final int SEAT = 10;
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private SessionService sessionService;
    private User user;
    private Event event;
    private DateTime eventDate;
    private Session session;

    @Before
    public void setUp() throws Exception {
        user = userService.register("test@epam.com", "Denys", new DateTime(1993, 3, 21, 0, 0));
        event = eventService.create("testEvent", new BigDecimal(100), EventRating.HIGH);
        Auditorium auditorium = auditoriumService.getAuditoriums().get(0);
        event.setAuditorium(auditorium);
        eventDate = DateTime.now().withHourOfDay(18);
        session =
                sessionService
                        .create(event, auditorium, eventDate, new DateTime(0).withHourOfDay(2));
    }

    @Test
    public void testGetTicketPrice() throws Exception {
        assertEquals(new BigDecimal(120.00).setScale(2),
                     bookingService.getTicketPrice(event, eventDate, 10, user)
                             .setScale(2, BigDecimal.ROUND_DOWN));
        assertEquals(new BigDecimal(240.00).setScale(2),
                     bookingService.getTicketPrice(event, eventDate, 8, user)
                             .setScale(2, BigDecimal.ROUND_DOWN));
        user.setBirthDay(DateTime.now());
        assertEquals(new BigDecimal(114.00).setScale(2),
                     bookingService.getTicketPrice(event, eventDate, 10, user)
                             .setScale(2, BigDecimal.ROUND_DOWN));
    }

    @Test
    public void testBookTicket() throws Exception {
        bookingService.bookTicket(user, SEAT, session);
        List<Ticket> bookedTickets = bookingService.getBookedTickets(user);
        bookedTickets.forEach(t -> {
            assertEquals(user.getId(), t.getUserId());
            assertEquals(SEAT, t.getSeat());
            assertEquals(session, t.getSession());
        });
    }

    @Test
    public void testGetBookedTickets() throws Exception {

    }
}