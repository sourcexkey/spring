package dao.aspects.db;

import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.IDBI;
import org.skife.jdbi.v2.spring.DBIUtil;
import org.springframework.beans.factory.annotation.Autowired;

import dao.Constants;
import dao.aspects.CountersDAO;
import dao.db.mappers.TicketMapper;

import java.util.HashMap;
import java.util.Map;

public class DBCountersDAO implements CountersDAO {

    public static final TicketMapper TICKET_MAPPER = new TicketMapper();
    private static final String
            GET_COUNTERS_BY_EVENT =
            "SELECT * FROM event_counter WHERE event_id=:event_id";
    private static final String
            INCREMENT_COUNTER =
            "UPDATE event_counter SET {counter}={counter} + 1 WHERE event_id=:event_id";
    private static final String
            INSERT_COUNTER =
            "INSERT INTO event_counter VALUES(:event_id,default,default,default)";
    public static final String
            CHECK_COUNTER_EXISTS =
            "SELECT * FROM event_counter WHERE event_id=:event_id";


    @Autowired
    private IDBI dbi;

    @Override
    public void incGetByName(long id) {
        incCounter(id, GET_BY_NAME_COUNTER);
    }

    @Override
    public void incGetTicketPrice(long id) {
        incCounter(id, GET_TICKET_PRICE_COUNTER);
    }

    @Override
    public void incBookTicket(long id) {
        incCounter(id, BOOK_TICKET_COUNTER);
    }

    private void incCounter(long id, String bookTicketCounter) {
        Handle h = DBIUtil.getHandle(dbi);
        checkIsCounterExists(id, h);
        h.createStatement(INCREMENT_COUNTER.replace("{counter}", bookTicketCounter))
                .bind(Constants.EVENT_ID_PLACEHOLDER, id)
                .execute();
    }

    private void checkIsCounterExists(long id, Handle h) {
        if (h.createQuery(CHECK_COUNTER_EXISTS)
                    .bind(Constants.EVENT_ID_PLACEHOLDER, id).first() == null) {
            h.createStatement(INSERT_COUNTER).bind(Constants.EVENT_ID_PLACEHOLDER, id).execute();
        }
    }

    @Override
    public Map<String, Integer> getCounterByEventId(long id) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_COUNTERS_BY_EVENT)
                .bind(Constants.EVENT_ID_PLACEHOLDER, id)
                .map((i, resultSet, statementContext) ->
                     {
                         Map<String, Integer> counter = new HashMap<>();
                         counter.put(GET_BY_NAME_COUNTER, resultSet.getInt(GET_BY_NAME_COUNTER));
                         counter.put(GET_TICKET_PRICE_COUNTER,
                                     resultSet.getInt(GET_TICKET_PRICE_COUNTER));
                         counter.put(BOOK_TICKET_COUNTER, resultSet.getInt(BOOK_TICKET_COUNTER));
                         return counter;
                     }).first();
    }
}
