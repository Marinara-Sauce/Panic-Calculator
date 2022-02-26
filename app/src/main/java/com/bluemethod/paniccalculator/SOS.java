package com.bluemethod.paniccalculator;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;

/**
 * Handles SOS information, maintains contacts and settings
 */
public class SOS extends Activity
{

    public void sendSMS()
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+14015730020", null, "Hello world!", null, null);
    }

}
