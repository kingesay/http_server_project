package com.nhnacademy.httpserver.method;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.body.PostBody;
import com.nhnacademy.httpserver.parser.files.FilesBody;
import com.nhnacademy.httpserver.parser.form.FormBody;
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
        PostBody body = createPostBody();
        PostMethod postMethod = new PostMethod();
        postMethod.setBody(body);

        assertThat(postMethod.makeJsonTemplate()).isEqualTo("{\r\n"
                +"  \"args\" : { },\r\n"
                +"  \"data\" : \"{ }\",\r\n"
                +"  \"files\" : { },\r\n"
                +"  \"form\" : { },\r\n"
                +"  \"headers\" : {\r\n"
                +"    \"Accept\" : \"*/*\",\r\n"
                +"    \"User-Agent\" : \"curl/7.64.1\",\r\n"
                +"    \"Host\" : \"test-vm.com\",\r\n"
                +"    \"Content-Length\" : \"36\",\r\n"
                +"    \"Content-Type\" : \"application/json\"\r\n"
                +"  },\r\n"
                +"  \"json\" : { },\r\n"
                +"  \"origin\" : \"103.243.200.16\",\r\n"
                +"  \"url\" : \"http://test-vm.com/post\"\r\n"
                +"}"
        );
    }

    private PostBody createPostBody(){
        Args args = new Args(Collections.emptyMap());
        Map<String , String> headers = new HashMap<>();
        headers.put("Accept" , "*/*");
        headers.put("Host" , "test-vm.com");
        headers.put("User-Agent" , "curl/7.64.1");
        headers.put("Content-Length" , "36");
        headers.put("Content-Type" , "application/json");
        FilesBody files = new FilesBody(Collections.emptyMap());
        FormBody form = new FormBody(Collections.emptyMap());
        JsonBody json = new JsonBody(Collections.emptyMap());

        return new PostBody(args.getArgsBodyData(),
            "103.243.200.16",
            "http://test-vm.com/post",
            headers,
            "{ }",
            files.getFiles(),
            form.getForm(),
            json.getJsonBodyData()
            );
    }
}
