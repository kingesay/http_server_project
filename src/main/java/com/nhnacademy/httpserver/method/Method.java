package com.nhnacademy.httpserver.method;

import com.nhnacademy.httpserver.parser.header.Header;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Method {
    private static final Log log = LogFactory.getLog(Method.class);

    public final void writeOutBody(DataOutputStream out) {
        try {
            String jsonTemplate = makeJsonTemplate();
            String serverHeaderAndBody = makeServerHeader(jsonTemplate.length()) + "\r\n" + jsonTemplate;

            out.write(serverHeaderAndBody.getBytes());
            out.flush();
        } catch (IOException e) {
            log.error(e);
        } finally {
            dismissOutputStream(out);
        }
    }

    private String makeServerHeader(int bodyLength) {
        return "HTTP/1.1 200 OK\r\n" +
            "Date: " + LocalDateTime.now() + "\r\n" +
            "Content-Type: application/json\r\n" +
            "Content-Length: " + bodyLength + "\r\n" +
            "Connection: keep-alive\r\n" +
            "Server: test-vm/0.0.1\r\n" +
            "Access-Control-Allow-Origin: *\r\n" +
            "Access-Control-Allow-Credentials: true\r\n";
    }

    private void dismissOutputStream(DataOutputStream out) {
        try {
            out.close();
        } catch (IOException e) {
            log.error(e);
        }
    }

    protected abstract String makeJsonTemplate();
    protected abstract void createBody(Header header);
    protected abstract void createBody(Header header, String clientBody);
}
