package com.payneteasy.http.server.api.request;

import java.net.InetSocketAddress;

public class HttpRequest {

    private final HttpRequestLine        requestLine;
    private final HttpRequestHeaders     headers;
    private final HttpRequestMessageBody body;
    private final InetSocketAddress      remoteAddress;

    public HttpRequest(HttpRequestLine requestLine, HttpRequestHeaders headers, HttpRequestMessageBody body, InetSocketAddress aRemoteAddress) {
        this.requestLine = requestLine;
        this.headers     = headers;
        this.body        = body;
        remoteAddress    = aRemoteAddress;
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

    public InetSocketAddress getRemoteAddress() {
        return remoteAddress;
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "requestLine=" + requestLine +
                ", headers=" + headers +
                ", body=" + body +
                ", remoteAddress='" + remoteAddress + '\'' +
                '}';
    }
}


