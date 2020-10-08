package com.payneteasy.http.server.api.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestHeaders {

    private final Map<String, String> headers;

    public HttpRequestHeaders(List<HttpRequestHeader> aHeaders) {
        headers = new HashMap<>(aHeaders.size());

        for (HttpRequestHeader header : aHeaders) {
            headers.put(header.getName().toLowerCase(), header.getValue());
        }
    }

    public int getContentLength() {
        return getRequiredInt("content-length");
    }

    public String getString(String aName) {
        return headers.get(normalizeName(aName));
    }

    public String getRequiredString(String aName) {
        String value = headers.get(normalizeName(aName));
        if(value == null) {
            throw new IllegalStateException("Header " + aName + " not found");
        }
        return value;
    }

    public int getRequiredInt(String aName) {
        return Integer.parseInt(getRequiredString(aName));
    }

    private String normalizeName(String aName) {
        return aName.toLowerCase();
    }

    @Override
    public String toString() {
        return "HttpRequestHeaders{" +
                "headers=" + headers +
                '}';
    }
}
