package io.github.haoyiwen.test.core.bus;

import org.greenrobot.eventbus.EventBus;

public class EventBusManager {
    private static EventBusManager instance;
    private EventBus eventBus;

    private EventBusManager() {
        eventBus = EventBus.builder().build();
    }

    public static synchronized EventBusManager getInstance() {
        if (instance == null) {
            instance = new EventBusManager();
        }
        return instance;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public void register(Object object) {
        if (!eventBus.isRegistered(object)) {
            eventBus.register(object);
        }
    }

    public void unregister(Object object) {
        if (eventBus.isRegistered(object)) {
            eventBus.unregister(object);
        }
    }

    public void post(Object object) {
        eventBus.post(object);
    }

}
