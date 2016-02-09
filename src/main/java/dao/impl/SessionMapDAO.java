package dao.impl;

import org.joda.time.DateTime;

import dao.SessionDAO;
import entity.Auditorium;
import entity.Event;
import entity.Session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SessionMapDAO implements SessionDAO {

    private Map<Long, Session> sessionRepo = new HashMap<>();
    private volatile long id = 0;

    @Override
    public Session create(Event event, Auditorium auditorium, DateTime date, DateTime duration) {
        Session s = new Session(id, event, auditorium, date, duration);
        sessionRepo.put(id++, s);
        return s;
    }

    @Override
    public List<Session> getByEvent(Event event) {
        return sessionRepo.values().stream().filter(s -> s.getEvent().equals(event)).collect(
                Collectors.toList());
    }

    @Override
    public List<Session> getByAuditorium(Auditorium auditorium) {
        return sessionRepo.values().stream().filter(s -> s.getAuditorium().equals(auditorium))
                .collect(
                        Collectors.toList());
    }

    @Override
    public Session getById(long id) {
        return sessionRepo.get(id);
    }

    @Override
    public List<Session> getAll() {
        return sessionRepo.values().stream().collect(Collectors.toList());
    }

    @Override
    public Session getEventAndDate(Event event, DateTime date) {
        return sessionRepo.values().stream()
                .filter(s -> s.getEvent().equals(event) && s.getDate().equals(date)).findFirst()
                .get();
    }
}
