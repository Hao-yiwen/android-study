package com.example.chapter04;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.chapter04.utils.DateUtil;

public class ActRequestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ActRequestActivity";
    ActivityResultLauncher<Intent> register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_request);
        TextView tv_request = findViewById(R.id.tv_request);
        tv_request.setText("待发送的消息为:" + TAG);
        findViewById(R.id.btn_request).setOnClickListener(this);
        TextView tv_response = findViewById(R.id.tv_response);

        register = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result != null) {
                Intent intent = result.getData();
                if (intent != null && result.getResultCode() == RESULT_OK) {
                    Bundle bundle = intent.getExtras();
                    String response_time = bundle.getString("response_time");
                    String response_content = bundle.getString("response_content");
                    tv_response.setText("收到返回消息:\n" + response_time + "\n" + response_content);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ActResponseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("response_time", DateUtil.getNowDateTime());
        bundle.putString("response_content", TAG);
        intent.putExtras(bundle);
        register.launch(intent);
    }
}