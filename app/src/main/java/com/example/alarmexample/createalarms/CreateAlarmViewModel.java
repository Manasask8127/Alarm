package com.example.alarmexample.createalarms;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.alarmexample.data.Alarm;
import com.example.alarmexample.data.AlarmRepository;

public class CreateAlarmViewModel extends AndroidViewModel {
    private AlarmRepository alarmRepository;
    public CreateAlarmViewModel(@NonNull Application application) {
        super(application);
        alarmRepository=new AlarmRepository(application);
    }
    public void insert(Alarm alarm)
    {
        alarmRepository.insert(alarm);
    }
}
