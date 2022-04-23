package com.nhnacademy.httpserver.parser.body;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Map;

@JsonPropertyOrder({"args", "data", "files", "form","headers", "json", "origin", "url"})
public class PostBody extends GetBody implements Body {
    private String data;
    private Map<String, String> files;
    private Map<String, String> form;
    private Map<String, String> json;

    public PostBody(Map<String,String> args,
                    String origin,
                    String url,
                    Map<String, String> header,
                    String data,
                    Map<String, String>  files,
                    Map<String, String>  form,
                    Map<String, String>  json) {
        super(args, origin, url, header);
        this.json = json;
        this.form = form;
        this.data = data;
        this.files = files;
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
