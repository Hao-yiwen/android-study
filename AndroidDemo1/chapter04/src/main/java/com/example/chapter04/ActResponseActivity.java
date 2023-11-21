package com.example.chapter04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.chapter04.utils.DateUtil;

import org.w3c.dom.Text;

public class ActResponseActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ActResponseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_response);
        TextView tv_request = findViewById(R.id.tv_request);
        Bundle bundle = getIntent().getExtras();
        String responseTime = bundle.getString("response_time");
        String responseContent = bundle.getString("response_content");
        String desc = String.format("收到请求时间：%s\n请求内容：%s", responseTime, responseContent);
        tv_request.setText(desc);

        findViewById(R.id.btn_response).setOnClickListener(this);

        TextView tv_response = findViewById(R.id.tv_response);
        tv_response.setText("待发送的消息为:" + TAG);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("response_time", DateUtil.getNowDateTime());
        bundle.putString("response_content", TAG);
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
    }
}