package dao.db.mappers;

import dao.db.Constants;
import entity.Event;
import entity.Session;
import org.joda.time.DateTime;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionMapper implements ResultSetMapper<Session> {
    @Override
    public Session map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        Session session = new Session();
        session.setId(resultSet.getLong(Constants.ID_PLACEHOLDER));
        session.setAuditoriumId(resultSet.getLong(Constants.AUDITORIUM_ID_PLACEHOLDER));
        session.setEventId(resultSet.getLong(Constants.EVENT_ID_PLACEHOLDER));
        session.setDate(new DateTime(resultSet.getDate(Constants.DATE_PLACEHOLDER)));
        return session;
    }
}