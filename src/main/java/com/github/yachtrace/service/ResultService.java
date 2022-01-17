package com.github.yachtrace.service;

import com.github.yachtrace.model.Race;
import com.github.yachtrace.repository.RaceRepository;
import com.github.yachtrace.repository.inmemory.InMemoryRaceRepository;
import com.github.yachtrace.to.ResultTo;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.yachtrace.RaceUtils.*;

public class ResultService {
    private static final RaceRepository REPOSITORY = new InMemoryRaceRepository();

    public List<ResultTo> getResultTos() {
        return getTos(REPOSITORY.getAll());
    }

    private List<ResultTo> getTos(List<Race> races) {
        if (races.size() == 0) {
            return Collections.emptyList();
        }

        if (races.size() == 1) {
            return createTos(calculateRaceResult(races.get(0)));
        }

        Map<Integer, List<Double>> allRaceResult = new HashMap<>();
        Map<Integer, Double> yachtsResult = new HashMap<>();
        races.forEach(r -> calculateRaceResult(r).forEach((key, value) -> updateValueInMap(allRaceResult, key, value)));
        for (Integer key : allRaceResult.keySet()) {
            List<Double> values = allRaceResult.get(key);
            if (races.size() - RACES_COUNT_FOR_DISQUALIFICATION > values.size()) {
                yachtsResult.put(key, null);
                continue;
            }
            if (races.size() > values.size()) {
                values.add(null);
            }
            yachtsResult.put(key, calculateYachtResult(values));
        }
        return createTos(yachtsResult);
    }

    private Map<Integer, Double> calculateRaceResult(Race race) {
        Map<Integer, Double> raceResult = new HashMap<>();
        race.getResult().forEach((k, v) -> raceResult.put(k, calculateScore(v)));
        return raceResult;
    }

    private Double calculateScore(Integer result) {
        if (result == null || result == 0) {
            return null;
        }

        var penaltyPosition = SCORE_TABLE.size();
        if (result <= penaltyPosition) {
            return SCORE_TABLE.get(result);
        }

        return SCORE_TABLE.get(penaltyPosition) + (result - penaltyPosition);
    }

    private void updateValueInMap(Map<Integer, List<Double>> map, Integer key, Double value) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(value);
    }

    private List<ResultTo> createTos(Map<Integer, Double> map) {
        List<ResultTo> list = new ArrayList<>();
        AtomicInteger yachtPlace = new AtomicInteger();
        Map<Integer, Double> sortedMap = sortMap(map);
        Iterator<Map.Entry<Integer, Double>> iterator = sortedMap.entrySet().iterator();
        Map.Entry<Integer, Double> previous = null;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Double> next = iterator.next();
            list.add(new ResultTo(getPoint(yachtPlace, next, previous), Collections.singletonMap(next.getKey(), next.getValue())));
            previous = next;
        }
        return list;
    }

    private int getPoint(AtomicInteger yachtPlace, Map.Entry<Integer, Double> value, Map.Entry<Integer, Double> previousValue) {
        if (previousValue != null && previousValue.getValue().equals(value.getValue())) {
            return yachtPlace.get();
        }
        return yachtPlace.incrementAndGet();
    }

    private Double calculateYachtResult(List<Double> scores) {
        return scores.stream()
                .sorted(Comparator.nullsFirst((o1, o2) -> -o1.compareTo(o2)))
                .skip(1)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
