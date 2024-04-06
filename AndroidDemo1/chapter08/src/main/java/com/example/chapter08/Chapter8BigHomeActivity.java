package com.example.chapter08;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Chapter8BigHomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chapter8_big_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.base_adapter_activity).setOnClickListener(this);
        findViewById(R.id.bill_add_activity).setOnClickListener(this);
        findViewById(R.id.bill_pager_activity).setOnClickListener(this);
        findViewById(R.id.fragment_static_activity).setOnClickListener(this);
        findViewById(R.id.fragment_dynamic_activity).setOnClickListener(this);
        findViewById(R.id.grid_view_activity).setOnClickListener(this);
        findViewById(R.id.launch_improve_activity).setOnClickListener(this);
        findViewById(R.id.launch_simple_activity).setOnClickListener(this);
        findViewById(R.id.list_focus_activity).setOnClickListener(this);
        findViewById(R.id.list_view_activity).setOnClickListener(this);
        findViewById(R.id.page_tab_activity).setOnClickListener(this);
        findViewById(R.id.shopping_cart_activity).setOnClickListener(this);
        findViewById(R.id.shopping_channel_activity).setOnClickListener(this);
        findViewById(R.id.shopping_detail_activity).setOnClickListener(this);
        findViewById(R.id.spinner_dialog_activity).setOnClickListener(this);
        findViewById(R.id.spinner_dropdown_activity).setOnClickListener(this);
        findViewById(R.id.spinner_icon_activity).setOnClickListener(this);
        findViewById(R.id.view_pager_activity).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.base_adapter_activity) {
            intent = new Intent(this, BaseAdapterActivity.class);
        } else if (v.getId() == R.id.bill_add_activity) {
            intent = new Intent(this, BillAddActivity.class);
        } else if (v.getId() == R.id.bill_pager_activity) {
            intent = new Intent(this, BillPagerActivity.class);
        } else if (v.getId() == R.id.fragment_static_activity) {
            intent = new Intent(this, FragmentStaticActivity.class);
        } else if (v.getId() == R.id.fragment_dynamic_activity) {
            intent = new Intent(this, FragmentDynamicActivity.class);
        } else if (v.getId() == R.id.grid_view_activity) {
            intent = new Intent(this, GridViewActivity.class);
        } else if (v.getId() == R.id.launch_improve_activity) {
            intent = new Intent(this, LaunchImproveActivity.class);
        } else if (v.getId() == R.id.launch_simple_activity) {
            intent = new Intent(this, LaunchSimpleActivity.class);
        } else if (v.getId() == R.id.list_focus_activity) {
            intent = new Intent(this, ListFocusActivity.class);
        } else if (v.getId() == R.id.list_view_activity) {
            intent = new Intent(this, ListViewActivity.class);
        } else if (v.getId() == R.id.page_tab_activity) {
            intent = new Intent(this, PageTabActivity.class);
        } else if (v.getId() == R.id.shopping_cart_activity) {
            intent = new Intent(this, ShoppingCartActivity.class);
        } else if (v.getId() == R.id.shopping_channel_activity) {
            intent = new Intent(this, ShoppingChannelActivity.class);
        } else if (v.getId() == R.id.shopping_detail_activity) {
            intent = new Intent(this, ShoppingDetailActivity.class);
        } else if (v.getId() == R.id.spinner_dialog_activity) {
            intent = new Intent(this, SpinnerDialogActivity.class);
        } else if (v.getId() == R.id.spinner_dropdown_activity) {
            intent = new Intent(this, SpinnerDropdownActivity.class);
        } else if (v.getId() == R.id.spinner_icon_activity) {
            intent = new Intent(this, SpinnerIconActivity.class);
        } else if (v.getId() == R.id.view_pager_activity) {
            intent = new Intent(this, ViewPagerActivity.class);
        }
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}