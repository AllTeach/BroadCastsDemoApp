package com.example.broadcastsdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class SetAlarmBroadcast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alam_broadcast);

        int i = 20;
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + (20 * 1000), pendingIntent);
        }
            else
               alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis()
                    + (i * 1000), pendingIntent);

        Toast.makeText(this, "Alarm set in " + i + " seconds",Toast.LENGTH_LONG).show();




    }
}