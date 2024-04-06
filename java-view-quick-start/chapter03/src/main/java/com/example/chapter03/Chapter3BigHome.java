package com.example.chapter03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Chapter3BigHome extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter3_big_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btn_calculator = findViewById(R.id.btn_calculator);

        Button btn_button_click = findViewById(R.id.btn_button_click);

        Button btn_button_enable = findViewById(R.id.btn_button_enable);

        Button btn_button_long_click = findViewById(R.id.btn_button_long_click);

        Button btn_button_style = findViewById(R.id.btn_button_style);

        Button btn_edit_text = findViewById(R.id.btn_edit_text);

        Button btn_image_button = findViewById(R.id.btn_image_button);

        Button image_style = findViewById(R.id.image_style);

        Button linear_layout = findViewById(R.id.linear_layout);


        Button relative_layout = findViewById(R.id.relative_layout);

        Button scroll_view = findViewById(R.id.scroll_view);

        Button text_color = findViewById(R.id.text_color);

        Button text_size = findViewById(R.id.text_size);

        Button text_view = findViewById(R.id.text_view);

        Button view_border = findViewById(R.id.view_border);

        Button view_margin = findViewById(R.id.view_margin);

        Button view_gravity = findViewById(R.id.view_gravity);

        btn_calculator.setOnClickListener(this);

        btn_button_click.setOnClickListener(this);

        btn_button_enable.setOnClickListener(this);

        btn_button_long_click.setOnClickListener(this);

        btn_button_style.setOnClickListener(this);

        btn_edit_text.setOnClickListener(this);

        btn_image_button.setOnClickListener(this);

        image_style.setOnClickListener(this);

        linear_layout.setOnClickListener(this);

        relative_layout.setOnClickListener(this);

        scroll_view.setOnClickListener(this);

        text_color.setOnClickListener(this);

        text_size.setOnClickListener(this);

        text_view.setOnClickListener(this);

        view_border.setOnClickListener(this);

        view_margin.setOnClickListener(this);

        view_gravity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.btn_calculator) {
            intent = new Intent(this, CalculatorActivity.class);
        } else if (v.getId() == R.id.btn_button_click) {
            intent = new Intent(this, ButtonClickActivity.class);
        } else if (v.getId() == R.id.btn_button_enable) {
            intent = new Intent(this, ButtonEableActivity.class);
        } else if (v.getId() == R.id.btn_button_long_click) {
            intent = new Intent(this, ButtonLongClickActivity.class);
        } else if (v.getId() == R.id.btn_button_style) {
            intent = new Intent(this, ButtonStyleActivity.class);
        } else if (v.getId() == R.id.btn_edit_text) {
            intent = new Intent(this, GridLayoutActivity.class);
        } else if (v.getId() == R.id.btn_image_button) {
            intent = new Intent(this, ImageButtonActivity.class);
        } else if (v.getId() == R.id.image_style) {
            intent = new Intent(this, ImageStyleActivity.class);
        } else if (v.getId() == R.id.linear_layout) {
            intent = new Intent(this, LinearLayoutActivity.class);
        } else if (v.getId() == R.id.relative_layout) {
            intent = new Intent(this, RelativeLayoutActivity.class);
        } else if (v.getId() == R.id.scroll_view) {
            intent = new Intent(this, ScrollViewActivity.class);
        } else if (v.getId() == R.id.text_color) {
            intent = new Intent(this, TextColorActivity.class);
        } else if (v.getId() == R.id.text_size) {
            intent = new Intent(this, TextSizeActivity.class);
        } else if (v.getId() == R.id.text_view) {
            intent = new Intent(this, TextViewActivity.class);
        } else if (v.getId() == R.id.view_border) {
            intent = new Intent(this, ViewBorderActivity.class);
        } else if (v.getId() == R.id.view_margin) {
            intent = new Intent(this, ViewMarginActivity.class);
        } else if (v.getId() == R.id.view_gravity) {
            intent = new Intent(this, ViewGravityActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
    }
}