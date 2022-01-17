package com.github.yachtrace.service;

import com.github.yachtrace.model.Race;
import com.github.yachtrace.repository.RaceRepository;
import com.github.yachtrace.repository.inmemory.InMemoryRaceRepository;

import java.util.List;
import java.util.Map;

public class RaceService {
    private static final RaceRepository REPOSITORY = new InMemoryRaceRepository();

    public Race get(int raceId) {
        return REPOSITORY.get(raceId);
    }

    public Race save(int raceId, Map<Integer, Integer> result) {
        return REPOSITORY.save(new Race(raceId, result));
    }

    public boolean delete(int id) {
        return REPOSITORY.delete(id);
    }

    public void deleteAll() {
        REPOSITORY.deleteAll();
    }

    public List<Race> getAll() {
        return REPOSITORY.getAll();
    }
}
