package services;

import entity.Auditorium;

import java.util.List;
import java.util.Set;

public interface AuditoriumService {
    List<Auditorium> getAuditoriums();

    int getSeatsNumber(Auditorium auditorium);

    Set<Integer> getVipSeats(Auditorium auditorium);

    Auditorium getById(long id);
}
