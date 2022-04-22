package com.nhnacademy.httpserver.parser.files;

import java.util.Map;

public class FilesBody {
    private final Map<String, String> files;

    public FilesBody(Map<String, String> files) {
        this.files = files;
    }

    public Map<String, String> getFiles() {
        return files;
    }
}
