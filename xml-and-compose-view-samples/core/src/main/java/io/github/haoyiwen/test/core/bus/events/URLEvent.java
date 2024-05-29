package io.github.haoyiwen.test.core.bus.events;

import android.content.Context;

public class URLEvent {
    public final Context context;
    public final String url;

    public URLEvent(Context context, String url) {
        this.url = url;
        this.context = context;
    }
}
