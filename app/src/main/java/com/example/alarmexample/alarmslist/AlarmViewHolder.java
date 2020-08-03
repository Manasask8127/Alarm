package com.example.alarmexample.alarmslist;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmexample.R;
import com.example.alarmexample.createalarms.CreateAlarmFragment;
import com.example.alarmexample.data.Alarm;

public class AlarmViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView alarmTime;
    private ImageView alarmRecurring;
    private TextView alarmRecurringDays;
    private TextView alarmTitle;
    private Switch alarmStarted;
    private CardView cardView;
    public AlarmViewHolder(@NonNull View itemView) {
        super(itemView);
        alarmTime=itemView.findViewById(R.id.item_alarm_time);
        alarmRecurring=itemView.findViewById(R.id.item_alarm_recurring);
        alarmRecurringDays=itemView.findViewById(R.id.item_alarm_recurringDays);
        alarmTitle=itemView.findViewById(R.id.item_alarm_title);
        alarmStarted=itemView.findViewById(R.id.item_alarm_started);
        cardView= itemView.findViewById(R.id.cardview);
        cardView.setOnClickListener(this);
    }
    public void bind(Alarm alarm,OnToggleAlarmListener listener)
    {
        String alarmText=String.format("%02d:%02d",alarm.getHour(),alarm.getMinute());

        alarmTime.setText(alarmText);
        alarmStarted.setChecked(alarm.isStarted());

        if(alarm.isRecurring())
        {
            alarmRecurring.setImageResource(R.drawable.ic_repeat);
            alarmRecurringDays.setText(alarm.getRecurringDaysText());
        }
        else
        {
            alarmRecurring.setImageResource(R.drawable.ic_looks_one);
            alarmRecurringDays.setText("Once Off");
        }

        if(alarm.getTitle().length()!=0)
        {
            alarmTitle.setText(alarm.getTitle());
        }
        else
        {
            alarmTitle.setText("My alarm");
        }

        alarmStarted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onToggle(alarm);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Navigation.findNavController(v).navigate(R.id.action_alarmsListFragment_to_createAlarmFragment);
    }
}
