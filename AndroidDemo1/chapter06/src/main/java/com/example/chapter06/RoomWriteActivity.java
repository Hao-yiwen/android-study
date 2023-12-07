package com.example.chapter06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.chapter06.dao.BookDao;
import com.example.chapter06.enity.BookInfo;
import com.example.chapter06.util.ToastUtil;

import java.util.List;

public class RoomWriteActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_name;
    EditText et_author;
    EditText et_press;
    EditText et_price;
    private BookDao bookDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_write);

        et_name = findViewById(R.id.et_name);
        et_author = findViewById(R.id.et_author);
        et_press = findViewById(R.id.et_press);
        et_price = findViewById(R.id.et_price);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);

        bookDao = MyApplication.getInstance().getBookDatabase().bookDao();
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String author = et_author.getText().toString();
        String press = et_press.getText().toString();
        String price = et_price.getText().toString();

        if (v.getId() == R.id.btn_add) {
            BookInfo b1 = new BookInfo();
            b1.setName(name);
            b1.setAuthor(author);
            b1.setPress(press);
            b1.setPrice(Double.parseDouble(price));
            bookDao.insert(b1);
            ToastUtil.showMsg(this, "添加成功");
        } else if (v.getId() == R.id.btn_delete) {
            BookInfo b2 = new BookInfo();
            b2.setId(1);
            bookDao.delete(b2);
        } else if (v.getId() == R.id.btn_update) {

        } else if (v.getId() == R.id.btn_query) {
            List<BookInfo> list = bookDao.queryAll();
            for (BookInfo bookInfo : list) {
                Log.d("ning", bookInfo.toString());
            }
        }
    }
}