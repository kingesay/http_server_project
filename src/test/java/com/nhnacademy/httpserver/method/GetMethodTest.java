package com.nhnacademy.httpserver.method;

import static org.assertj.core.api.Assertions.assertThat;
import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.body.GetBody;
import com.nhnacademy.httpserver.parser.header.Header;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GetMethodTest {

    @DisplayName("get 방식의 데이터를 json으로 만듬")
    @Test
    void get_method_server_body_test() {
        Args args = new Args(Collections.emptyMap());
        Map<String , String> headers = new HashMap<>();
        headers.put("Accept" , "*/*");
        headers.put("Host" , "test-vm.com");
        headers.put("User-Agent" , "curl/7.64.1");
        Header header = new Header("200", "/get", "HTTP/1.1", headers);
        GetBody body = new GetBody(args.getArgsBodyData(),"103.243.200.16", "http://test-vm.com/get", headers);
        GetMethod getMethod = new GetMethod();
        getMethod.createBody(header);
        assertThat(getMethod.makeJsonTemplate())
            .contains("args", "headers", "origin", "url", "Accept", "User-Agent", "Host");
    }
}
