package com.nhnacademy.httpserver.body;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.httpserver.parser.json.JsonBody;
import com.nhnacademy.httpserver.parser.json.BodyParser;
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
}
