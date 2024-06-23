package com.example.javaviewtest.url.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.javaviewtest.url.History;

import java.util.List;

@Dao
public interface HistoryDao {
    @Insert
    void insert(History history);

    @Query("SELECT * FROM history")
    List<History> getAllHistories();

    @Query("DELETE FROM history WHERE url = :url")
    void deleteHistory(String url);
}
