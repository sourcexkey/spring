package entity;

public class Ticket {

    private long id;
    private long userId;
    private int seat;
    private long sessionId;

    public Ticket(long id, long userId, int seat, long sessionId) {
        this.id = id;
        this.userId = userId;
        this.seat = seat;
        this.sessionId = sessionId;
    }

    public Ticket() {

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

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
        return sessionId == ticket.sessionId;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + seat;
        result = 31 * result + (int) (sessionId ^ (sessionId >>> 32));
        return result;
    }
}
