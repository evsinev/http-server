package com.payneteasy.http.server.api.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestHeaders {

    private final List<HttpRequestHeader> originalHeaders;
    private final Map<String, String>     headers;

    public HttpRequestHeaders(List<HttpRequestHeader> aHeaders) {
        originalHeaders = aHeaders;
        headers = new HashMap<>(aHeaders.size());

        for (HttpRequestHeader header : aHeaders) {
            headers.put(header.getName().toLowerCase(), header.getValue());
        }
    }

    public int getContentLength() {
        return getInt("content-length", 0);
    }

    public String getString(String aName) {
        return headers.get(normalizeName(aName));
    }

    public int getInt(String aName, int aDefaultValue) {
        String value = headers.get(normalizeName(aName));
        return value == null || value.isEmpty() ? aDefaultValue : Integer.parseInt(value);
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

    public List<HttpRequestHeader> getOriginalHeaders() {
        return originalHeaders;
    }

    @Override
    public String toString() {
        return "HttpRequestHeaders{" +
                "headers=" + headers +
                '}';
    }
}
