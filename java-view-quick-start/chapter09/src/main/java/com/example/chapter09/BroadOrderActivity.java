package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

import com.example.chapter09.receiver.OrderAReceiver;
import com.example.chapter09.receiver.OrderBReceiver;

public class BroadOrderActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String ORDER_ACTION = "com.example.chapter09.order";
    private OrderBReceiver orderBReceiver;
    private OrderAReceiver orderAReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_order);

        findViewById(R.id.btn_send_standard).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(ORDER_ACTION);
        sendOrderedBroadcast(intent, null);
    }

    @Override
    protected void onStart() {
        super.onStart();
        orderAReceiver = new OrderAReceiver();
        IntentFilter filterA = new IntentFilter(ORDER_ACTION);
        filterA.setPriority(8);
        registerReceiver(orderAReceiver, filterA, RECEIVER_EXPORTED);

        orderBReceiver = new OrderBReceiver();
        IntentFilter filterB = new IntentFilter(ORDER_ACTION);
        filterB.setPriority(10);
        registerReceiver(orderBReceiver, filterB, RECEIVER_EXPORTED);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(orderAReceiver);
        unregisterReceiver(orderBReceiver);
    }
}