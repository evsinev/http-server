package com.payneteasy.http.server.api.request;

import java.nio.charset.Charset;

public class HttpRequestMessageBody {

    private final byte[] body;

    public HttpRequestMessageBody(byte[] body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "HttpRequestMessageBody{" +
                "body.length=" + body.length +
                '}';
    }

    public String asString(Charset aCharset) {
        return new String(body, aCharset);
    }
}
