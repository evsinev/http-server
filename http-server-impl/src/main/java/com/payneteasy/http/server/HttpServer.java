package com.payneteasy.http.server;

import com.payneteasy.http.server.api.handler.IHttpRequestHandler;
import com.payneteasy.http.server.impl.HttpClientTask;
import com.payneteasy.http.server.log.IHttpLogger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class HttpServer {

    private final ServerSocket        serverSocket;
    private final AtomicBoolean       isRunning = new AtomicBoolean(true);
    private final IHttpLogger         log;
    private final ExecutorService     executorService;
    private final IHttpRequestHandler handler;
    private final int                 readTimeoutMs;

    public HttpServer(
              InetSocketAddress   aBindAddress
            , IHttpLogger         aLog
            , ExecutorService     aExecutor
            , IHttpRequestHandler aHandler
            , int                 aReadTimeoutMs
    ) throws IOException {
        log             = aLog;
        executorService = aExecutor;
        handler         = aHandler;
        readTimeoutMs   = aReadTimeoutMs;

        serverSocket = new ServerSocket();
        serverSocket.setReuseAddress(true);
        serverSocket.bind(aBindAddress);
    }

    public void acceptSocketAndWait() {
        log.debug("Start listening...", "serverSocket", serverSocket);
        while (!Thread.currentThread().isInterrupted() && isRunning.get()) {
            try {
                Socket clientSocket = serverSocket.accept();
                clientSocket.setSoTimeout(readTimeoutMs);
                clientSocket.setTcpNoDelay(true);

                log.debug("Connected from client", "remote address", clientSocket.getRemoteSocketAddress());

                executorService.execute(new HttpClientTask(clientSocket, log, handler));

            } catch (Exception e) {
                if(Thread.currentThread().isInterrupted() || !isRunning.get()) {
                    log.error("Exiting from the listening server socket ...");
                    break;
                }
                log.error("Cannot deal with socket", e);
            }
        }
        log.debug("Exited from accept loop.");
    }

    public void stop() {
        log.debug("Stopping ...");
        isRunning.set(false);
        try {
            serverSocket.close();
        } catch (Exception e) {
            log.error("Cannot close server socket", e);
        }
    }

}
