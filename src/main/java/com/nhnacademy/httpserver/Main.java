package com.nhnacademy.httpserver;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context =
                 new AnnotationConfigApplicationContext("com.nhnacademy.httpserver")) {
//
        }
    }
}
