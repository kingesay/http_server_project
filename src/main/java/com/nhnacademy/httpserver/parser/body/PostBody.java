package com.nhnacademy.httpserver.parser.body;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

@JsonPropertyOrder({"args", "data", "files", "form","headers", "json", "origin", "url"})
public class PostBody implements Body {
    private final Map<String, String> args;
    private final Map<String, String> headers;
    private final String origin;
    private final String url;
    private final String data;
    private final Map<String, String> files;
    private final Map<String, String> form;
    private final Map<String, String> json;

    @Autowired
    public PostBody(Map<String,String> args,
                    String origin,
                    String url,
                    Map<String, String> header,
                    String data,
                    Map<String, String>  files,
                    Map<String, String>  form,
                    Map<String, String>  json) {
        this.args = args;
        this.headers = header;
        this.origin = origin;
        this.url = url;
        this.json = json;
        this.form = form;
        this.data = data;
        this.files = files;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getOrigin() {
        return origin;
    }

    public String getUrl() {
        return url;
    }

    public String getData() {
        return data;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public Map<String, String> getForm() {
        return form;
    }

    public Map<String, String> getJson() {
        return json;
    }
}
