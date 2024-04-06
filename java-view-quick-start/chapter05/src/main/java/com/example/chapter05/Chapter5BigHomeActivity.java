package com.example.chapter05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Chapter5BigHomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter5_big_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button timer_picker_activity = findViewById(R.id.timer_picker_activity);

        Button date_picker_activity = findViewById(R.id.date_picker_activity);

        Button alert_dialog_activity = findViewById(R.id.alert_dialog_activity);

        Button edit_hide_activity = findViewById(R.id.edit_hide_activity);

        Button edit_focus_activity = findViewById(R.id.edit_focus_activity);

        Button edit_border_activity = findViewById(R.id.edit_border_activity);

        Button edit_simple_activity = findViewById(R.id.edit_simple_activity);

        Button radio_horizontal_activity = findViewById(R.id.radio_horizontal_activity);

        Button check_box_activity = findViewById(R.id.check_box_activity);

        Button drawable_state_activity = findViewById(R.id.drawable_state_activity);

        Button drawable_nine_activity = findViewById(R.id.drawable_nine_activity);

        Button login_main_activity = findViewById(R.id.login_main_activity);

        timer_picker_activity.setOnClickListener(this);

        date_picker_activity.setOnClickListener(this);

        alert_dialog_activity.setOnClickListener(this);

        edit_hide_activity.setOnClickListener(this);

        edit_focus_activity.setOnClickListener(this);

        edit_border_activity.setOnClickListener(this);

        edit_simple_activity.setOnClickListener(this);

        radio_horizontal_activity.setOnClickListener(this);

        check_box_activity.setOnClickListener(this);

        drawable_state_activity.setOnClickListener(this);

        drawable_nine_activity.setOnClickListener(this);

        login_main_activity.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.timer_picker_activity) {
            intent = new Intent(this, TimerPickerActivity.class);
        } else if (v.getId() == R.id.date_picker_activity) {
            intent = new Intent(this, DatePickerActivity.class);
        } else if (v.getId() == R.id.alert_dialog_activity) {
            intent = new Intent(this, AlertDialogActivity.class);
        } else if (v.getId() == R.id.edit_hide_activity) {
            intent = new Intent(this, EditHideActivity.class);
        } else if (v.getId() == R.id.edit_focus_activity) {
            intent = new Intent(this, EditFocusActivity.class);
        } else if (v.getId() == R.id.edit_border_activity) {
            intent = new Intent(this, EditBorderActivity.class);
        } else if (v.getId() == R.id.edit_simple_activity) {
            intent = new Intent(this, EditSimpleActivity.class);
        } else if (v.getId() == R.id.radio_horizontal_activity) {
            intent = new Intent(this, RadioHorizontalActivity.class);
        } else if (v.getId() == R.id.check_box_activity) {
            intent = new Intent(this, CheckBoxActivity.class);
        } else if (v.getId() == R.id.drawable_state_activity) {
            intent = new Intent(this, DrawableStateActivity.class);
        } else if (v.getId() == R.id.drawable_nine_activity) {
            intent = new Intent(this, DrawableNineActivity.class);
        } else if (v.getId() == R.id.login_main_activity) {
            intent = new Intent(this, LoginMainActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}