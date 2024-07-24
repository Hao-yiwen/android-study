package com.yiwen.java_view_other.moshiTest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomDrawableView extends View {
    private ShapeDrawable drawable;

    public CustomDrawableView(Context context) {
        this(context, null);
    }

    public CustomDrawableView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomDrawableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        int x = 10;
        int y = 10;
        int width = 300;
        int height = 50;
        setContentDescription("测试");

        drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(0xff74AC23);
        drawable.setBounds(x, y, x + width, y + height);

    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        drawable.draw(canvas);
    }
}
