package com.payneteasy.http.server.api.request;

public class HttpRequestLine {

    private final String method;
    private final String requestUri;
    private final String httpVersion;

    public HttpRequestLine(String method, String requestUri, String httpVersion) {
        this.method = method;
        this.requestUri = requestUri;
        this.httpVersion = httpVersion;
    }

    public String getMethod() {
        return method;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    @Override
    public String toString() {
        return method + ' ' + requestUri + ' ' + httpVersion;
    }
}
