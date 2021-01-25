package com.payneteasy.http.server.impl;

import com.payneteasy.http.server.api.handler.IHttpRequestHandler;
import com.payneteasy.http.server.api.request.HttpRequest;
import com.payneteasy.http.server.api.request.HttpRequestHeaders;
import com.payneteasy.http.server.api.request.HttpRequestLine;
import com.payneteasy.http.server.api.request.HttpRequestMessageBody;
import com.payneteasy.http.server.api.response.HttpResponse;
import com.payneteasy.http.server.impl.request.*;
import com.payneteasy.http.server.impl.response.HttpResponseStreamImpl;
import com.payneteasy.http.server.impl.response.IHttpResponseStream;
import com.payneteasy.http.server.log.IHttpLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class HttpClientTask implements Runnable {

    private final Socket                       socket;
    private final IHttpLogger                  logger;
    private final HttpRequestLineParser        requestLineParser;
    private final HttpRequestHeadersParser     requestHeadersParser;
    private final HttpRequestMessageBodyParser requestBodyParser;
    private final IHttpRequestHandler          requestHandler;

    public HttpClientTask(Socket aSocket, IHttpLogger aLogger, IHttpRequestHandler aHandler) {
        socket               = aSocket;
        logger               = aLogger;
        requestLineParser    = new HttpRequestLineParser(aLogger);
        requestHeadersParser = new HttpRequestHeadersParser(aLogger);
        requestBodyParser    = new HttpRequestMessageBodyParser(aLogger);
        requestHandler       = aHandler;
    }

    @Override
    public void run() {
        try {

            processStreams(socket.getInputStream(), socket.getOutputStream(), (InetSocketAddress) socket.getRemoteSocketAddress());

        } catch (Exception e) {
            logger.error("Error while processing client", e);
        } finally {
            closeSocket();
        }
    }

    private void processStreams(InputStream aIn, OutputStream aOut, InetSocketAddress aAddress) throws IOException {
        IHttpInputStream       httpInputStream = new HttpInputStreamImpl(aIn);
        HttpRequestLine        requestLine     = requestLineParser.parseRequestLine(httpInputStream);
        HttpRequestHeaders     requestHeaders  = requestHeadersParser.parseHeaders(httpInputStream);
        HttpRequestMessageBody requestBody     = requestBodyParser.parseMessageBody(httpInputStream, requestHeaders);
        HttpRequest            request         = new HttpRequest(requestLine, requestHeaders, requestBody, aAddress);

        logger.debug("Request", "line", requestLine, "headers", requestHeaders, "body", requestBody);

        HttpResponse        response       = requestHandler.handleRequest(request);
        IHttpResponseStream responseStream = new HttpResponseStreamImpl(aOut);

        responseStream.writeStatusLine  ( response.getStatusLine()                  );
        responseStream.writeHeaders     ( response.getHeaders(), response.getBody() );
        responseStream.writeBody        ( response.getBody()                        );

        logger.debug("Wrote response", "status", response.getStatusLine(), "headers", response.getHeaders(), "body", response.getBody());
    }

    private void closeSocket() {
        logger.debug("Closing connection");
        try {
            socket.close();
        } catch (IOException e) {
            logger.error("Cannot close socket", e);
        }
    }
}
