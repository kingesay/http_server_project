package com.nhnacademy.httpserver.method;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.httpserver.exception.ParseFailureException;
import com.nhnacademy.httpserver.exception.method.WrongMethodAccessException;
import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.args.ArgsParser;
import com.nhnacademy.httpserver.parser.body.Body;
import com.nhnacademy.httpserver.parser.body.GetBody;
import com.nhnacademy.httpserver.parser.header.Header;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GetMethod extends Method {
    private static final Log log = LogFactory.getLog(GetMethod.class);
    private GetBody body;

    @Override
    protected String makeJsonTemplate() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(body);
        } catch (JsonProcessingException e) {
            log.warn(e);
        }
        throw new ParseFailureException("GET Method json 데이터 파싱에 실패했습니다.");
    }

    @Override
    protected void setBody(Body body) {
        this.body = (GetBody) body;
    }

    @Override
    protected void createBody(Header header) {
        Args args = new ArgsParser().parse(header.getPath());
        String ip = header.getClientIp();
        String url = "http://test-vm.com" + header.getPath();
        body = new GetBody(args.getArgsBodyData(),
            ip, url, header.getHeader());
    }

    @Override
    protected void createBody(Header header, String clientBody) {
        throw new WrongMethodAccessException("GetMethod : 잘못된 메서드 접근입니다.");
    }
}
