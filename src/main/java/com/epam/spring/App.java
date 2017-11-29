package com.epam.spring;

import com.epam.spring.beans.Client;
import com.epam.spring.loggers.Event;
import com.epam.spring.loggers.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;
    private EventLogger eventLogger;

    void logEvent(Event event) {
        String message = event.getMsg().replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public EventLogger getEventLogger() {
        return eventLogger;
    }

    public void setEventLogger(EventLogger eventLogger) {
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) context.getBean("app");
        Event event1 = (Event) context.getBean("event");
        event1.setMsg("Some event for 1");

        Event event2 = (Event) context.getBean("event");
        event2.setMsg("Some event for 2");

        app.logEvent(event1);
        app.logEvent(event2);
    }
}
