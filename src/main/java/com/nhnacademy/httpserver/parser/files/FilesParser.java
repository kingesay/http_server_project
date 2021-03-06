package com.nhnacademy.httpserver.parser.files;

import com.nhnacademy.httpserver.ReadFileBodyData;
import com.nhnacademy.httpserver.exception.ParseFailureException;
import com.nhnacademy.httpserver.parser.Parseable;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.fileupload.MultipartStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FilesParser implements Parseable<FilesBody> {
    private static final Log log = LogFactory.getLog(FilesParser.class);
    private String contentTypeHeader;

    @Override
    public FilesBody parse(String data) {
        try {
            if (!data.isEmpty()) {

                String[] contents = contentTypeHeader.split("=");
                byte[] boundary = contents[contents.length - 1].trim().getBytes();

                ByteArrayInputStream content = new ByteArrayInputStream(data.getBytes());

                MultipartStream multipartStream =
                    new MultipartStream(content, boundary, 4096, null);

                boolean nextPart = false;

                nextPart = multipartStream.skipPreamble();

                Map<String, String> filesBody = new HashMap<>();
                while (nextPart) {
                    String header = multipartStream.readHeaders();
                    // Content-Disposition: form-data; >>> name="upload" <<< ; filename="README.md"
                    String key = header.split("; ")[1]
                        .substring(5) // name="upload" >>> "upload"
                        .replace("\"", ""); // "upload" >>> upload

                    ReadFileBodyData rfd = new ReadFileBodyData();
                    multipartStream.readBodyData(rfd);
                    String value = rfd.parseBuff();

                    filesBody.put(key, value);
                    nextPart = multipartStream.readBoundary();
                }
                return new FilesBody(filesBody);
            }
        } catch (IOException e) {
            log.warn(e);
        }
        throw new ParseFailureException("?????? ????????? ????????? ??????????????????.");
    }

    public void setContentTypeHeader(String contentTypeHeader) {
        this.contentTypeHeader = contentTypeHeader;
    }
}
