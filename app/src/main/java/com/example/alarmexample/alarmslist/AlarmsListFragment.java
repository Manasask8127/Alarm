package com.example.alarmexample.alarmslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmexample.R;
import com.example.alarmexample.data.Alarm;
import com.example.alarmexample.data.AlarmRepository;

import java.util.List;

public class AlarmsListFragment extends Fragment implements OnToggleAlarmListener
{
    private AlarmRecyclerViewAdapter alarmRecyclerViewAdapter;
    private AlarmsListViewModel alarmsListViewModel;
    private RecyclerView alarmsRecyclerView;
    private Button addAlarm;
    private AlarmRepository alarmRepository;
    public AlarmsListFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmRecyclerViewAdapter=new AlarmRecyclerViewAdapter(this);
        alarmsListViewModel=new ViewModelProvider(this).get(AlarmsListViewModel.class);
        alarmsListViewModel.getAlarmsliveData().observe(this,new Observer<List<Alarm>>()
        {
            @Override
            public void onChanged(List<Alarm> alarms)
            {
                if(alarms!=null)
                    alarmRecyclerViewAdapter.setAlarms(alarms);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.fragment_listalarms,container,false);
        alarmsRecyclerView=view.findViewById(R.id.fragment_listalarms_recylerView);
        alarmsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        alarmsRecyclerView.setAdapter(alarmRecyclerViewAdapter);



        addAlarm=view.findViewById(R.id.fragment_listalarms_addAlarm);
        addAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Navigation.findNavController(v).navigate(R.id.action_alarmsListFragment_to_createAlarmFragment,alarmRepository.getAlarmsLiveData() );
                Navigation.findNavController(v).navigate(R.id.action_alarmsListFragment_to_createAlarmFragment);
            }
        });
        return view;
    }

    @Override
    public void onToggle(Alarm alarm) {
        if(alarm.isStarted()) {
            alarm.cancelAlarm(getContext());
            alarmsListViewModel.update(alarm);
        }
        else
        {
            alarm.schedule(getContext());
            alarmsListViewModel.update(alarm);
        }
    }
}
