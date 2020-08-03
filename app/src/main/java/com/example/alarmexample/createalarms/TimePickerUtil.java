package com.example.alarmexample.createalarms;

import android.os.Build;
import android.widget.TimePicker;

import androidx.annotation.RequiresApi;

public final class TimePickerUtil {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int getTimePickerHour(TimePicker tp){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            return tp.getHour();
        }
        else
            return tp.getHour();
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static int getTimePickerMinute(TimePicker tp){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            return tp.getMinute();
        }
        else
            return tp.getMinute();
    }
}
