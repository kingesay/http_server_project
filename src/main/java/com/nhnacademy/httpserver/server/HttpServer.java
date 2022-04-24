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

public class HttpServer extends Thread{
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
                read = read.replaceFirst("\r\n\r\n", "-\r\n\r\n");
                String[] headerAndBody = read.split("-\r\n\r\n");
                HeaderParser headerParser = new HeaderParser();
                Header header = headerParser.parse(headerAndBody[0]);
                header.setClientIp(socket.getRemoteSocketAddress().toString());

                // body send to client
                String plainTextBody = getClientPlainTextBody(headerAndBody); // nullable
                Method body = factory.getMethod(header, plainTextBody);
                body.writeOutBody(new DataOutputStream(socket.getOutputStream()));

                log.info("\n Thread id : " + this.getId() + "\n" + read);
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
