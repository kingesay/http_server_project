package com.nhnacademy.httpserver;

import com.nhnacademy.httpserver.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context =
                 new AnnotationConfigApplicationContext("com.nhnacademy.httpserver")) {
//
        }
    }
}
