package com.payneteasy.http.server.api.response;

public class HttpResponseMessageBody {

    private final byte[] bytes;

    public HttpResponseMessageBody(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "HttpResponseMessageBody{" +
                "bytes.length=" + bytes.length +
                '}';
    }

    public byte[] getBytes() {
        return bytes;
    }
}
