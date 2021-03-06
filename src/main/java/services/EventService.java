package services;

import org.joda.time.DateTime;

import entity.Auditorium;
import entity.Event;
import entity.EventRating;

import java.math.BigDecimal;
import java.util.List;

public interface EventService {

    Event create(String name, BigDecimal price, EventRating rating);

    boolean update(Event event);

    void remove(Event event);

    Event getByName(String name);

    List<Event> getAll();

    List<Event> getForDateRange(DateTime from, DateTime to);

    List<Event> getNextEvents(DateTime to);

    Boolean assignAuditorium(Event event, Auditorium auditorium, DateTime date);
}
