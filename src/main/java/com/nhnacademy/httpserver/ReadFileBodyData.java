package com.nhnacademy.httpserver;

import java.io.ByteArrayOutputStream;

public class ReadFileBodyData extends ByteArrayOutputStream {
    public String parseBuff(){
        return new String(super.buf).split("\u0000")[0];
    }
}
