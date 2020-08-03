package com.example.alarmexample.data;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Dao;

import java.util.List;

@Dao
public interface AlarmDao {
    @Insert
    void insert(Alarm alarm);

    @Query("DELETE FROM alarm_table")
    void deleteAll();

    @Query("SELECT * FROM alarm_table ORDER BY alarmId ASC")
    LiveData<List<Alarm>> getAlarms();

    @Update
    void update(Alarm alarm);
}
