package com.nhnacademy.httpserver.parser.body;

import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.files.FilesBody;
import com.nhnacademy.httpserver.parser.form.Form;
import com.nhnacademy.httpserver.parser.header.Header;
import com.nhnacademy.httpserver.parser.json.JsonBody;

public class Body {
    private Args args;
    private String data;
    private FilesBody files;
    private Form form;
    private Header header;
    private JsonBody json;
    private String origin;
    private String url;

    public Body(Args args, String data, FilesBody files,
                Form form, Header header, JsonBody json, String origin, String url) {
        this.args = args;
        this.data = data;
        this.files = files;
        this.form = form;
        this.header = header;
        this.json = json;
        this.origin = origin;
        this.url = url;
    }
}
