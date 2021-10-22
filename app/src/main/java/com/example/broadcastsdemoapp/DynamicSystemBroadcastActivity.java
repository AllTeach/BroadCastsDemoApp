

package com.example.broadcastsdemoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DynamicSystemBroadcastActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PHONE_STATE = 1;
    private HeadSetReceiver receiver;
    private PhoneReceiver phoneReceiver;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_system_broadcast);
        
        initViews();
        // ask for permission if required
        checkPermission();

        // create receiver instance
         receiver= new HeadSetReceiver();
         phoneReceiver = new PhoneReceiver();


    }

    private void initViews() {
        textView= findViewById(R.id.tvDynamicExplain);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // create the intent filter and
        // register two receivers dynamically
        // 1. Register for the headset plugged/unplugged event:
        // 2.Register the phone state
        IntentFilter headSetFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        IntentFilter phoneFilter = new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);

        registerReceiver(receiver,headSetFilter);
        registerReceiver(phoneReceiver,phoneFilter);


    }

        // very important- DO NOT FORGET TO UNREGISTER
        @Override
        protected void onPause() {
            super.onPause();
            unregisterReceiver(receiver);
        unregisterReceiver(phoneReceiver);
    }

    private void checkPermission()
    {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_PHONE_STATE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.READ_PHONE_STATE },
                    REQUEST_CODE_PHONE_STATE);
        }


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            // this means fine location
            case REQUEST_CODE_PHONE_STATE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"permission granted to receive SMS",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this,"permission denied to receive SMS",Toast.LENGTH_SHORT).show();
                break;

        }
    }

    public void show(View view)
    {
        if(textView.getVisibility()==View.INVISIBLE)
            textView.setVisibility(View.VISIBLE);
        else
            textView.setVisibility(View.INVISIBLE);

    }
}