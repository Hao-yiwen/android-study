package com.example.chapter05;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AlertDialogActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        tv_result = findViewById(R.id.tv_result);
        findViewById(R.id.btn_alert_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("尊贵的用户");
        builder.setMessage("您是否要卸载此应用？");

        builder.setPositiveButton("残忍卸载", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_result.setText("残忍卸载");
            }
        });

        builder.setNegativeButton("我再想想", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_result.setText("我再想想");
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}