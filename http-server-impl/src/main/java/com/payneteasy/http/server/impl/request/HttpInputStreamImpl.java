package com.payneteasy.http.server.impl.request;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class HttpInputStreamImpl implements IHttpInputStream {

    private final InputStream in;

    public HttpInputStreamImpl(InputStream in) {
        this.in = in;
    }

    @Override
    public String readUntilSpace() throws IOException {
        return readUntilCharacter(' ', "SP");
    }

    private String readUntilCharacter(char aStopCharacter, String aName) throws IOException {
        byte[] buffer = new byte[1024];
        int    count  = -1;

        for(int i=0; i< buffer.length; i++) {
            int value = in.read();
            if(value == -1) {
                throw new EOFException("Cannot read. Reached EOF.");
            }

            if(value == aStopCharacter) {
                count = i;
                break;
            }

            buffer[i] = (byte)value;
        }

        if(count == -1 ) {
            throw new IllegalStateException(aName + " not found");
        }

        return new String(buffer, 0, count);
    }

    @Override
    public String readUntilCrlf() throws IOException {
        String token = readUntilCharacter('\r', "CR");
        int next = in.read();
        if(next != '\n') {
            throw new IllegalStateException("Only CR found but should be CRLF. Next code is " + next + ", char = " + (char)next);
        }
        return token;
    }

    @Override
    public String readerUntilColonOrCrlf() throws IOException {
        byte[] buffer = new byte[1024];
        int    count  = -1;

        for(int i=0; i< buffer.length; i++) {
            int value = in.read();
            if(value == -1) {
                throw new EOFException("Cannot read");
            }

            if(i == 0 && value == '\r') {
                int next = in.read();
                if(next != '\n') {
                    throw new IllegalStateException("Should be LF but was " + (char)(next));
                }
                return null;
            }

            if(value == ':') {
                count = i;
                break;
            }

            buffer[i] = (byte)value;
        }

        if(count == -1 ) {
            throw new IllegalStateException("'=' not found");
        }

        return new String(buffer, 0, count);
    }

    @Override
    public byte[] readBytes(int aLength) throws IOException {
        byte[] buffer = new byte[aLength];

        int offset = 0;
        int count;
        while ( ( count = in.read(buffer, offset, aLength - offset)) >= 0) {
            offset += count;
            if(offset >= aLength) {
                break;
            }
        }

        return buffer;
    }
}
