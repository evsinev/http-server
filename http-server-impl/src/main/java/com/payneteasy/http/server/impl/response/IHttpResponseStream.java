package com.payneteasy.http.server.impl.response;

import com.payneteasy.http.server.api.response.HttpResponseHeaders;
import com.payneteasy.http.server.api.response.HttpResponseMessageBody;
import com.payneteasy.http.server.api.response.HttpResponseStatusLine;

import java.io.IOException;

public interface IHttpResponseStream {

    void writeStatusLine(HttpResponseStatusLine aStatusLine) throws IOException;

    void writeHeaders(HttpResponseHeaders aHeaders) throws IOException;

    void writeBody(HttpResponseMessageBody aBody) throws IOException;
}
