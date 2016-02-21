package entity;

import java.math.BigDecimal;

public class Event {
    private long id;
    private String name;
    private BigDecimal price;
    private EventRating rating;
    private Long auditoriumId;

    public Event(long id, String name, BigDecimal price, EventRating rating,
                 Long auditoriumId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.auditoriumId = auditoriumId;
    }

    public Event() {

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

    public Long getAuditoriumId() {
        return auditoriumId;
    }

    public void setAuditoriumId(Long auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (!name.equals(event.name)) return false;
        if (!price.equals(event.price)) return false;
        if (rating != event.rating) return false;
        return auditoriumId != null ? auditoriumId.equals(event.auditoriumId) : event.auditoriumId == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + rating.hashCode();
        result = 31 * result + (auditoriumId != null ? auditoriumId.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
