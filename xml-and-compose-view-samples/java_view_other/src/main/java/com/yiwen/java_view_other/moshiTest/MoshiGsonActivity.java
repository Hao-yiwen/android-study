package com.yiwen.java_view_other.moshiTest;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.graphics.Color;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
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
    ImageView img2;

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
//        ll.setGravity(Gravity.CENTER_VERTICAL);

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
        if (cv != null) {
            cv.setBackgroundColor(Color.BLUE);
            cv.setLayoutParams(lp);
        }

        View view = findViewById(R.id.view_only);
        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(200, 200);
        lp1.setMargins(0, 40, 0, 0);
        view.setLayoutParams(lp1);
        // 在x轴移动
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", 0, 200);
        animator.setDuration(2000);
        animator.start();
        // 在x轴和y轴同时移动
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(view, "translationY", 0, 200);
        ObjectAnimator animatorVisible = ObjectAnimator.ofFloat(view, "alpha", 1, 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animator, animatorY, ObjectAnimator.ofFloat(view, "rotation", 0, 360), animatorVisible);
        animatorSet.setInterpolator(new OvershootInterpolator());
        animatorSet.setDuration(2000);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animatorY.setRepeatMode(ObjectAnimator.REVERSE);
        animatorY.setRepeatCount(ObjectAnimator.INFINITE);
        animatorVisible.setRepeatCount(ObjectAnimator.RESTART);
        animatorVisible.setRepeatMode(ObjectAnimator.RESTART);
        animatorVisible.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setAlpha(1);
            }
        });
        animatorSet.start();
        // xml定义视图动画
        View view2 = findViewById(R.id.view_two);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        view2.startAnimation(animation);

        View view3 = findViewById(R.id.view_three);
        AnimatorSet animatorSet1 = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.rotation);
        animatorSet1.setTarget(view3);
        animatorSet1.start();

        ImageView img1 = findViewById(R.id.iv_heart);
        img1.setImageResource(R.drawable.anim_heart);
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) img1.getDrawable();
        drawable.start();

        img2 = findViewById(R.id.iv_shengdan);
        img2.setImageResource(R.drawable.anim_shendan);
        img2.setPadding(20, 0, 0, 20);
        AnimatedVectorDrawable drawable1 = (AnimatedVectorDrawable) img2.getDrawable();
        drawable1.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        img2.post(new Runnable() {
            @Override
            public void run() {
                Logger.i("img2 position: " + img2.getLeft() + " " + img2.getTop() + " " + img2.getRight() + " " + img2.getBottom() + " " + img2.getX());
                Logger.i("img2 measure: " + img2.getMeasuredHeight() + " " + img2.getMeasuredWidth() + " " + img2.getWidth() + " " + img2.getHeight());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}