package com.payneteasy.http.server.impl.response;

import com.payneteasy.http.server.api.response.HttpResponseHeader;
import com.payneteasy.http.server.api.response.HttpResponseHeaders;
import com.payneteasy.http.server.api.response.HttpResponseMessageBody;
import com.payneteasy.http.server.api.response.HttpResponseStatusLine;

import java.io.IOException;
import java.io.OutputStream;

import static java.nio.charset.StandardCharsets.US_ASCII;

public class HttpResponseStreamImpl implements IHttpResponseStream {

    private static final byte[] HTTP_1_1 = "HTTP/1.1" .getBytes(US_ASCII);
    private static final byte[] SP       = " "        .getBytes(US_ASCII);
    private static final byte[] CRLF     = "\r\n"     .getBytes(US_ASCII);
    private static final byte[] COLON    = ": "       .getBytes(US_ASCII);

    private final OutputStream out;

    public HttpResponseStreamImpl(OutputStream aOut) {
        out = aOut;
    }

    @Override
    public void writeStatusLine(HttpResponseStatusLine aStatusLine) throws IOException {
        out.write(HTTP_1_1);
        out.write(SP);
        out.write(aStatusLine.getStatusCodeAsBytes());
        out.write(SP);
        out.write(aStatusLine.getReasonPhraseAsBytes());
        out.write(CRLF);
        out.flush();
    }

    @Override
    public void writeHeaders(HttpResponseHeaders aHeaders, HttpResponseMessageBody body) throws IOException {
        for (HttpResponseHeader header : aHeaders.getHeaders()) {
            writeHeader(header);
        }
        if(body.getBytes() != null && body.getBytes().length > 0) {
            writeHeader(new HttpResponseHeader("Content-Length", String.valueOf(body.getBytes().length)));
        }
        writeHeader(new HttpResponseHeader("Connection", "Close"));

        out.write(CRLF);
        out.flush();
    }

    private void writeHeader(HttpResponseHeader header) throws IOException {
        out.write(header.getName().getBytes(US_ASCII));
        out.write(COLON);
        out.write(header.getValue().getBytes(US_ASCII));
        out.write(CRLF);
    }

    @Override
    public void writeBody(HttpResponseMessageBody aBody) throws IOException {
        out.write(aBody.getBytes());
        out.flush();
    }
}
