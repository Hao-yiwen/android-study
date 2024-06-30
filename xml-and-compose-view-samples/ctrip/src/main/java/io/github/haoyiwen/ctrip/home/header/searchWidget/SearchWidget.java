package io.github.haoyiwen.ctrip.home.header.searchWidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchWidget extends View {
    private Paint paint;
    private int backgroundColor = Color.WHITE;

    public SearchWidget(Context context) {
        super(context);
        init();
    }

    public SearchWidget(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
//        paint.setColor(backgroundColor);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(backgroundColor);
        float radius = 50f; // 圆角半径
        canvas.drawRoundRect(new RectF(0, 100, 100, 300), radius, radius, paint);

        // 绘制按钮文本（可选）
        paint.setColor(Color.BLACK);
        paint.setTextSize(50); // 调整文本大小
        String text = "Custom Button";
        float textWidth = paint.measureText(text);

        // 获取文本绘制时的偏移值以垂直居中
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float textY = (getHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;

        // 垂直居中绘制文本
        canvas.drawText(text, (getWidth() - textWidth) / 2, textY, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // do some tap action
                invalidate();
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                invalidate();
                performClick();
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
