package com.github.yachtrace;

import com.github.yachtrace.controller.ResultController;

public class Main {
    private static final ResultController CONTROLLER = new ResultController();

    public static void main(String[] args) {
        CONTROLLER.getResultTos().forEach(System.out::println);
    }
}
