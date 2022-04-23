package com.nhnacademy.httpserver.method;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.httpserver.exception.ParseFailureException;
import com.nhnacademy.httpserver.exception.method.WrongMethodAccessException;
import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.args.ArgsParser;
import com.nhnacademy.httpserver.parser.body.Body;
import com.nhnacademy.httpserver.parser.body.PostBody;
import com.nhnacademy.httpserver.parser.files.FilesBody;
import com.nhnacademy.httpserver.parser.files.FilesParser;
import com.nhnacademy.httpserver.parser.form.FormBody;
import com.nhnacademy.httpserver.parser.header.Header;
import com.nhnacademy.httpserver.parser.json.JsonBody;
import com.nhnacademy.httpserver.parser.json.JsonBodyParser;
import java.util.Collections;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class PostMethod extends Method{
    private static final Log log = LogFactory.getLog(PostMethod.class);
    private PostBody body;

    @Override
    protected String makeJsonTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(body);
        } catch (JsonProcessingException e) {
            log.warn(e);
        }
        throw new ParseFailureException("POST Method json 데이터 파싱에 실패했습니다.");
    }

    @Override
    protected void createBody(Header header) {
        throw new WrongMethodAccessException("PostMethod : 잘못된 메서드 접근입니다.");
    }

    @Override
    protected void createBody(Header header, String clientBody) {
        Args args = new ArgsParser().parse(header.getPath());
        String url = "http://test-vm.com" + header.getPath();
        FilesBody filesBody = new FilesBody(Collections.emptyMap());
        FormBody formBody = new FormBody(Collections.emptyMap());

        if(header.getHeader().get("Content-Type").equals("application/json")){
            JsonBodyParser parser = new JsonBodyParser();
            JsonBody jsonBody = parser.parse(clientBody);

            this.body = new PostBody(args.getArgsBodyData(),
                header.getClientIp(),
                url,
                header.getHeader(),
                clientBody,
                filesBody.getFiles(),
                formBody.getForm(),
                jsonBody.getJsonBodyData()
            );
        } else {
            FilesParser filesParser = new FilesParser();
            String fileContent = getFileContent(header, clientBody);
            filesParser.setContentTypeHeader(header.getHeader().get("Content-Type"));
            filesBody = filesParser.parse(fileContent);

            this.body = new PostBody(args.getArgsBodyData(),
                header.getClientIp(),
                url,
                header.getHeader(),
                "",
                filesBody.getFiles(),
                formBody.getForm(),
                null
            );
        }
    }

    private String getFileContent(Header header, String clientBody) {
        StringBuilder fileContent = new StringBuilder(header.getHeader().get("Content-Type"));
        fileContent.append("\r\n").append(clientBody);
        return fileContent.toString();
    }
}
