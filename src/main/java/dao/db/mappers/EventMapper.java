package dao.db.mappers;

import dao.Constants;
import entity.Event;
import entity.EventRating;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements ResultSetMapper<Event> {
    @Override
    public Event map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        Event event = new Event();
        event.setId(resultSet.getLong(Constants.ID_PLACEHOLDER));
        event.setName(resultSet.getString(Constants.NAME_PLACEHOLDER));
        event.setPrice(resultSet.getBigDecimal(Constants.PRICE_PLACEHOLDER));
        event.setRating(EventRating.valueOf(resultSet.getString(Constants.RATING_PLACEHOLDER)));
        event.setAuditoriumId(resultSet.getLong(Constants.AUDITORIUM_ID_PLACEHOLDER));
        return event;
    }
}