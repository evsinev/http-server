package com.payneteasy.http.server.impl.response;

import com.payneteasy.http.server.api.response.*;

import java.util.ArrayList;
import java.util.List;

public class HttpResponseBuilder {

    private final HttpResponseStatusLine   statusLine;
    private final List<HttpResponseHeader> headers = new ArrayList<>();
    private       HttpResponseMessageBody  body;

    private HttpResponseBuilder(HttpResponseStatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public static HttpResponseBuilder statusOk() {
        return new HttpResponseBuilder(HttpResponseStatusLine.OK);
    }

    public static HttpResponseBuilder status(HttpResponseStatusLine aStatus) {
        return new HttpResponseBuilder(aStatus);
    }

    public HttpResponseBuilder body(HttpResponseMessageBody aBody) {
        body = aBody;
        return this;
    }

    public HttpResponseBuilder body(byte[] aBytes) {
        body = new HttpResponseMessageBody(aBytes);
        return this;
    }

    public HttpResponseBuilder addHeader(String aName, String aValue) {
        headers.add(new HttpResponseHeader(aName, aValue));
        return this;
    }

    public HttpResponseBuilder addHeader(String aName, int aValue) {
        headers.add(new HttpResponseHeader(aName, String.valueOf(aValue)));
        return this;
    }

    public HttpResponse build() {
        addHeader("Content-Length", body.getBytes().length);
        return new HttpResponse(statusLine, new HttpResponseHeaders(headers), body);
    }
}
