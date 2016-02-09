package dao.impl;

import com.sun.tracing.dtrace.Attributes;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.EventDAO;
import dao.SessionDAO;
import entity.Event;
import entity.EventRating;
import entity.Session;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventMapDAO implements EventDAO {

    private static Map<String, Event> eventRepo = new HashMap<>();

    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public Event create(String name, BigDecimal price, EventRating rating) {
        Event event = new Event(name, price, rating, null);
        eventRepo.put(name, event);
        return event;
    }

    @Override
    public boolean update(Event event) {
        String name = event.getName();
        if (eventRepo.containsKey(name)) {
            eventRepo.put(name, event);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void remove(Event event) {
        eventRepo.remove(event.getName());
    }

    @Override
    public Event getByName(String name) {
        return eventRepo.get(name);
    }

    @Override
    public List<Event> getAll() {
        return eventRepo.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<Event> getForDateRange(DateTime from, DateTime to) {
        return sessionDAO.getAll().stream()
                .filter(s -> s.getDate().isAfter(from) && s.getDate().isBefore(to))
                .map(Session::getEvent)
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> getNextEvents(DateTime to) {
        return sessionDAO.getAll().stream().filter(s -> s.getDate().isAfter(to))
                .map(Session::getEvent)
                .collect(Collectors.toList());
    }
}
