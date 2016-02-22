package dao.aspects;

import java.util.Map;

public interface CountersDAO {

    String GET_BY_NAME_COUNTER = "get_by_name";
    String GET_TICKET_PRICE_COUNTER = "get_ticket_price";
    String BOOK_TICKET_COUNTER = "book_ticket";

    void incGetByName(long id);

    void incGetTicketPrice(long id);

    void incBookTicket(long id);

    Map<String, Integer> getCounterByEventId(long id);
}
