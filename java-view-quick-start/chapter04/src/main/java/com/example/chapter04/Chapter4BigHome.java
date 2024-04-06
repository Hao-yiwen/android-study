package com.example.chapter04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Chapter4BigHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter4_big_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_act_response = findViewById(R.id.btn_act_response);

        Button btn_act_receiver = findViewById(R.id.btn_act_receive);

        Button jump_first = findViewById(R.id.jump_first);

        Button login_input = findViewById(R.id.login_input);

        Button act_reponse = findViewById(R.id.act_reponse);

        Button act_start = findViewById(R.id.act_start);

        Button act_finish = findViewById(R.id.act_finish);

        Button act_uri = findViewById(R.id.act_uri);

        Button act_request = findViewById(R.id.act_request);

        Button act_send = findViewById(R.id.act_send);

        Button read_string = findViewById(R.id.read_string);

        btn_act_response.setOnClickListener(this);
        btn_act_receiver.setOnClickListener(this);
        jump_first.setOnClickListener(this);
        login_input.setOnClickListener(this);
        act_reponse.setOnClickListener(this);
        act_start.setOnClickListener(this);
        act_finish.setOnClickListener(this);
        act_uri.setOnClickListener(this);
        act_request.setOnClickListener(this);
        act_send.setOnClickListener(this);
        read_string.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.btn_act_response) {
            intent = new Intent(this, ActResponseActivity.class);
        } else if (v.getId() == R.id.btn_act_receive) {
            intent = new Intent(this, ActReceiveActivity.class);
        } else if (v.getId() == R.id.jump_first) {
            intent = new Intent(this, JumpFirstActivity.class);
        } else if (v.getId() == R.id.login_input) {
            intent = new Intent(this, LoginInputActivity.class);
        } else if (v.getId() == R.id.act_reponse) {
            intent = new Intent(this, MetaDataActivity.class);
        } else if (v.getId() == R.id.act_start) {
            intent = new Intent(this, ActStartActivity.class);
        } else if (v.getId() == R.id.act_finish) {
            intent = new Intent(this, ActFinshActivity.class);
        } else if (v.getId() == R.id.act_uri) {
            intent = new Intent(this, ActionUriActivity.class);
        } else if (v.getId() == R.id.act_request) {
            intent = new Intent(this, ActRequestActivity.class);
        } else if (v.getId() == R.id.act_send) {
            intent = new Intent(this, ActSendActivity.class);
        } else if (v.getId() == R.id.read_string) {
            intent = new Intent(this, ReadStringActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}