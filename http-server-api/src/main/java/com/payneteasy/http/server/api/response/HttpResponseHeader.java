package com.payneteasy.http.server.api.response;

public class HttpResponseHeader {

    private final String name;
    private final String value;

    public HttpResponseHeader(String name, String value) {
        this.name  = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + '=' + value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
