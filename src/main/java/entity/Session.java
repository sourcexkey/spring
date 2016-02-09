package entity;

import org.joda.time.DateTime;

public class Session {
    private long id;
    private Event event;
    private Auditorium auditorium;
    private DateTime date;
    private DateTime duration;

    public Session(long id, Event event, Auditorium auditorium, DateTime date,
                   DateTime sessionDuration) {
        this.id = id;
        this.event = event;
        this.auditorium = auditorium;
        this.date = date;
        this.duration = sessionDuration;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Session session = (Session) o;

        if (id != session.id) {
            return false;
        }
        if (event != null ? !event.equals(session.event) : session.event != null) {
            return false;
        }
        if (auditorium != null ? !auditorium.equals(session.auditorium)
                               : session.auditorium != null) {
            return false;
        }
        if (date != null ? !date.equals(session.date) : session.date != null) {
            return false;
        }
        return !(duration != null ? !duration.equals(session.duration) : session.duration != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (event != null ? event.hashCode() : 0);
        result = 31 * result + (auditorium != null ? auditorium.hashCode() : 0);
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
