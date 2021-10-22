package com.example.broadcastsdemoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Log.e("PANKAJ", "Context class " + context.getClass().getName());
        Log.e("PANKAJ", "Activity Context class "
                + MainActivity.class);
        Log.e("PANKAJ", "Application Context class "
                + context.getApplicationContext().getClass().getName());

        if (intent.getExtras() != null) {


            // fetch the incoming sms that is broadcast.

            Object[] pdus = (Object[]) intent.getExtras().get("pdus");
            for (int i = 0; i < pdus.length; i++) {

                SmsMessage smsMessage = Build.VERSION.SDK_INT >= 19
                        ? Telephony.Sms.Intents.getMessagesFromIntent(intent)[i]
                        : SmsMessage.createFromPdu((byte[]) pdus[i]);


                // get the sender & body of the incoming sms.

                String sender = smsMessage.getOriginatingAddress();
                String body = smsMessage.getMessageBody().toString();

                Toast.makeText(context, "Received SMS from " + sender + " message: " + body, Toast.LENGTH_SHORT).show();


            }
        }
    }
}