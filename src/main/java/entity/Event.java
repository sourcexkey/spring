package entity;

import java.math.BigDecimal;
import java.util.Date;

public class Event {
    private String name;
    private BigDecimal price;
    private EventRating rating;
    private Date date;
    private Auditorium auditorium;

    public Event(String name, BigDecimal price, EventRating rating, Date date, Auditorium auditorium) {
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }
}
