package aspects;

import entity.Event;
import entity.Session;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class CounterAspect {

    public static final String GET_EVENT_BY_NAME_KEY = "getByName";
    public static final String GET_TICKET_PRICE_KEY = "getTicketPrice";
    public static final String BOOK_TICKET_KEY = "bookTicket";
    private Map<Event, Map<String, Integer>> counters = new HashMap<>();

    @AfterReturning(pointcut = "execution(* services.impl.EventServiceImpl.getByName(..))", returning = "event")
    private void eventGetByName(Event event) {
        Map<String, Integer> counter = counters.getOrDefault(event, new HashMap<>());
        Integer amount = counter.getOrDefault(GET_EVENT_BY_NAME_KEY, 0);
        counter.put(GET_EVENT_BY_NAME_KEY, ++amount);
        counters.putIfAbsent(event, counter);
    }

    @Before("execution(* services.impl.BookingServiceImpl.getTicketPrice(..)) && args(event,..)")
    private void getTicketPrice(Event event) {
        Map<String, Integer> counter = counters.getOrDefault(event, new HashMap<>());
        Integer amount = counter.getOrDefault(GET_TICKET_PRICE_KEY, 0);
        counter.put(GET_TICKET_PRICE_KEY, ++amount);
        counters.putIfAbsent(event, counter);
    }

    @Before("execution(* services.impl.BookingServiceImpl.bookTicket(..)) && args(..,session)")
    private void bookTicket(Session session) {
        Event event = session.getEvent();
        Map<String, Integer> counter = counters.getOrDefault(event, new HashMap<>());
        Integer amount = counter.getOrDefault(BOOK_TICKET_KEY, 0);
        counter.put(BOOK_TICKET_KEY, ++amount);
        counters.putIfAbsent(event, counter);
    }

    public Map<Event, Map<String, Integer>> getCounters() {
        return new HashMap<>(counters);
    }
}
