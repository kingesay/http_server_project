package com.nhnacademy.httpserver.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ServerReceiver implements Runnable{
    private static final Log log = LogFactory.getLog(ServerReceiver.class);
    private final InputStream in;
    private final byte[] readData;

    public ServerReceiver(InputStream in) {
        this.in = in;
        readData = new byte[2048];
    }

    @Override
    public void run() {
        try {
//            while (true) {
                if (isReadable()) {
//                    byte[] resultData = in.readAllBytes();
//                    System.out.println(new String(resultData));
                    in.read(readData);
                    System.out.println(new String(readData).split("\u0000")[0]);
                }
//            }
        } catch (Exception e) {
            log.warn(e);
        }
    }

    private boolean isReadable(){
        return Objects.nonNull(this.in);
    }

    public byte[] getReadData() {
        return readData;
    }
}
