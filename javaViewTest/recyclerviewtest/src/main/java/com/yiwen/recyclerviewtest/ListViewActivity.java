package com.yiwen.recyclerviewtest;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yiwen.recyclerviewtest.fragment.ToolBarFragment;

/**
 * @description: ListViewActivity
 * @date: 2021/4/6 10:00 AM
 * ListView仅支持简单的列表，不支持复杂的列表，例如一项Item是一个fragemnt，复杂场景还是需要使用recycleView
 */
public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10", "Item 11", "Item 12", "Item 13", "Item 14", "Item 15", "Item 16", "Item 17", "Item 18", "Item 19", "Item 20", "Item 21", "Item 22", "Item 23", "Item 24", "Item 25", "Item 26", "Item 27", "Item 28", "Item 29", "Item 30", "Item 31", "Item 32", "Item 33", "Item 34", "Item 35", "Item 36", "Item 37", "Item 38", "Item 39", "Item 40", "Item 41", "Item 42", "Item 43", "Item 44", "Item 45", "Item 46", "Item 47", "Item 48", "Item 49", "Item 50"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_toolbar, ToolBarFragment.newInstance("ListView")).commit();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);

        ListView listView = findViewById(R.id.list_view);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Click on " + items[position], Toast.LENGTH_SHORT).show();
    }
}