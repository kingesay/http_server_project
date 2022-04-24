package com.nhnacademy.httpserver.integration;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HttpServerIntegrationTest {

    @Test
    @DisplayName("멀티 쓰레드 환경으로 서버가 테스트 클라이언트들에게 응답을 안전하게 하는가? ")
    void server_thread_test() {
        Set<ProcessBuilder> builders = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            ProcessBuilder processBuilder = new ProcessBuilder("curl", "-v", "localhost/get");
            builders.add(processBuilder);
        }
        builders.forEach(processBuilder -> {
            try {
                processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Test
    @DisplayName("멀티 쓰레드 환경으로 서버가 테스트 클라이언트들에게 응답을 안전하게 하는가? ")
    void server_thread_test_post_method() {
        Set<ProcessBuilder> builders = new HashSet<>();
        for (int i = 0; i < 20; i++) {
            ProcessBuilder processBuilder = new ProcessBuilder("curl", "-v", "-X", "POST", "localhost/post?name1=minsu",
                "'Content-Type: application/json'", "-d", "'{ \"msg1\": \"hello\", \"msg2\": \"world\" }'");
            builders.add(processBuilder);
        }
        builders.forEach(processBuilder -> {
            try {
                processBuilder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
