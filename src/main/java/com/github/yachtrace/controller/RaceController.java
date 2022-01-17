package com.github.yachtrace.controller;

import com.github.yachtrace.model.Race;
import com.github.yachtrace.service.RaceService;

import java.util.List;
import java.util.Map;

public class RaceController {
    private static final RaceService SERVICE = new RaceService();

    public Race get(int raceId) {
        return SERVICE.get(raceId);
    }

    public Race save(int raceId, Map<Integer, Integer> result) {
        return SERVICE.save(raceId, result);
    }

    public boolean delete(int id) {
        return SERVICE.delete(id);
    }

    public void deleteAll() {
        SERVICE.deleteAll();
    }

    public List<Race> getAll() {
        return SERVICE.getAll();
    }
}
