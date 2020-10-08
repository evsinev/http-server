package com.payneteasy.http.server.api.response;

import static java.nio.charset.StandardCharsets.US_ASCII;

public class HttpResponseStatusLine {

    public static final HttpResponseStatusLine OK          = new HttpResponseStatusLine(200, "OK");
    public static final HttpResponseStatusLine BAD_REQUEST = new HttpResponseStatusLine(400, "Bad Request");

    private final int    statusCode;
    private final String reasonPhrase;
    private final byte[] statusCodeBytes;
    private final byte[] reasonPhraseBytes;

    public HttpResponseStatusLine(int statusCode, String reasonPhrase) {
        this.statusCode   = statusCode;
        this.reasonPhrase = reasonPhrase;
        statusCodeBytes   = ("" + statusCode).getBytes(US_ASCII);
        reasonPhraseBytes = reasonPhrase.getBytes(US_ASCII);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public byte[] getStatusCodeAsBytes() {
        return statusCodeBytes;
    }

    public byte[] getReasonPhraseAsBytes() {
        return reasonPhraseBytes;
    }

    @Override
    public String toString() {
        return "HTTP/1.1 " + statusCode + " " + reasonPhrase;
    }
}
