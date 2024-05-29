package io.github.haoyiwen.test.core.router;

import io.github.haoyiwen.test.core.bus.events.URLEvent;

public interface URLHandlerListener {
    void onURLEvent(URLEvent event);
}
