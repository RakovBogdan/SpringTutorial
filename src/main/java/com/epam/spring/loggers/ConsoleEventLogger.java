package com.epam.spring.loggers;

import com.epam.spring.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(String msg) {
        System.out.println(msg);
    }
}
