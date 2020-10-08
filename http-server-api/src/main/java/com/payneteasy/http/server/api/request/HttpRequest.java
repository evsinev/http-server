package com.payneteasy.http.server.api.request;

public class HttpRequest {

    private final HttpRequestLine        requestLine;
    private final HttpRequestHeaders     headers;
    private final HttpRequestMessageBody body;

    public HttpRequest(HttpRequestLine requestLine, HttpRequestHeaders headers, HttpRequestMessageBody body) {
        this.requestLine = requestLine;
        this.headers     = headers;
        this.body        = body;
    }

    public HttpRequestLine getRequestLine() {
        return requestLine;
    }

    public HttpRequestHeaders getHeaders() {
        return headers;
    }

    public HttpRequestMessageBody getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "requestLine=" + requestLine +
                ", headers=" + headers +
                ", body=" + body +
                '}';
    }
}


