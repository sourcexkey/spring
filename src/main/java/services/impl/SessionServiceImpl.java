package services.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import dao.SessionDAO;
import entity.Auditorium;
import entity.Event;
import entity.Session;
import services.SessionService;

import java.util.List;

public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionDAO sessionDAO;

    @Override
    public Session create(Event event, Auditorium auditorium, DateTime date, DateTime duration) {
        return sessionDAO.create(event, auditorium, date, duration);
    }

    @Override
    public List<Session> getByEvent(Event event) {
        return sessionDAO.getByEvent(event);
    }

    @Override
    public List<Session> getByAuditorium(Auditorium auditorium) {
        return sessionDAO.getByAuditorium(auditorium);
    }

    @Override
    public Session getById(long id) {
        return sessionDAO.getById(id);
    }

    @Override
    public Session getByEventAndDate(Event event, DateTime date) {
        return sessionDAO.getEventAndDate(event, date);
    }
}
