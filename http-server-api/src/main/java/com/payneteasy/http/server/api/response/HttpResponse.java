package com.payneteasy.http.server.api.response;

public class HttpResponse {

    private final HttpResponseStatusLine  statusLine;
    private final HttpResponseHeaders     headers;
    private final HttpResponseMessageBody body;

    public HttpResponse(HttpResponseStatusLine statusLine, HttpResponseHeaders headers, HttpResponseMessageBody body) {
        this.statusLine = statusLine;
        this.headers    = headers;
        this.body       = body;
    }

    public HttpResponseStatusLine getStatusLine() {
        return statusLine;
    }

    public HttpResponseHeaders getHeaders() {
        return headers;
    }

    public HttpResponseMessageBody getBody() {
        return body;
    }
}
