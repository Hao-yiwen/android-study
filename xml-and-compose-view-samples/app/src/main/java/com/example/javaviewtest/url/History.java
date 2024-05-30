package com.example.javaviewtest.url;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history")
public class History {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String url;
    private String title;
    private String time;

    public History(String url, String title, String time) {
        this.url = url;
        this.title = title;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }
}
