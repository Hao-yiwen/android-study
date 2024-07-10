package com.yiwen.java_view_other;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.yiwen.java_view_other.databinding.ActivityBigHomeBinding;
import com.yiwen.java_view_other.databinding.DataBindingActivity;
import com.yiwen.java_view_other.fragmentOfBinding.FragmentOfBindingActivity;
import com.yiwen.java_view_other.model.MessageEvent;
import com.yiwen.java_view_other.rxjava.RxJavaActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import io.github.haoyiwen.test.core.activity.BaseActivity;

public class BigHomeActivity extends BaseActivity implements View.OnClickListener {
    private ActivityBigHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBigHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        findViewById(R.id.btn_qr_code).setOnClickListener(this);

        findViewById(R.id.btn_intent_filter).setOnClickListener(this);

        findViewById(R.id.btn_color_colors).setOnClickListener(this);

        findViewById(R.id.btn_jump_viewModel).setOnClickListener(this);

        findViewById(R.id.btn_jump_animation).setOnClickListener(this);

        binding.btnJumpViewbinding.setOnClickListener(this);

        binding.btnLiveData.setOnClickListener(this);

        binding.btnJumpNavhost.setOnClickListener(this);

        binding.btnJumpWebview.setOnClickListener(this);

        binding.btnJumpLoading.setOnClickListener(this);

        binding.btnJumpEventbus.setOnClickListener(this);

        binding.btnDataBinding.setOnClickListener(this);

        binding.btnRxjava.setOnClickListener(this);

        binding.btnElvation.setOnClickListener(this);

        binding.btnPermission.setOnClickListener(this);

        binding.btnMHandler.setOnClickListener(this);

        binding.btnRelativeLayout.setOnClickListener(this);

        binding.btnFragmentBinding.setOnClickListener(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_big_home;
    }

    @Override
    protected String setTitle() {
        return "BigHomeActivity";
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if (v.getId() == R.id.btn_qr_code) {
            intent.setClass(this, ScanActivity.class);
        } else if (v.getId() == R.id.btn_intent_filter) {
            intent.setClass(this, ShareActivity.class);
        } else if (v.getId() == R.id.btn_color_colors) {
            intent.setClass(this, ColorAndColorsActivity.class);
        } else if (v.getId() == R.id.btn_jump_viewModel) {
            intent.setClass(this, ViewModelWithXml.class);
        } else if (v.getId() == R.id.btn_jump_animation) {
            intent.setClass(this, AnimationActivity.class);
        } else if (v.getId() == R.id.btn_jump_viewbinding) {
            intent.setClass(this, ViewBindingActivity.class);
        } else if (v.getId() == R.id.btn_live_data) {
            intent.setClass(this, LiveDataActivity.class);
        } else if (v.getId() == R.id.btn_jump_navhost) {
            intent.setClass(this, NavigationFragmentActivity.class);
        } else if(v.getId() == R.id.btn_jump_webview){
            intent.setClass(this, WebviewActivity.class);
        } else if(v.getId() == R.id.btn_jump_loading){
            intent.setClass(this, LoadingActivity.class);
        } else if(v.getId() == R.id.btn_jump_eventbus){
            intent.setClass(this, EventBusActivity.class);
        } else if(v.getId() == R.id.btn_data_binding){
            intent.setClass(this, DataBindingActivity.class);
        } else if(v.getId() == R.id.btn_rxjava){
            intent.setClass(this, RxJavaActivity.class);
        } else if(v.getId() == R.id.btn_permission){
            intent.setClass(this, PermissonTestActivity.class);
        } else if(v.getId() == R.id.btn_mHandler){
            intent.setClass(this, HandlerTestActivity.class);
        } else if(v.getId() == R.id.btn_elvation){
            intent.setClass(this, ElevationActivity.class);
        } else if(v.getId() == R.id.btn_relative_layout){
            intent.setClass(this, RelativeLayoutActivity.class);
        } else if(v.getId() == R.id.btn_fragment_binding){
            intent.setClass(this, FragmentOfBindingActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Button btn = (Button)findViewById(R.id.btn_jump_eventbus);
        if (btn != null) {
            btn.setText(event.message);
        }
        // 移除Sticky事件，以避免多次处理
        EventBus.getDefault().removeStickyEvent(event);
    }
}