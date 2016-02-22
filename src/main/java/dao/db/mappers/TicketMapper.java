package dao.db.mappers;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import dao.Constants;
import entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper implements ResultSetMapper<Ticket> {

    @Override
    public Ticket map(int index, ResultSet resultSet, StatementContext ctx) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong(Constants.ID_PLACEHOLDER));
        ticket.setUserId(resultSet.getLong(Constants.USER_ID_PLACEHOLDER));
        ticket.setSeat(resultSet.getInt(Constants.SEAT_PLACEHOLDER));
        ticket.setSessionId(resultSet.getLong(Constants.SESSION_ID_PLACEHOLDER));
        return ticket;
    }
}