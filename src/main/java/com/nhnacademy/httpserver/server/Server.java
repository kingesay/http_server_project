package com.nhnacademy.httpserver.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class Server {
    private static final Log log = LogFactory.getLog(Server.class);
    private DataOutputStream out;

    public void open(){
        try(ServerSocket serverSocket = new ServerSocket(80)){
            while (true){
                Socket socket = serverSocket.accept();
                InputStreamReader in = new InputStreamReader(socket.getInputStream());
                Thread receiver = new Thread(new ServerReceiver(socket.getInputStream()));
                out = new DataOutputStream(socket.getOutputStream());
                receiver.start();

            }
        } catch (IOException e) {
            log.warn(e);
        }
    }
}
