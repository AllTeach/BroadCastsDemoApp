package com.example.broadcastsdemoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyCustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction() == "com.example.allon.custom.broadcast")
        {
            // read extra field
            String customData = intent.getStringExtra("data");
            Toast.makeText(context,"MyCustom Broadcast received with data: " + customData,Toast.LENGTH_SHORT).show();
        }


    }
}
