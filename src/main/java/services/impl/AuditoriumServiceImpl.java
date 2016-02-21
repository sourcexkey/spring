package services.impl;

import entity.Auditorium;
import services.AuditoriumService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

public class AuditoriumServiceImpl implements AuditoriumService {

    private final Map<String, Auditorium> auditoriumRepo = new HashMap<>();
    private volatile long id = 1;

    public AuditoriumServiceImpl(List<Properties> auditoriums) {
        auditoriums.forEach(p -> {
            String name = p.getProperty("name");
            int seats = Integer.parseInt(p.getProperty("seats"));
            Set<Integer> vipSeats = Arrays
                    .stream(p.getProperty("vipSeats").split(","))
                    .map(s -> Integer.parseInt(s))
                    .filter(vipSeat -> vipSeat > 0 && vipSeat <= seats)
                    .collect(Collectors.toSet());
            auditoriumRepo.put(name, new Auditorium(id++, name, seats, vipSeats));
        });
    }

    @Override
    public List<Auditorium> getAuditoriums() {
        return auditoriumRepo.values().stream().collect(Collectors.toList());
    }

    @Override
    public Auditorium getById(long id) {
        return auditoriumRepo
                .values()
                .stream()
                .filter(a -> a.getId() == id)
                .findFirst().get();
    }

    @Override
    public int getSeatsNumber(Auditorium auditorium) {
        return auditoriumRepo.get(auditorium.getName()).getSeats();
    }

    @Override
    public Set<Integer> getVipSeats(Auditorium auditorium) {
        return auditoriumRepo.get(auditorium.getName()).getVipSeats();
    }
}
