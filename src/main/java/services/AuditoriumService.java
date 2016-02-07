package services;

import entity.Auditorium;

import java.util.List;

public interface AuditoriumService {
    List<Auditorium> getAuditoriums();

    int getSeatsNumber();

    List<Integer> getVipSeats();
}
