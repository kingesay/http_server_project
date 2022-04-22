package com.nhnacademy.httpserver.parser.body;

import com.nhnacademy.httpserver.parser.args.Args;
import com.nhnacademy.httpserver.parser.header.Header;

public class GetBody {
    private Args args;
    private String origin;
    private String url;
    private Header header;

    public GetBody(Args args, String origin, String url,
                   Header header) {
        this.args = args;
        this.origin = origin;
        this.url = url;
        this.header = header;
    }

    //TODO
}
