package com.nhnacademy.httpserver.body;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.COLLECTION;

import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.args.ArgsParser;
import com.nhnacademy.httpserver.parser.json.JsonBody;
import com.nhnacademy.httpserver.parser.json.BodyParser;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("파라미터 데이터를 받아 Args에 저장하여 확인한다.")
    void get_method_argument_test() {
        String paramsData = "/get?msg1=hello&msg2=world";
        ArgsParser argsParser = new ArgsParser();

        Args args = argsParser.parse(paramsData);

        assertThat(args.getArgsBodyData()).containsEntry("msg1","hello");
        assertThat(args.getArgsBodyData()).containsEntry("msg2","world");
    }

    @Test
    @DisplayName("파라미터 데이터가 없으면 빈 Map 컬렉션이 나온다.")
    void get_method_argument_test_none_params_case() {
        String paramsData = "/get";
        ArgsParser argsParser = new ArgsParser();

        Args args = argsParser.parse(paramsData);

        assertThat(args.getArgsBodyData()).isEqualTo(Collections.emptyMap());
    }
}
