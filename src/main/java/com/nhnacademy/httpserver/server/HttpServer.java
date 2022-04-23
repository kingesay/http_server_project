package com.nhnacademy.httpserver.server;

import java.io.InputStream;
import java.net.Socket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpServer implements Runnable{
    private static final Log log = LogFactory.getLog(HttpServer.class);
    private final Socket socket;

    public HttpServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            if (isServerOpend()) {
                InputStream in = socket.getInputStream();
                byte[] resultData = new byte[4096];
                in.read(resultData);
                String read = new String(resultData).split("\u0000")[0];

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

    private boolean isServerOpend(){
        return !this.socket.isClosed();
    }
}
