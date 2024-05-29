package io.github.haoyiwen.test.core.router;

import android.content.Context;
import android.net.Uri;

public interface URLHandler {
    boolean canHandle(String url);

    void handle(Context context, String url);
}
