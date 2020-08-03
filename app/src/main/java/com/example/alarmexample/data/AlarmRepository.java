package com.example.alarmexample.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class AlarmRepository
{
    private AlarmDao alarmDao;
    private LiveData<List<Alarm>> alarmsliveData;

    public AlarmRepository(Application application)
    {
        AlarmDatabase db=AlarmDatabase.getDatabase(application);
        alarmDao=db.alarmDao();
        alarmsliveData=alarmDao.getAlarms();
    }

    public void insert(final Alarm alarm)
    {
        AlarmDatabase.databaseWriteExecutor.execute(()->{
            alarmDao.insert(alarm);
        });
    }

    public void update(final Alarm alarm)
    {
        AlarmDatabase.databaseWriteExecutor.execute(()->{
            alarmDao.update(alarm);
        });
    }

    public LiveData<List<Alarm>> getAlarmsLiveData()
    {
        return alarmsliveData;
    }
}
