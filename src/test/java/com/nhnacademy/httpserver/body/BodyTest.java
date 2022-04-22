package com.nhnacademy.httpserver.body;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.httpserver.parser.json.JsonBody;
import com.nhnacademy.httpserver.parser.json.BodyParser;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BodyTest {
    BodyParser bodyParser;

    @BeforeEach
    void setUp() {
        bodyParser = new BodyParser();
    }

    @Test
    void post_method_test() {
        String bodyData = "{\"msg1\": \"jack\", \"msg2\": \"jack\"}";

        JsonBody body = bodyParser.parse(bodyData);

        assertThat(body.getJsonBodyData().toString()).isEqualTo(
            "{msg1=jack, msg2=jack}"
        );
    }

    @Test
    void get_method_argument_test() {
        String bodyData = "/get?msg1=hello&msg2=world";

        StringTokenizer tokenizer = new StringTokenizer(bodyData ,"?");
        tokenizer.nextToken();
        StringTokenizer tokenizer2 = new StringTokenizer(tokenizer.nextToken() ,"&");
        Map<String, String> msgMap = new HashMap<>();
        while(tokenizer2.hasMoreTokens()) {
            String msg = tokenizer2.nextToken();
            String[] arr = msg.split("=");
            msgMap.put(arr[0], arr[1]);
        }

        assertThat(msgMap).containsEntry("msg1","hello");
        assertThat(msgMap).containsEntry("msg2","world");

    }
}
