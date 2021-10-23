package com.example.broadcastsdemoapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String channelID = "channelID123456";
    public static final String channelName = "Channel Name";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context,"ALARM RECEIVED ",Toast.LENGTH_SHORT).show();
//show notification upon boot



        // create the notification channel
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});


            NotificationManager manager = (NotificationManager) (context.getSystemService(Context.NOTIFICATION_SERVICE));
            manager.createNotificationChannel(channel);


            // create the notification-
            //notice the same CHANNEL_ID!!
            NotificationCompat.Builder nb = new NotificationCompat.Builder(context.getApplicationContext(), channelID);
            nb.setContentTitle("Alarm Broadcast!");
            nb.setContentText("Broadcast from Alarm manager.");
            nb.setSmallIcon(R.drawable.androidimg);
            nb.setChannelId(channelID);


            // create a pending intent to start the application
            // this is optional - just for example:
       //     Intent notifyIntent = new Intent(context, MainActivity.class);
            Intent notifyIntent = new Intent(context,SendCustomBroadcast.class);
            // Set the Activity to start in a new, empty task
            // no need to carry the task stack in this example
            //| Intent.FLAG_ACTIVITY_TASK_ON_HOME
            notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                 //  | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_TASK_ON_HOME
            );
            // Create the PendingIntent
            PendingIntent notifyPendingIntent = PendingIntent.getActivity(
                    context, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
            );
            //add the pending intent to notification builder
            nb.setContentIntent(notifyPendingIntent);
            manager.notify(1, nb.build());

        }



    }
}
