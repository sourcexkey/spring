package entity;

import java.util.Set;

public class Auditorium {
    private String name;
    private int  seats;
    private Set<Integer> vipSeats;

    public Auditorium(String name, int seats, Set<Integer> vipSeats) {
        this.name = name;
        this.seats = seats;
        this.vipSeats = vipSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Set<Integer> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(Set<Integer> vipSeats) {
        this.vipSeats = vipSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Auditorium that = (Auditorium) o;

        if (seats != that.seats) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        return !(vipSeats != null ? !vipSeats.equals(that.vipSeats) : that.vipSeats != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + seats;
        result = 31 * result + (vipSeats != null ? vipSeats.hashCode() : 0);
        return result;
    }
}
