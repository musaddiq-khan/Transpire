package com.example.junaid.transpire;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.junaid.transpire.Database.YogaDB;

import java.util.Calendar;
import java.util.Date;

public class SettingsPage extends AppCompatActivity {

    Button btnSave;
    RadioButton rdiEasy,rdiMedium,rdiHard;
    RadioGroup rdiGroup;
    YogaDB yogaDB;
    ToggleButton switchAlarm;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        btnSave =(Button)findViewById(R.id.btn_save);
        rdiGroup=(RadioGroup)findViewById(R.id.rdiGroup);
        rdiEasy=(RadioButton)findViewById(R.id.rdiEasy);
        rdiMedium=(RadioButton)findViewById(R.id.rdiMedium);
        rdiHard=(RadioButton)findViewById(R.id.rdiHard);

        switchAlarm=(ToggleButton)findViewById(R.id.switchAlarm);
        timePicker=(TimePicker)findViewById(R.id.timePicker);

        yogaDB= new YogaDB(this);

        int mode = yogaDB.getSettingMode();
        setRadioButton(mode);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveWorkoutMode();

                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(SettingsPage.this,"SAVED",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void saveAlarm(boolean checked)
    {
        if(checked)
        {


           AlarmManager manager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            intent = new Intent(SettingsPage.this,AlarmNotificationReceiver.class);
            pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);

            Calendar calendar=Calendar.getInstance();
            Date toDay = Calendar.getInstance().getTime();
            calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_WEEK),timePicker.getCurrentHour(),timePicker.getCurrentMinute());

            manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);


        }else
        {
           Intent intent = new Intent(SettingsPage.this,AlarmNotificationReceiver.class);
           PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);

           AlarmManager manager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
           manager.cancel(pendingIntent);

        }
        Log.d("DEBUG","ALARM WILL WAKE AT:"+timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute());
    }


    private void saveWorkoutMode() {
        int a=0,b=1,c=2;
        int selectID=rdiGroup.getCheckedRadioButtonId();
        if(selectID==rdiEasy.getId())
            yogaDB.saveSettingMode(a);
        else if(selectID==rdiMedium.getId())
            yogaDB.saveSettingMode(b);
        else if(selectID==rdiHard.getId())
            yogaDB.saveSettingMode(c);
    }

    private void setRadioButton(int mode) {
        if(mode == 0)
            rdiGroup.check(R.id.rdiEasy);
        else if(mode==1)
            rdiGroup.check(R.id.rdiMedium);
        else if(mode==2)
            rdiGroup.check(R.id.rdiHard);
    }
}
