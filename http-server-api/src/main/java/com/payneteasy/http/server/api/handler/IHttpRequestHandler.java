package com.payneteasy.http.server.api.handler;

import com.payneteasy.http.server.api.request.HttpRequest;
import com.payneteasy.http.server.api.response.HttpResponse;

public interface IHttpRequestHandler {

    HttpResponse handleRequest(HttpRequest aRequest);

}
