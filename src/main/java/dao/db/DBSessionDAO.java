package dao.db;

import dao.SessionDAO;
import dao.db.mappers.EventMapper;
import dao.db.mappers.SessionMapper;
import entity.Auditorium;
import entity.Event;
import entity.Session;
import org.h2.command.dml.Select;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.GeneratedKeys;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.IDBI;
import org.skife.jdbi.v2.Update;
import org.skife.jdbi.v2.spring.DBIUtil;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.util.LongMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class DBSessionDAO implements SessionDAO {
    public static final SessionMapper SESSION_MAPPER = new SessionMapper();
    private static final String GET_SESSION_BY_EVENT = "SELECT * FROM sessions WHERE event_id=:event_id";
    private static final String GET_SESSION_BY_AUDITORIUM = "SELECT * FROM sessions WHERE auditorium_id=:auditorium_id";
    private static final String GET_SESSION_BY_ID = "SELECT * FROM sessions WHERE id=:id";
    private static final String GET_BY_EVENT_AND_DATE = "SELECT * FROM sessions WHERE event_id=:event_id AND date=:date";
    private static final String GET_ALL_SESSIONS = "SELECT * FROM sessions";

    private static final String INSERT_SESSION = "INSERT INTO sessions VALUES(default,:event_id,:auditorium_id,:date,:duration)";


    @Autowired
    private IDBI dbi;

    @Override
    public Session create(long eventId, long auditoriumId, DateTime date, DateTime duration) {
        Handle h = DBIUtil.getHandle(dbi);
        Update stmt = h.createStatement(INSERT_SESSION)
                .bind(Constants.EVENT_ID_PLACEHOLDER, eventId)
                .bind(Constants.AUDITORIUM_ID_PLACEHOLDER, auditoriumId)
                .bind(Constants.DATE_PLACEHOLDER, date.toDate())
                .bind(Constants.DURATION_PLACEHOLDER, duration.toDate());
        GeneratedKeys<Long> keys = stmt.executeAndReturnGeneratedKeys(LongMapper.FIRST);
        return new Session(keys.first(), eventId, auditoriumId, date, duration);
    }

    @Override
    public List<Session> getByEvent(Event event) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_SESSION_BY_EVENT)
                .map(SESSION_MAPPER)
                .bind(Constants.EVENT_ID_PLACEHOLDER, event.getId())
                .list();
    }

    @Override
    public List<Session> getByAuditorium(Auditorium auditorium) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_SESSION_BY_AUDITORIUM)
                .map(SESSION_MAPPER)
                .bind(Constants.AUDITORIUM_ID_PLACEHOLDER, auditorium.getId())
                .list();
    }

    @Override
    public Session getById(long id) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_SESSION_BY_ID)
                .map(SESSION_MAPPER)
                .bind(Constants.ID_PLACEHOLDER, id)
                .first();
    }

    @Override
    public List<Session> getAll() {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_ALL_SESSIONS)
                .map(SESSION_MAPPER)
                .list();
    }


    @Override
    public Session getEventAndDate(Event event, DateTime date) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_BY_EVENT_AND_DATE)
                .map(SESSION_MAPPER)
                .bind(Constants.EVENT_ID_PLACEHOLDER, event.getId())
                .bind(Constants.DATE_PLACEHOLDER, date.toDate())
                .first();
    }

}