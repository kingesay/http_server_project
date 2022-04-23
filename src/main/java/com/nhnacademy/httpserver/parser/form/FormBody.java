package com.nhnacademy.httpserver.parser.form;

import java.util.Map;

public class FormBody {
    private final Map<String, String> form;

    public FormBody(Map<String, String> form) {
        this.form = form;
    }

    public Map<String, String> getForm() {
        return form;
    }
}
