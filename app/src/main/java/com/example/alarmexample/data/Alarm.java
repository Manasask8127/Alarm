package com.example.alarmexample.data;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.alarmexample.broadcastreceiver.AlarmBroadcastReceiver;
import com.example.alarmexample.createalarms.DayUtil;

import java.util.Calendar;

import static com.example.alarmexample.broadcastreceiver.AlarmBroadcastReceiver.FRIDAY;
import static com.example.alarmexample.broadcastreceiver.AlarmBroadcastReceiver.MONDAY;
import static com.example.alarmexample.broadcastreceiver.AlarmBroadcastReceiver.RECURRING;
import static com.example.alarmexample.broadcastreceiver.AlarmBroadcastReceiver.SATURDAY;
import static com.example.alarmexample.broadcastreceiver.AlarmBroadcastReceiver.SUNDAY;
import static com.example.alarmexample.broadcastreceiver.AlarmBroadcastReceiver.THURSDAY;
import static com.example.alarmexample.broadcastreceiver.AlarmBroadcastReceiver.TITLE;
import static com.example.alarmexample.broadcastreceiver.AlarmBroadcastReceiver.TUESDAY;
import static com.example.alarmexample.broadcastreceiver.AlarmBroadcastReceiver.WEDNESDAY;

@Entity(tableName = "alarm_table")
public class Alarm
{
    @PrimaryKey
    @NonNull
    private int alarmId;

    private int hour,minute;
    private boolean started,recurring;
    private boolean monday,tuesday,wednesday,thursday,friday,saturday,sunday;
    private String title;

    public Alarm(int alarmId,int hour,int minute,String title,boolean started,boolean recurring,boolean monday,boolean tuesday,boolean wednesday,boolean thursday,boolean friday,boolean saturday,boolean sunday)
    {
        this.alarmId=alarmId;
        this.hour=hour;
        this.minute=minute;
        this.title=title;
        this.started=started;
        this.recurring=recurring;
        this.monday=monday;
        this.tuesday=tuesday;
        this.wednesday=wednesday;
        this.thursday=thursday;
        this.friday=friday;
        this.saturday=saturday;
        this.sunday=sunday;
    }

    public int getAlarmId() {
        return alarmId;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public boolean isMonday() {
        return monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public String getTitle() {
        return title;
    }

    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

   /* public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public void setTitle(String title) {
        this.title = title;
    }*/
   public void schedule(Context context)
   {
       AlarmManager alarmManager=(AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

       Intent intent=new Intent(context, AlarmBroadcastReceiver.class);
       intent.putExtra(RECURRING,recurring);
       intent.putExtra(MONDAY,monday);
       intent.putExtra(TUESDAY,tuesday);
       intent.putExtra(WEDNESDAY,wednesday);
       intent.putExtra(THURSDAY,thursday);
       intent.putExtra(FRIDAY,friday);
       intent.putExtra(SATURDAY,saturday);
       intent.putExtra(SUNDAY,sunday);
       intent.putExtra(TITLE,title);

       PendingIntent pendingIntent=PendingIntent.getBroadcast(context,alarmId,intent,0);

       Calendar calendar=Calendar.getInstance();
       calendar.setTimeInMillis(System.currentTimeMillis());
       calendar.set(Calendar.HOUR_OF_DAY,hour);
       calendar.set(Calendar.MINUTE,minute);
       calendar.set(Calendar.SECOND,0);
       calendar.set(Calendar.MILLISECOND,0);

       if(calendar.getTimeInMillis()<=System.currentTimeMillis()){
           calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+1);
       }
        String toastText=String.format("Alarm %s scheduled for %s at %02d:%02d",title, DayUtil.toDay(calendar.get(Calendar.DAY_OF_WEEK)),hour,minute,alarmId);
       Toast.makeText(context,toastText,Toast.LENGTH_LONG).show();

       if(!recurring)
       {
           alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
       }
       else
       {
           final long RUN_DAILY=24*60*60*1000;
           alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),RUN_DAILY,pendingIntent);
       }
       this.started=true;
       Log.i("schedule",toastText);
   }
   public void cancelAlarm(Context context)
   {
       AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
       Intent intent=new Intent(context, AlarmBroadcastReceiver.class);
       PendingIntent pendingIntent=PendingIntent.getBroadcast(context,alarmId,intent,0);
       alarmManager.cancel(pendingIntent);
       this.started=false;

       String toastText=String.format("Alarm cancelled for  %02d:%02d with id %d",hour,minute,alarmId);
       Toast.makeText(context,toastText,Toast.LENGTH_SHORT).show();
       Log.i("cancel",toastText);
   }

   public String getRecurringDaysText()
   {
       if(!recurring)
       {
           return null;
       }
       String days="";
       if(monday)
           days+="Mo";
       if(tuesday)
           days+="Tu";
       if(wednesday)
           days+="We";
       if(thursday)
           days+="Th";
       if(friday)
           days+="Fr";
       if(saturday)
           days+="Sa";
       if(sunday)
           days+="Su";
       return days;
   }

}
