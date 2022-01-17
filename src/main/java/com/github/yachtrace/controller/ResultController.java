package com.github.yachtrace.controller;

import com.github.yachtrace.service.ResultService;
import com.github.yachtrace.to.ResultTo;

import java.util.List;

public class ResultController {
    private static final ResultService SERVICE = new ResultService();

    public List<ResultTo> getResultTos() {
        return SERVICE.getResultTos();
    }
}
