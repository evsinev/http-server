package com.payneteasy.http.server.log;

public interface IHttpLogger {

    void debug(String aMessage, Object ... aKeysValues);

    void error(String aMessage);

    void error(String aMessage, Exception aException);
}
