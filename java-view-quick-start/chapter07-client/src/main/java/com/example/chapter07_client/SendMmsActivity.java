package com.example.chapter07_client;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class SendMmsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_appendix;
    private ActivityResultLauncher<Intent> mResultLauncher;
    private EditText et_title;
    private EditText et_message;
    private EditText et_phone;

    private Uri picUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mms);
        et_title = findViewById(R.id.et_title);
        et_phone = findViewById(R.id.et_phone);
        et_message = findViewById(R.id.et_message);
        iv_appendix = findViewById(R.id.iv_appendix);
        iv_appendix.setOnClickListener(this);
        findViewById(R.id.btn_send_mms).setOnClickListener(this);

        mResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {

            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent intent = result.getData();
                    picUri = intent.getData();
                    if (picUri != null) {
                        iv_appendix.setImageURI(picUri);
                        Log.d("ning", "picUri: " + picUri);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_appendix) {
            // 选择附件
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            // 设置内容类型为图片类型
            intent.setType("image/*");
            mResultLauncher.launch(intent);
        } else if (v.getId() == R.id.btn_send_mms) {
            // 发送彩信
            sendMms();
        }
    }

    private void sendMms() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra("address", et_phone.getText().toString());
        intent.putExtra("subject", et_title.getText().toString());
        intent.putExtra("sms_body", et_message.getText().toString());
        intent.putExtra(Intent.EXTRA_STREAM, picUri);
        intent.setType("image/*");
        startActivity(intent);
    }
}