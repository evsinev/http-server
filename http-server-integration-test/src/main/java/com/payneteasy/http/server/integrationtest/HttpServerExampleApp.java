package com.payneteasy.http.server.integrationtest;

import com.payneteasy.http.server.HttpServer;
import com.payneteasy.http.server.api.handler.IHttpRequestHandler;
import com.payneteasy.http.server.api.response.HttpResponseStatusLine;
import com.payneteasy.http.server.impl.response.HttpResponseBuilder;
import com.payneteasy.http.server.log.HttpLoggerSystemOut;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HttpServerExampleApp {

    public static void main(String[] args) throws IOException {
        IHttpRequestHandler handler = aRequest -> HttpResponseBuilder.status(HttpResponseStatusLine.OK)
                .addHeader("Server", "Test")
                .body("Hello".getBytes())
                .build();

        HttpServer server = new HttpServer(
                new InetSocketAddress(8081)
                , new HttpLoggerSystemOut()
                , Executors.newCachedThreadPool()
                , handler
                , 10_000
        );
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        server.acceptSocketAndWait();
    }
}
