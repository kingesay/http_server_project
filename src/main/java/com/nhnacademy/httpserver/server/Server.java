package com.nhnacademy.httpserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class Server {
    private static final Log log = LogFactory.getLog(Server.class);

    @SuppressWarnings("java:S2189")
    public void open(){
        try(ServerSocket serverSocket = new ServerSocket(80)){
            while (true){
                Socket socket = serverSocket.accept();
                Thread receiver = new Thread(new HttpServer(socket));
                receiver.start();
            }
        } catch (IOException e) {
            log.warn(e);
        }
    }
}
