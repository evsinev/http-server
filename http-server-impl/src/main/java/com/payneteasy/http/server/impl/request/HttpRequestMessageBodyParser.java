package com.payneteasy.http.server.impl.request;

import com.payneteasy.http.server.log.IHttpLogger;
import com.payneteasy.http.server.api.request.HttpRequestHeaders;
import com.payneteasy.http.server.api.request.HttpRequestMessageBody;

import java.io.IOException;

public class HttpRequestMessageBodyParser {

    private final IHttpLogger logger;

    public HttpRequestMessageBodyParser(IHttpLogger aLogger) {
        logger = aLogger;
    }

    public HttpRequestMessageBody parseMessageBody(IHttpInputStream aStream, HttpRequestHeaders aHeaders) throws IOException {
        int    length = aHeaders.getContentLength();
        byte[] bytes  = aStream.readBytes(length);
        return new HttpRequestMessageBody(bytes);
    }
}
