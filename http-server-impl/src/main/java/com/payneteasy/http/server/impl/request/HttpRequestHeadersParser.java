package com.payneteasy.http.server.impl.request;

import com.payneteasy.http.server.log.IHttpLogger;
import com.payneteasy.http.server.api.request.HttpRequestHeader;
import com.payneteasy.http.server.api.request.HttpRequestHeaders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpRequestHeadersParser {

    private final IHttpLogger logger;

    public HttpRequestHeadersParser(IHttpLogger aLogger) {
        logger = aLogger;
    }

    public HttpRequestHeaders parseHeaders(IHttpInputStream aStream) throws IOException {
        return new HttpRequestHeaders(parseHeadersToList(aStream));
    }

    private List<HttpRequestHeader> parseHeadersToList(IHttpInputStream aInput) throws IOException {
        List<HttpRequestHeader> headers = new ArrayList<>();
        HttpRequestHeader       header;

        while ((header = parseHeader(aInput)) != null) {
            headers.add(header);
        }
        return headers;
    }

    private HttpRequestHeader parseHeader(IHttpInputStream aInput) throws IOException {
        String name = aInput.readerUntilColonOrCrlf();
        if (name == null) {
            return null;
        }
        String value = aInput.readUntilCrlf();

        return new HttpRequestHeader(name.trim(), value.trim());
    }
}
