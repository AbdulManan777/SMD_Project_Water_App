package com.ass2.water_reminderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ass2.water_reminderapp.databinding.ActivityBedTimeModeBinding;
import com.ass2.water_reminderapp.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class BedTimeMode extends AppCompatActivity {


    ActivityBedTimeModeBinding binding;

    MaterialTimePicker materialTimePicker;

    Calendar calender;

    AlarmManager alarmManager;

    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityBedTimeModeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createNotificationChannel();

        binding.selecttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showTimePicker();

            }
        });

        binding.settitme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setedtime();
            }
        });

        binding.canceltime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                canceltime();

            }
        });







    }

    private void canceltime() {


        Intent intent=new Intent(this,BedtimeNotify.class);

        pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);

        if(alarmManager==null){

            alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);


        }

        alarmManager.cancel(pendingIntent);

        Toast.makeText(this,"bed Time Canceled",Toast.LENGTH_LONG).show();


    }

    private void setedtime() {


        alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent=new Intent(this,BedtimeNotify.class);

        pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(this,"Bed time Successfully Set", Toast.LENGTH_LONG).show();




    }

    private void showTimePicker() {

        materialTimePicker=new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Bed Time")
                .build();

        materialTimePicker.show(getSupportFragmentManager(),"bedtime");
        materialTimePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(materialTimePicker.getHour() >12){

                    binding.selectedTime.setText(
                            String.format("0%2d",(materialTimePicker.getHour()-12))+ "  : "+String.format("0%2d", materialTimePicker.getMinute())+" PM"
                    );
                }

                else{

                    binding.selectedTime.setText(

                            materialTimePicker.getHour()+" : "+materialTimePicker.getMinute()+" AM"

                    );
                }

                calender=Calendar.getInstance();
                calender.set(Calendar.HOUR_OF_DAY, materialTimePicker.getHour());
                calender.set(Calendar.MINUTE,materialTimePicker.getMinute());
                calender.set(Calendar.SECOND,0);
                calender.set(Calendar.MILLISECOND,0);

            }
        });


    }

    private void createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name="bedtimeModeChannel";
            String description="Channel for Bed time";
            int importance= NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel=new NotificationChannel("bedtime",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}