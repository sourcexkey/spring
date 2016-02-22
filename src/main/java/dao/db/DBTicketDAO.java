package dao.db;

import org.joda.time.DateTime;
import org.skife.jdbi.v2.GeneratedKeys;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.IDBI;
import org.skife.jdbi.v2.Update;
import org.skife.jdbi.v2.spring.DBIUtil;
import org.skife.jdbi.v2.util.LongMapper;
import org.springframework.beans.factory.annotation.Autowired;

import dao.Constants;
import dao.TicketDAO;
import dao.db.mappers.TicketMapper;
import entity.Event;
import entity.Ticket;
import entity.User;

import java.util.List;

public class DBTicketDAO implements TicketDAO {

    public static final TicketMapper TICKET_MAPPER = new TicketMapper();
    private static final String
            GET_TICKET_BY_EVENT =
            "SELECT * FROM tickets AS t INNER JOIN sessions AS s ON t.session_id=s.id WHERE event_id=:event_id AND date=:date";
    private static final String
            GET_TICKETS_BY_USER =
            "SELECT * FROM tickets WHERE user_id=:user_id";
    private static final String
            INSERT_TICKET =
            "INSERT INTO tickets VALUES(default,:user_id,:seat,:session_id)";


    @Autowired
    private IDBI dbi;

    @Override
    public Ticket bookTicket(long userId, int seat, long sessionId) {
        Handle h = DBIUtil.getHandle(dbi);
        Update stmt = h.createStatement(INSERT_TICKET)
                .bind(Constants.USER_ID_PLACEHOLDER, userId)
                .bind(Constants.SEAT_PLACEHOLDER, seat)
                .bind(Constants.SESSION_ID_PLACEHOLDER, sessionId);
        GeneratedKeys<Long> keys = stmt.executeAndReturnGeneratedKeys(LongMapper.FIRST);
        return new Ticket(keys.first(), userId, seat, sessionId);
    }

    @Override
    public List<Ticket> getTicketsForEvent(Event event, DateTime date) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_TICKET_BY_EVENT)
                .map(TICKET_MAPPER)
                .bind(Constants.EVENT_ID_PLACEHOLDER, event.getId())
                .bind(Constants.DATE_PLACEHOLDER, date.toDate())
                .list();
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        Handle h = DBIUtil.getHandle(dbi);
        return h.createQuery(GET_TICKETS_BY_USER)
                .map(TICKET_MAPPER)
                .bind(Constants.USER_ID_PLACEHOLDER, user.getId())
                .list();
    }

}
