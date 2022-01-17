package com.github.yachtrace;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class RaceUtils {
    public static final Map<Integer, Double> SCORE_TABLE = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(1, 0.0),
            new AbstractMap.SimpleEntry<>(2, 3.0),
            new AbstractMap.SimpleEntry<>(3, 5.7),
            new AbstractMap.SimpleEntry<>(4, 8.0),
            new AbstractMap.SimpleEntry<>(5, 10.0),
            new AbstractMap.SimpleEntry<>(6, 11.7),
            new AbstractMap.SimpleEntry<>(7, 13.0)
    );

    public static final int RACES_COUNT_FOR_DISQUALIFICATION = 2;

    public static Map<Integer, Double> sortMap(Map<Integer, Double> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(LinkedHashMap::new, (m, v) -> m.put(v.getKey(), v.getValue()), LinkedHashMap::putAll);
    }
}
