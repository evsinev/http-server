package com.payneteasy.http.server.api.response;

import java.util.List;

public class HttpResponseHeaders {

    private final List<HttpResponseHeader> headers;

    public HttpResponseHeaders(List<HttpResponseHeader> headers) {
        this.headers = headers;
    }

    public List<HttpResponseHeader> getHeaders() {
        return headers;
    }

    @Override
    public String toString() {
        return "HttpResponseHeaders{" +
                "headers=" + headers +
                '}';
    }
}
