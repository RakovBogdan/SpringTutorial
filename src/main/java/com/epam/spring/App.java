package com.epam.spring;

import com.epam.spring.beans.Client;
import com.epam.spring.loggers.Event;
import com.epam.spring.loggers.EventLogger;
import com.epam.spring.loggers.EventType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static com.epam.spring.loggers.EventType.*;


import java.util.Map;

public class App {

    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> eventLoggerMap;

    void logEvent(Event event, EventType eventType) {
        String message = event.getMsg().replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = eventLoggerMap.get(eventType);

        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }

    public App(Client client, EventLogger defaultLogger, Map<EventType,
            EventLogger> eventLoggerMap) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.eventLoggerMap = eventLoggerMap;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public EventLogger getDefaultLogger() {
        return defaultLogger;
    }

    public void setDefaultLogger(EventLogger defaultLogger) {
        this.defaultLogger = defaultLogger;
    }

    public Map<EventType, EventLogger> getEventLoggerMap() {
        return eventLoggerMap;
    }

    public void setEventLoggerMap(Map<EventType, EventLogger> eventLoggerMap) {
        this.eventLoggerMap = eventLoggerMap;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) context.getBean("app");
        Event event1 = (Event) context.getBean("event");
        event1.setMsg("Some event for 1");

        Event event2 = (Event) context.getBean("event");
        event2.setMsg("Some event for 2");

        Event event3 = (Event) context.getBean("event");
        event3.setMsg("Some event for 3");

        app.logEvent(event1, INFO);
        app.logEvent(event2, ERROR);
        app.logEvent(event3, null);
        context.close();
    }
}
