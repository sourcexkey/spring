package dao;

import org.joda.time.DateTime;

import entity.Auditorium;
import entity.Event;
import entity.Session;

import java.util.List;

public interface SessionDAO {

    Session create(long  eventId, long auditoriumId, DateTime date, DateTime duration);

    List<Session> getByEvent(Event event);

    List<Session> getByAuditorium(Auditorium auditorium);

    Session getById(long id);

    List<Session> getAll();

    Session getEventAndDate(Event event, DateTime date);
}
