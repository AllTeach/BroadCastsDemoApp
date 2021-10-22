package com.example.broadcastsdemoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class HeadSetReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
            int state = intent.getIntExtra("state", -1);
            int microphone = intent.getIntExtra("microphone",0);

            switch (state) {
                case 0:
                    Toast.makeText(context, "headset unplugged",Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(context, "headset plugged , with microphone: " + (microphone==1) ,Toast.LENGTH_SHORT).show();
                    break;

                default:
                    Toast.makeText(context, "unknown state ",Toast.LENGTH_SHORT).show();
            }
        }


    }
}
