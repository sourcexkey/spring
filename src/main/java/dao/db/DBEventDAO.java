package dao.db;

import dao.EventDAO;
import dao.db.mappers.EventMapper;
import entity.Event;
import entity.EventRating;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.GeneratedKeys;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.IDBI;
import org.skife.jdbi.v2.Update;
import org.skife.jdbi.v2.spring.DBIUtil;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.util.LongMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

public class DBEventDAO implements EventDAO {
    private final ResultSetMapper<Event> EVENT_MAPPER = new EventMapper();
    private static final String SELECT_EVENT_BY_NAME = "SELECT * FROM events WHERE name=:name";
    private static final String SELECT_ALL_EVENTS = "SELECT * FROM events";
    private static final String SELECT_EVENT_FOR_DATE_RANGE = "SELECT * FROM events AS e INNER JOIN sessions AS s ON t.id=s.evet_id WHERE date BETWEEN :from AND :to";
    private static final String SELECT_EVENT_FROM_DATE = "SELECT * FROM events AS e INNER JOIN sessions AS s ON t.id=s.evet_id WHERE date>=:from ";
    private static final String INSERT_EVENT = "INSERT INTO events VALUES(default,:name,:price,:rating,null)";
    private static final String UPDATE_EVENT = "UPDATE events SET name=:name, price=:price, rating=:rating, auditorium=:auditorium WHERE id=:id";
    private static final String DELETE_EVENT = "DELETE FROM events WHERE id=:id";

    @Autowired
    private IDBI dbi;

    @Override
    public Event create(String name, BigDecimal price, EventRating rating) {
        Handle h = DBIUtil.getHandle(dbi);
        Update stmt = h.createStatement(INSERT_EVENT)
                .bind(Constants.NAME_PLACEHOLDER, name)
                .bind(Constants.PRICE_PLACEHOLDER, price)
                .bind(Constants.RATING_PLACEHOLDER, rating);
        GeneratedKeys<Long> keys = stmt.executeAndReturnGeneratedKeys(LongMapper.FIRST);
        Event event = new Event(keys.first(), name, price, rating, null);
        return event;
    }

    @Override
    public boolean update(Event event) {
        Handle h = DBIUtil.getHandle(dbi);
        Update stmt = h.createStatement(UPDATE_EVENT)
                .bind(Constants.NAME_PLACEHOLDER, event.getName())
                .bind(Constants.PRICE_PLACEHOLDER, event.getPrice())
                .bind(Constants.RATING_PLACEHOLDER, event.getRating())
                .bind(Constants.AUDITORIUM_PLACEHOLDER, event.getAuditoriumId())
                .bind(Constants.ID_PLACEHOLDER, event.getId());
        return stmt.execute() == 1;
    }

    @Override
    public void remove(Event event) {
        Handle h = DBIUtil.getHandle(dbi);
        Update stmt = h.createStatement(DELETE_EVENT)
                .bind(Constants.ID_PLACEHOLDER, event.getId());
        stmt.execute();
    }

    @Override
    public Event getByName(String name) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(SELECT_EVENT_BY_NAME)
                .map(EVENT_MAPPER)
                .bind(Constants.NAME_PLACEHOLDER, name)
                .first();
    }

    @Override
    public List<Event> getAll() {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(SELECT_ALL_EVENTS)
                .map(EVENT_MAPPER)
                .list();
    }

    @Override
    public List<Event> getForDateRange(DateTime from, DateTime to) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(SELECT_EVENT_FOR_DATE_RANGE)
                .map(EVENT_MAPPER)
                .bind(Constants.DATE_FROM_PLACEHOLDER, from)
                .bind(Constants.DATE_TO_PLACEHOLDER, to)
                .list();
    }

    @Override
    public List<Event> getNextEvents(DateTime from) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(SELECT_EVENT_FROM_DATE)
                .map(EVENT_MAPPER)
                .bind(Constants.DATE_FROM_PLACEHOLDER, from)
                .list();
    }
}
