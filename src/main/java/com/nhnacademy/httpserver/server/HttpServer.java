package com.nhnacademy.httpserver.server;

import com.nhnacademy.httpserver.method.Method;
import com.nhnacademy.httpserver.method.MethodFactory;
import com.nhnacademy.httpserver.parser.header.Header;
import com.nhnacademy.httpserver.parser.header.HeaderParser;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.Socket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

public class HttpServer implements Runnable{
    private static final Log log = LogFactory.getLog(HttpServer.class);
    private final Socket socket;
    private final MethodFactory factory;

    public HttpServer(Socket socket, MethodFactory factory) {
        this.socket = socket;
        this.factory = factory;
    }

    @Override
    public void run() {
        try {
            if (isServerOpend()) {
                // read client request
                InputStream in = socket.getInputStream();
                byte[] resultData = new byte[4096];
                in.read(resultData);
                String read = new String(resultData).split("\u0000")[0]; // client all Data

                // header setting
                String[] headerAndBody = read.split("\r\n");
                HeaderParser headerParser = new HeaderParser();
                Header header = headerParser.parse(headerAndBody[0]);
                header.setClientIp(socket.getRemoteSocketAddress().toString());

                // body send to client
                String plainTextBody = getClientPlainTextBody(headerAndBody); // nullable
                Method body = factory.getMethod(header, plainTextBody);
                body.writeOutBody(new DataOutputStream(socket.getOutputStream()));

                // 헤더객체에 데이터 주입.
                // 객체 생성 >> 빈팩토리를 통해서 메서드 구분하여 각각의 Method객체를 통해서 Body를 만듦
                // Method method = 빈 팩토리로 json body까지 다 담은 객체를 담고
                // method.writeOutBody
                System.out.println(read);
            }
        } catch (Exception e) {
            log.warn(e);
        }
    }

    private String getClientPlainTextBody(String[] headerAndBody) {
        if(headerAndBody.length > 1){
            return headerAndBody[1];
        }
        return null;
    }

    private boolean isServerOpend(){
        return !this.socket.isClosed();
    }
}
