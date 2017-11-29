package com.epam.spring.loggers;

import com.epam.spring.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event) {
        System.out.println(event);
    }
}
