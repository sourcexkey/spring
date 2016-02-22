package aspects;

import dao.aspects.CountersDAO;
import entity.Event;
import entity.Session;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class CounterAspect {
    @Autowired
    private CountersDAO counters;

    @AfterReturning(pointcut = "execution(* services.impl.EventServiceImpl.getByName(..))", returning = "event")
    private void eventGetByName(Event event) {
        counters.incGetByName(event.getId());
    }

    @Before("execution(* services.impl.BookingServiceImpl.getTicketPrice(..)) && args(event,..)")
    private void getTicketPrice(Event event) {
        counters.incGetTicketPrice(event.getId());
    }

    @Before("execution(* services.impl.BookingServiceImpl.bookTicket(..)) && args(..,session)")
    private void bookTicket(Session session) {
        counters.incBookTicket(session.getEventId());
    }

    public Map<String, Integer> getCounterByEventId(long id) {
        return counters.getCounterByEventId(id);
    }
}
