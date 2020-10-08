package com.payneteasy.http.server.api.request;

public class HttpRequestHeader {

    private final String name;
    private final String value;

    public HttpRequestHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "HttpRequestHeader{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
