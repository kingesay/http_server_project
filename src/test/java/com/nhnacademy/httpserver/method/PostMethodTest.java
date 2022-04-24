package com.nhnacademy.httpserver.method;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.body.PostBody;
import com.nhnacademy.httpserver.parser.files.FilesBody;
import com.nhnacademy.httpserver.parser.form.FormBody;
import com.nhnacademy.httpserver.parser.header.Header;
import com.nhnacademy.httpserver.parser.json.JsonBody;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostMethodTest {
    @Test
    @DisplayName("Post method로 호출 시 서버에서 보내는 데이터를 json형식으로 보내는가?")
    void post_method_server_body_test() {
        Map<String , String> headers = new HashMap<>();
        headers.put("Accept" , "*/*");
        headers.put("Host" , "test-vm.com");
        headers.put("User-Agent" , "curl/7.64.1");
        headers.put("Content-Length" , "36");
        headers.put("Content-Type" , "multipart/form-data; boundary=------------------------17b58eab1165ba70");
        Header header = new Header("POST", "/post", "HTTP/1.1", headers);
        String clientBody = ""
            + "--------------------------17b58eab1165ba70\r\n"
            + "Content-Disposition: form-data; name=\"upload\"; filename=\"README.md\"\r\n"
            + "Content-Type: application/octet-stream\r\n"
            + "\r\n"
            + "{ \"msg1\": \"hello\", \"msg2\": \"world\" }"
            + "\r\n"
            + "--------------------------17b58eab1165ba70--\r\n";

        PostMethod postMethod = new PostMethod();
        postMethod.createBody(header, clientBody);
        assertThat(postMethod.makeJsonTemplate())
            .contains("args", "data", "files", "headers", "json", "origin", "url", "{", "}");
    }
}
