package com.nhnacademy.httpserver.body;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.nhnacademy.httpserver.ReadFileBodyData;
import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.args.ArgsParser;
import com.nhnacademy.httpserver.parser.files.FilesParser;
import com.nhnacademy.httpserver.parser.json.JsonBody;
import com.nhnacademy.httpserver.parser.json.JsonBodyParser;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.fileupload.MultipartStream.ProgressNotifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BodyTest {

    @Test
    @DisplayName("json데이터가 클라이언트 body data로 들어오면, 파싱하여 Map으로 변환한다.")
    void post_method_test() {
        JsonBodyParser bodyParser = bodyParser = new JsonBodyParser();;
        String bodyData = "{\"msg1\": \"jack\", \"msg2\": \"jack\"}";
        JsonBody body = bodyParser.parse(bodyData);

        assertThat(body.getJsonBodyData()).hasToString("{msg1=jack, msg2=jack}");
    }

    @Test
    @DisplayName("빈 json데이터가 들어오면, 빈 Map이 된다.")
    void post_method_test_empty_json_case() {
        JsonBodyParser bodyParser = bodyParser = new JsonBodyParser();;
        String bodyData = "{}";
        JsonBody body = bodyParser.parse(bodyData);

        assertThat(body.getJsonBodyData()).hasToString("{}");
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

    @Test
    @DisplayName("클라이언트에서 파일을 보내면, Map data가 나온다.")
    void post_method_files_data_parse() throws IOException {
        String files =""
            + "Content-Type: multipart/form-data; boundary=------------------------17b58eab1165ba70\r\n"
            + "\r\n"
            + "--------------------------17b58eab1165ba70\r\n"
            + "Content-Disposition: form-data; name=\"upload\"; filename=\"README.md\"\r\n"
            + "Content-Type: application/octet-stream\r\n"
            + "\r\n"
            + "{ \"msg1\": \"hello\", \"msg2\": \"world\" }"
            + "\r\n"
            + "--------------------------17b58eab1165ba70--\r\n";

        byte[] boundary = "------------------------17b58eab1165ba70".getBytes();

        ByteArrayInputStream content = new ByteArrayInputStream(files.getBytes());

        MultipartStream multipartStream =
            new MultipartStream(content, boundary, 4096, null);

        boolean nextPart = multipartStream.skipPreamble();
        // 클라이언트에서 파일을 쉽게 보내는 법입니다.
        while (nextPart) {
            String header = multipartStream.readHeaders();
            System.out.println("");
            System.out.println("Headers:");
            System.out.println(header);

            ReadFileBodyData rfd = new ReadFileBodyData();
            multipartStream.readBodyData(rfd);
            System.out.println(rfd.parseBuff());
            assertThat(rfd.parseBuff()).isEqualTo("{ \"msg1\": \"hello\", \"msg2\": \"world\" }");

            nextPart = multipartStream.readBoundary();
        }
    }

    @Test
    @DisplayName("클라이언트에서 파일을 보내지않으면, 빈 Map이 출력된다.")
    void post_method_files_data_parse_empty_file_case(){
        FilesParser fp = new FilesParser();
        assertThat(fp.parse("").getFiles()).isEqualTo(Collections.emptyMap());
    }
}
