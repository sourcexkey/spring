package entity;

public class Ticket {

    private long id;
    private long userId;
    private int seat;
    private Session session;

    public Ticket(long id, long userId, int seat, Session session) {
        this.id = id;
        this.userId = userId;
        this.seat = seat;
        this.session = session;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Ticket ticket = (Ticket) o;

        if (id != ticket.id) {
            return false;
        }
        if (userId != ticket.userId) {
            return false;
        }
        if (seat != ticket.seat) {
            return false;
        }
        return !(session != null ? !session.equals(ticket.session) : ticket.session != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + seat;
        result = 31 * result + (session != null ? session.hashCode() : 0);
        return result;
    }
}
