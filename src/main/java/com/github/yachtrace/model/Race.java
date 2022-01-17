package com.github.yachtrace.model;

import java.util.Map;

public class Race {
    private final Integer id;

    //yacht id, finish number
    private final Map<Integer, Integer> result;

    public Race(Integer id, Map<Integer, Integer> result) {
        this.id = id;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public Map<Integer, Integer> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "race number:" + this.getId();
    }
}
