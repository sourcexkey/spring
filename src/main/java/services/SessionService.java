package services;

import org.joda.time.DateTime;

import entity.Auditorium;
import entity.Event;
import entity.Session;

import java.util.List;

public interface SessionService {

    Session create(Event event, Auditorium auditorium, DateTime date, DateTime duration);

    List<Session> getByEvent(Event event);

    List<Session> getByAuditorium(Auditorium auditorium);

    Session getById(long id);

    Session getByEventAndDate(Event event, DateTime date);
}
