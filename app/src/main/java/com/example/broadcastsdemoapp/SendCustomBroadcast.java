package com.example.broadcastsdemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class SendCustomBroadcast extends AppCompatActivity {
    private MyCustomReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_custom_broadcast);
         receiver = new MyCustomReceiver();
    }
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter("com.example.allon.custom.broadcast");
        registerReceiver(receiver,filter);
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void send(View view)
    {
        Intent intent = new Intent("com.example.allon.custom.broadcast");
        intent.putExtra("data","my broadcast");
        sendBroadcast(intent);

    }
}