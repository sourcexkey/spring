package entity;

import java.util.List;

public class Auditorium {
    private String name;
    private int  seats;
    private List<Integer> vipSeats;

    public Auditorium(String name, int seats, List<Integer> vipSeats) {
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

    public List<Integer> getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(List<Integer> vipSeats) {
        this.vipSeats = vipSeats;
    }
}
