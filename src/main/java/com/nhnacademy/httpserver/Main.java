package com.nhnacademy.httpserver;

import com.nhnacademy.httpserver.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        try (var context =
                 new AnnotationConfigApplicationContext("com.nhnacademy.httpserver")) {
            context.getBean("server", Server.class).open();
        }
    }
}
// 1. 클라이언트 데이터 받아오기 -> 완료 --
// <<
// 1-2 클라이언트 데이터 헤더를 받아 파싱 (...)
// 1-3 클라이언트 body 데이터 헤더를 받아 파싱 (POST) --
// 2 각 메서드를 구분하여 Method객체의 writeOutBody 메서드를 통해서 클라이언트에 데이터를 송출함 --
