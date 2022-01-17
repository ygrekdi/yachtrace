package com.github.yachtrace.repository.inmemory;

import com.github.yachtrace.model.Race;
import com.github.yachtrace.repository.RaceRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRaceRepository implements RaceRepository {
    private final Map<Integer, Race> repository = new ConcurrentHashMap<>();

    {
        save(new Race(1, Map.of(15, 1, 1, 2, 22, 3, 17, 4, 4, 5, 45, 6, 29, 7, 16, 8, 24, 9, 8, 10)));
        save(new Race(2, Map.of(1, 1, 15, 2, 17, 3, 22, 4, 45, 5, 29, 6, 4, 7, 24, 8)));
        save(new Race(3, Map.of(17, 1, 15, 2, 1, 3, 16, 4, 4, 5, 45, 6, 22, 7, 24, 8, 8, 9, 29, 10)));
        save(new Race(4, Map.of(4, 1, 17, 2, 15, 3, 1, 4, 22, 5, 16, 6, 45, 7, 29, 8, 24, 9)));
        save(new Race(5, Map.of(22, 1, 1, 2, 16, 3, 17, 4, 15, 5, 4, 6, 24, 7, 45, 8, 29, 9)));
    }

    @Override
    public Race get(int id) {
        return repository.get(id);
    }

    @Override
    public void deleteAll() {
        repository.clear();
    }

    @Override
    public Race save(Race race) {
        //Добавляем запись только если она отсутствует в репозитории
        return repository.putIfAbsent(race.getId(), race);
    }

    @Override
    public List<Race> getAll() {
        return repository.values().stream().toList();
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }
}
