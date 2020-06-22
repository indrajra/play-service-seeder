package org.sunbird;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;


public class HelloWord {


    private final Logger log = LoggerFactory.getLogger(getClass());


    private HelloWord() {
    }

    /**
     * request Id
     * @param trace
     */
    public HelloWord(Map<String, Object> trace) {
        MDC.put("msgId", (String) trace.get("msgId"));
    }


    public void printHello() {
        log.info("Hello world");
    }
}
