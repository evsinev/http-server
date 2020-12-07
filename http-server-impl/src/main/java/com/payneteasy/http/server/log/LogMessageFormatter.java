package com.payneteasy.http.server.log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

public class LogMessageFormatter {

    public String format(String aMessage, Object... aKeysValues) {
        StringBuilder sb = new StringBuilder();
        sb.append(aMessage);

        if(aKeysValues == null || aKeysValues.length == 0) {
            return sb.toString();

        }

        sb.append(" [ ");
        for(int i=0; i<aKeysValues.length; i+=2) {
            if(i > 0) {
                sb.append(", ");

            }
            Object key = aKeysValues[i];
            Object value = aKeysValues[i + 1];
            sb.append(key).append(" = ").append(value);
        }

        sb.append(" ]");
        return sb.toString();
    }

    public String formatException(String aMessage, Exception aException) {
        StringWriter out    = new StringWriter();
        PrintWriter  writer = new PrintWriter(out);
        aException.printStackTrace(writer);
        writer.flush();

        return aMessage +
                "\n" +
                out.toString();
    }
}
