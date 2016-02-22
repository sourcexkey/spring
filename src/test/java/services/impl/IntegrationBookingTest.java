package services.impl;

import org.joda.time.DateTime;
import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import aspects.CounterAspect;
import aspects.DiscountAspect;
import dao.aspects.CountersDAO;
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
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:sprint-contexts/root.xml")
public class IntegrationBookingTest {

    public static final int SEAT = 10;
    public static final BigDecimal TICKET_PRICE = new BigDecimal(120.00).setScale(2);
    public static final BigDecimal TICKET_PRICE_VIP = new BigDecimal(240.00).setScale(2);
    public static final BigDecimal TICKET_PRICE_DISCOUNT = new BigDecimal(114.00).setScale(2);
    public static final int NON_VIP_SEAT = 10;
    public static final int VIP_SEAT = 8;
    public static final BigDecimal ZERO_PRICE = BigDecimal.ZERO.setScale(2);
    public static final String TEST_EVENT_NAME = "testEvent";
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private CounterAspect counterAspect;

    @Autowired
    private DiscountAspect discountAspect;

    @Autowired
    private AuditoriumService auditoriumService;

    @Autowired
    private SessionService sessionService;
    private Event event;
    private static User user;
    private DateTime eventDate;
    private Session session;

    @Before
    public void setUp() throws Exception {
        if (user == null) {
            user = userService.register("test@epam.com", "Denys", new DateTime(1993, 3, 21, 0, 0));
        }
        event = eventService.create(TEST_EVENT_NAME, new BigDecimal(100), EventRating.HIGH);
        Auditorium auditorium = auditoriumService.getAuditoriums().get(0);
        event.setAuditoriumId(auditorium.getId());
        eventDate = DateTime.now().withHourOfDay(18);
        session = sessionService
                .create(event, auditorium, eventDate, new DateTime(0).withHourOfDay(2));
    }

    @Test
    public void testBookTicket() throws Exception {
        bookingService.bookTicket(user, SEAT, session);
        List<Ticket> bookedTickets = bookingService.getBookedTickets(user);
        bookedTickets.forEach(t -> {
            assertEquals(user.getId(), t.getUserId());
            assertEquals(SEAT, t.getSeat());
            assertEquals(session.getId(), t.getSessionId());
        });
    }

    @Test
    public void testGetTicketPrice() throws Exception {
        BigDecimal priceNonVip = bookingService.getTicketPrice(event, eventDate, NON_VIP_SEAT, user)
                .setScale(2, BigDecimal.ROUND_DOWN);
        assertTrue(priceNonVip.equals(TICKET_PRICE) || priceNonVip.equals(ZERO_PRICE));
        BigDecimal priceVip = bookingService.getTicketPrice(event, eventDate, VIP_SEAT, user)
                .setScale(2, BigDecimal.ROUND_DOWN);
        assertTrue(priceVip.equals(TICKET_PRICE_VIP) || priceVip.equals(ZERO_PRICE));
        user.setBirthDay(DateTime.now());
        priceNonVip = bookingService.getTicketPrice(event, eventDate, NON_VIP_SEAT, user)
                .setScale(2, BigDecimal.ROUND_DOWN);
        assertTrue(priceNonVip.equals(TICKET_PRICE_DISCOUNT) || priceNonVip.equals(ZERO_PRICE));
    }

    @Test
    public void testDiscountAspect() {
        eventService.getByName(TEST_EVENT_NAME);
        Map<String, Integer> eventCounter = counterAspect.getCounterByEventId(event.getId());
        assertEquals(Integer.valueOf(1), eventCounter.get(CountersDAO.GET_BY_NAME_COUNTER));
        eventService.getByName(TEST_EVENT_NAME);
        eventCounter = counterAspect.getCounterByEventId(event.getId());
        assertEquals(Integer.valueOf(2), eventCounter.get(CountersDAO.GET_BY_NAME_COUNTER));
        eventService.getByName(TEST_EVENT_NAME);
        eventCounter = counterAspect.getCounterByEventId(event.getId());
        assertEquals(Integer.valueOf(3), eventCounter.get(CountersDAO.GET_BY_NAME_COUNTER));
        eventService.getByName(TEST_EVENT_NAME);
    }

}