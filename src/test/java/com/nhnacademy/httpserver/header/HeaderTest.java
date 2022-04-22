package com.nhnacademy.httpserver.header;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.StringTokenizer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HeaderTest {
    HeaderParser headerParser = new HeaderParser();

    @Test
    @DisplayName("클라이언트 데이터가 수신되면 Header객체로 파싱한다.")
    void get_method_test() {
        String clientData = "GET / HTTP/1.1\r\n" +
            "Host: localhost\r\n" +
            "User-Agent: curl/7.79.1\r\n" +
            "Accept: */*";
        StringTokenizer sk = new StringTokenizer(clientData, "\r\n");
        StringBuilder sb = new StringBuilder();
        String protocols = sk.nextToken();

        while (sk.hasMoreTokens()){
            sb.append(sk.nextToken()).append(";");
        }

        Header header = headerParser.parse(protocols, sb.toString());
        assertThat(header.getMethod()).isEqualTo("GET");
        assertThat(header.getPath()).isEqualTo("/");
        assertThat(header.getProtocolVersion()).isEqualTo("HTTP/1.1");
        Map<String, String> headerMap = header.getHeader();
        assertThat(headerMap).containsEntry("Host", "localhost")
            .containsEntry("User-Agent", "curl/7.79.1")
            .containsEntry("Accept", "*/*");
    }

    @Test
    @DisplayName("CRLF 문자는 trim 메서드로 사라지나?")
    void is_removed_return() {
        // 사라진다!!
        assertThat("HTTP/1.1\r\n".trim()).isEqualTo("HTTP/1.1");
    }

    @Test
    @DisplayName("tokenizer로 구분한다.")
    void tokenizer_Test() {
        String clientData = "GET / HTTP/1.1\n" +
            "Host: localhost\n" +
            "User-Agent: curl/7.79.1\n" +
            "Accept: */*";

        StringTokenizer tokenizer = new StringTokenizer(clientData);
        assertThat(tokenizer.nextToken()).isEqualTo("GET");
        assertThat(tokenizer.nextToken()).isEqualTo("/");
        assertThat(tokenizer.nextToken()).isEqualTo("HTTP/1.1");

    }
}
