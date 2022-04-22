package com.nhnacademy.httpserver.method;

import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class Method {
    private static final Log log = LogFactory.getLog(Method.class);

    public final void writeOutBody(DataOutputStream out) {
        try {
            out.write(makeJsonTemplate().getBytes());
            out.flush();
        } catch (IOException e) {
            log.error(e);
        } finally {
            dismissOutputStream(out);
        }
    }

    private void dismissOutputStream(DataOutputStream out) {
        try {
            out.close();
        } catch (IOException e) {
            log.error(e);
        }
    }

    protected abstract String makeJsonTemplate();
}
