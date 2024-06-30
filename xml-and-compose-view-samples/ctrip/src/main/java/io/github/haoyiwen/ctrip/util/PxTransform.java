package io.github.haoyiwen.ctrip.util;

import android.content.Context;

public class PxTransform {
    public static int pxToDp(int px, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(px / density);
    }

    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}
