package com.payneteasy.http.server.impl.request;

import com.payneteasy.http.server.log.IHttpLogger;
import com.payneteasy.http.server.api.request.HttpRequestLine;

import java.io.IOException;

public class HttpRequestLineParser {

    private final IHttpLogger logger;

    public HttpRequestLineParser(IHttpLogger aLogger) {
        logger = aLogger;
    }

    public HttpRequestLine parseRequestLine(IHttpInputStream aStream) throws IOException {

        String method  = aStream.readUntilSpace();
        String uri     = aStream.readUntilSpace();
        String version = aStream.readUntilCrlf();

        return new HttpRequestLine(method, uri, version);
    }
}
