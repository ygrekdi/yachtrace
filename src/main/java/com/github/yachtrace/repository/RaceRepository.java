package com.github.yachtrace.repository;

import com.github.yachtrace.model.Race;

import java.util.List;

public interface RaceRepository {
    Race get(int id);

    Race save(Race race);

    List<Race> getAll();

    boolean delete(int id);

    void deleteAll();
}
