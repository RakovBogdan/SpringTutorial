package com.epam.spring.loggers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache = new ArrayList<>();

    public CacheFileEventLogger(int cacheSize, String fileName) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event) {
        cache.add(event);

        if (cacheSize == cache.size()) {
            writeEventsFromCache();
            cache.clear();
        }
    }

    @Override
    public void init() throws IOException {
        super.init();
    }


    public void destroy() {
        if (!cache.isEmpty()) {
            writeEventsFromCache();
        }
        cache.clear();
    }

    private void writeEventsFromCache() {
        for (Event event: cache) {
            super.logEvent(event);
        }
    }

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
}
