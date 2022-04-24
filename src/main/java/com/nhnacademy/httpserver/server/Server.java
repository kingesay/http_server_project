package com.nhnacademy.httpserver.server;

import com.nhnacademy.httpserver.method.MethodFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Server {
    private static final Log log = LogFactory.getLog(Server.class);
    private final MethodFactory factory;

    @Autowired
    public Server(MethodFactory factory) {
        this.factory = factory;
    }

    @SuppressWarnings("java:S2189")
    public void open(){
        try(ServerSocket serverSocket = new ServerSocket(80)){
            while (true){
                Socket socket = serverSocket.accept();
                Thread session = new HttpServer(socket, factory);
                runSession(session);
            }
        } catch (IOException e) {
            log.warn(e);
        }
    }

    private void runSession(Thread receiver) {
        try {
            receiver.start();
            receiver.join();
        } catch (InterruptedException e) {
            log.error(e);
            receiver.interrupt();
        }
    }
}
