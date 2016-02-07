package services;

import entity.Auditorium;
import entity.Event;
import entity.EventRating;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface EventService {
    Event create(String name, BigDecimal price, EventRating rating, Date date);

    void remove();

    Event getByName(String name);

    List<Event> getAll();

    List<Event> getForDateRange(Date from, Date to);

    List<Event> getNextEvents(Date to);

    void assignAuditorium(Event event, Auditorium auditorium, Date date);
}
