package com.example.javaviewtest.url;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javaviewtest.R;
import com.example.javaviewtest.database.AppDatabase;
import com.example.javaviewtest.url.dao.HistoryDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.github.haoyiwen.test.core.activity.BaseActivity;
import io.github.haoyiwen.test.core.router.URLRouter;

public class HistoryUrlActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    ArrayList<History> items;

    private HistoryAdapter adapter;
    private AppDatabase db;
    private HistoryDao historyDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listView = findViewById(R.id.list_view);
        // 初始化 Room 数据库
        db = AppDatabase.getDatabase(this);
        historyDao = db.historyDao();


        items = new ArrayList<>();

        adapter = new HistoryAdapter(this, items);

        listView.setAdapter(adapter);

        // 从数据库读取数据并更新 ListView
        executorService.execute(() -> {
            List<History> histories = historyDao.getAllHistories();
            runOnUiThread(() -> {
                items.clear();
                items.addAll(histories);
                adapter.notifyDataSetChanged();
            });
        });

        listView.setOnItemClickListener(this);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_history_url;
    }

    @Override
    protected String setTitle() {
        return "跳转历史记录";
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "点击了第" + position + "项", Toast.LENGTH_SHORT).show();
        URLRouter.openURL(this, items.get(position).getUrl());
    }

    public void deleteHistory(History history) {
        Log.d("HistoryUrlActivity", "deleteHistory: " + history);
        // 从数据库读取数据并更新 ListView
        executorService.execute(() -> {
            historyDao.deleteHistory(history.getUrl());
            List<History> histories = historyDao.getAllHistories();
            runOnUiThread(() -> {
                items.clear();
                items.addAll(histories);
                adapter.notifyDataSetChanged();
            });
        });
    }

    public void jumpURL(History history) {
        Log.d("HistoryUrlActivity", "deleteHistory: " + history);
        // 从数据库读取数据并更新 ListView
        URLRouter.openURL(this, history.getUrl());
    }
}