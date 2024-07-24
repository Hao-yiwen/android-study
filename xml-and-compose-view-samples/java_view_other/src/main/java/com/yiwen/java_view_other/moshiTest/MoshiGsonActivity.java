package com.yiwen.java_view_other.moshiTest;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.yiwen.java_view_other.R;

import org.w3c.dom.Text;

import java.io.IOException;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class MoshiGsonActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_moshi_gson;
    }

    @Override
    protected String setTitle() {
        return "MoshiGsonActivity";
    }

    @Override
    public void initView() {
        TextView tv_gson = findViewById(R.id.tv_gson);
        TextView tv_moshi = findViewById(R.id.tv_moshi);
        // 模拟json数据
        String json = "{\"name\":\"Tom\",\"age\":3,\"isMale\":true}";
        Gson gson = new Gson();
        Cat cat = gson.fromJson(json, Cat.class);
        Logger.i("CatInfo" + cat.toString());
        tv_gson.setText("cat: " + cat.toString());
        tv_gson.setGravity(Gravity.CENTER);

        // moshi处理
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Cat> jsonAdapter = moshi.adapter(Cat.class);
        Cat cat1 = null;
        try {
            cat1 = jsonAdapter.fromJson(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LinearLayout ll = (LinearLayout) tv_gson.getParent();
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.CENTER_VERTICAL);

        Logger.i("Cat1Info" + cat1.toString());
        tv_moshi.setText("cat1: " + cat1.toString());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 40, 0, 0);
        layoutParams.gravity = Gravity.CENTER;
        tv_moshi.setLayoutParams(layoutParams);
        tv_moshi.setGravity(Gravity.CENTER);
        tv_moshi.setBackgroundColor(getResources().getColor(R.color.pink));


        CustomDrawableView cv = findViewById(R.id.cv_draw);
        Logger.i("cv: " + cv);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
        lp.setMargins(0, 40, 0, 0);
        if(cv != null) {
            cv.setBackgroundColor(Color.BLUE);
            cv.setLayoutParams(lp);
        }

        View view = findViewById(R.id.view_only);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200);
        lp1.setMargins(0, 40, 0, 0);
        view.setLayoutParams(lp1);
    }
}