package com.github.yachtrace.to;

import java.util.Map;

public class ResultTo {
    //result place, yacht number, yacht result score
    private final Integer id;

    private final Map<Integer, Double> result;

    public ResultTo(Integer id, Map<Integer, Double> yachtResult) {
        this.id = id;
        this.result = yachtResult;
    }

    public Integer getId() {
        return id;
    }

    public Map<Integer, Double> getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "point:" + this.getId() + " , yacht number:" + this.getResult().keySet() + " , score: " + this.getResult().values();
    }
}
