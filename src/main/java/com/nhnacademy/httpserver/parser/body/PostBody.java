package com.nhnacademy.httpserver.parser.body;

import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.files.FilesBody;
import com.nhnacademy.httpserver.parser.form.Form;
import com.nhnacademy.httpserver.parser.header.Header;
import com.nhnacademy.httpserver.parser.json.JsonBody;
import java.util.Map;

public class PostBody extends GetBody {
    private JsonBody json;
    private Form form;
    private String data;
    private FilesBody files;

    public PostBody(Map<String,String> args, String origin, String url,
                    Map<String, String> header, JsonBody json, Form form, String data,
                    FilesBody files) {
        super(args, origin, url, header);
        this.json = json;
        this.form = form;
        this.data = data;
        this.files = files;
    }

    //TODO
}
