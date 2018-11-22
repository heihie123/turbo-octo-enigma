package com.example.latte_core.detegates.web.event;

import java.util.HashMap;

import androidx.annotation.NonNull;

public class EventManager {

    private static final HashMap<String, Event> EVENTS = new HashMap<>();

    private EventManager() {

    }

    public static EventManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final EventManager INSTANCE = new EventManager();
    }

    public Event createEvent(@NonNull String action) {
        final Event event = EVENTS.get(action);
        if (event == null) {
            return new UndefineEvent();
        }
        return event;
    }
}
