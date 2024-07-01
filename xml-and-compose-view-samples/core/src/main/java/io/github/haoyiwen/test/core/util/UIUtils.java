package io.github.haoyiwen.test.core.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import io.github.haoyiwen.test.core.app.BaseApp;

public class UIUtils {

    public static Toast mToast;

    public static void showToast(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

    public static void showToast(String msg, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(BaseApp.getContext(), "", duration);
        }
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * 收起软键盘
     */
    public static void hideInput(View view, Context context){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
