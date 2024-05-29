package io.github.haoyiwen.test.core.router;

import android.content.Context;
import android.net.Uri;

public interface URLHandler {
    boolean canHandle(Uri uri);

    void handle(Context context, Uri uri);
}
