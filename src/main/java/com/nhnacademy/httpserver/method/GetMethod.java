package com.nhnacademy.httpserver.method;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.httpserver.exception.ParseFailureException;
import com.nhnacademy.httpserver.exception.method.WrongMethodAccessException;
import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.args.ArgsParser;
import com.nhnacademy.httpserver.parser.body.Body;
import com.nhnacademy.httpserver.parser.header.Header;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GetMethod extends Method {
    private static final Log log = LogFactory.getLog(GetMethod.class);
    private Body body;

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
    protected void createBody(Header header) {
        Args args = new ArgsParser().parse(header.getPath());
        String ip = header.getClientIp();
        String url = "http://test-vm.com" + header.getPath();

        if(Objects.equals(header.getPath(), "/ip")){
            body = Body.factoryGetOriginBody(ip);
            return;
        }

        body = Body.factoryGetBody(args.getArgsBodyData(),
            ip, url, header.getHeader());
    }

    @Override
    protected void createBody(Header header, String clientBody) {
        throw new WrongMethodAccessException("GetMethod : 잘못된 메서드 접근입니다.");
    }
}
