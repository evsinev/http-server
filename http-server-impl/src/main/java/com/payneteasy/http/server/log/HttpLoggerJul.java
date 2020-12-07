package com.payneteasy.http.server.log;

import java.util.logging.Logger;

public class HttpLoggerJul implements IHttpLogger {

    private final LogMessageFormatter formatter = new LogMessageFormatter();
    private final Logger              logger;

    public HttpLoggerJul(String aLoggerName) {
        logger = Logger.getLogger(aLoggerName);
    }

    @Override
    public void debug(String aMessage, Object... aKeysValues) {
        logger.fine(formatter.format(aMessage, aKeysValues));
    }

    @Override
    public void error(String aMessage) {
        logger.severe(formatter.format(aMessage));
    }

    @Override
    public void error(String aMessage, Exception aException) {
        logger.severe(formatter.formatException(aMessage, aException));
    }
}
