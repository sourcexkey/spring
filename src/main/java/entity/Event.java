package entity;

import java.math.BigDecimal;

import org.joda.time.DateTime;

public class Event {

    private String name;
    private BigDecimal price;
    private EventRating rating;
    private Auditorium auditorium;

    public Event(String name, BigDecimal price, EventRating rating,
                 Auditorium auditorium) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.auditorium = auditorium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
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

        Event event = (Event) o;

        if (!name.equals(event.name)) {
            return false;
        }
        if (!price.equals(event.price)) {
            return false;
        }
        if (rating != event.rating) {
            return false;
        }
        return !(auditorium != null ? !auditorium.equals(event.auditorium)
                                    : event.auditorium != null);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + rating.hashCode();
        result = 31 * result + (auditorium != null ? auditorium.hashCode() : 0);
        return result;
    }
}
