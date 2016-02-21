package entity;

import org.joda.time.DateTime;

public class Session {
    private long id;
    private long eventId;
    private long auditoriumId;
    private DateTime date;
    private DateTime duration;

    public Session(long id, long eventId, long auditoriumId, DateTime date, DateTime duration) {
        this.id = id;
        this.eventId = eventId;
        this.auditoriumId = auditoriumId;
        this.date = date;
        this.duration = duration;
    }

    public Session() {

    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public DateTime getDuration() {
        return duration;
    }

    public void setDuration(DateTime duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (id != session.id) return false;
        if (eventId != session.eventId) return false;
        if (auditoriumId != session.auditoriumId) return false;
        if (date != null ? !date.equals(session.date) : session.date != null) return false;
        return duration != null ? duration.equals(session.duration) : session.duration == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (eventId ^ (eventId >>> 32));
        result = 31 * result + (int) (auditoriumId ^ (auditoriumId >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
