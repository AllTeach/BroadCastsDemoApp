package com.example.broadcastsdemoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_RECEIVE_SMS =1 ;
    private static final int REQUEST_CODE_READ_SMS =2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ask for permission if required
        checkPermission();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        // get the id of the selected item
        int id=item.getItemId();

        // check which item was selected
        if(id==R.id.itemMenuDynamicHeadset)
        {
            Intent intent= new Intent(this,DynamicSystemBroadcastActivity.class);
            startActivity(intent); // moved to other activity
        }
        else if(id==R.id.itemMenuCustomBroadcast)
        {
            Intent intent= new Intent(this,SendCustomBroadcast.class);
            startActivity(intent); // moved to other activity
        }
        else if(id==R.id.itemMenuAlarmBroadcast)
        {
            Intent intent= new Intent(this,SetAlarmBroadcast.class);
            startActivity(intent); // moved to other activity
        }

        return true;
    }


    private void checkPermission()
    {
        if (ContextCompat.checkSelfPermission(
            this, Manifest.permission.RECEIVE_SMS) !=
            PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(this,
                new String[] { Manifest.permission.RECEIVE_SMS },
                REQUEST_CODE_RECEIVE_SMS);
    }
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_SMS) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.READ_SMS },
                    REQUEST_CODE_READ_SMS);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            // this means fine location
            case REQUEST_CODE_RECEIVE_SMS:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"permission granted to receive SMS",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this,"permission denied to receive SMS",Toast.LENGTH_SHORT).show();
                break;
            case REQUEST_CODE_READ_SMS:
            {
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,"permission granted to read SMS",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this,"permission denied to read SMS",Toast.LENGTH_SHORT).show();
                break;
            }

            }
        }


    }