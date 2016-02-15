package services.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import dao.EventDAO;
import entity.Auditorium;
import entity.Event;
import entity.EventRating;
import services.EventService;
import services.SessionService;

import java.math.BigDecimal;
import java.util.List;

public class EventServiceImpl implements EventService {

    public static final int SESSION_DURATION = 1;
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    private SessionService sessionService;

    @Override
    public Event create(String name, BigDecimal price, EventRating rating) {
        return eventDAO.create(name, price, rating);
    }

    @Override
    public boolean update(Event event) {
        return eventDAO.update(event);
    }

    @Override
    public void remove(Event event) {
        eventDAO.remove(event);
    }

    @Override
    public Event getByName(String name) {
        return eventDAO.getByName(name);
    }

    @Override
    public List<Event> getAll() {
        return eventDAO.getAll();
    }

    @Override
    public List<Event> getForDateRange(DateTime from, DateTime to) {
        return eventDAO.getForDateRange(from, to);
    }

    @Override
    public List<Event> getNextEvents(DateTime to) {
        return eventDAO.getNextEvents(to);
    }

    @Override
    public Boolean assignAuditorium(Event event, Auditorium auditorium, DateTime date) {
        if (isAuditoriumFree(auditorium, date)) {
            event.setAuditorium(auditorium);
            return true;
        } else {
            return false;
        }
    }

    private boolean isAuditoriumFree(Auditorium auditorium, DateTime date) {
        return !sessionService.getByAuditorium(auditorium).stream().anyMatch(s -> {
            DateTime sessionDateBefore = new DateTime(s.getDate()).plusHours(SESSION_DURATION);
            DateTime sessionDateAfter = new DateTime(s.getDate()).minusHours(SESSION_DURATION);
            return !sessionDateBefore.isBefore(date)
                   && !sessionDateAfter.isAfter(date);
        });
    }
}
