package com.bluemethod.paniccalculator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.ArraySet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Handles SOS information, maintains contacts and settings
 */
public class SOS extends AppCompatActivity {

    //Class that can fetch the users location
    private final Coordinates coordinates;

    // --- Stored Settings --- //

    private String name; //The users name
    private String message; //The message to attach to the text

    private boolean sendLocation; //Whether or not to send the coordinates in the text

    private final List<String> phoneNumbers; //List of phone numbers to send alert to

    private final Context context;

    public SOS(Context context)
    {
        this.context = context;
        this.coordinates = new Coordinates(context);
        phoneNumbers = new ArrayList<>();

        loadSettings();
    }

    /**
     * Loads stored settings from the phone
     */
    private void loadSettings()
    {
        final String PHONE_NUMBER_PREF = "phoneNumbers";

        SharedPreferences sp = context.getSharedPreferences(MainSettingsActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);

        //Begin settings variables
        name = sp.getString(MainSettingsActivity.USER_NAME_PREF, "Jim");
        message = sp.getString(MainSettingsActivity.MESSAGE_PREF, "is in an unsafe situation and needs help");
        sendLocation = sp.getBoolean(MainSettingsActivity.SEND_LOCATION_PREF, true);

        //Phone numbers
        phoneNumbers.add(sp.getString(MainSettingsActivity.PHONE_NUMBER_PREF1, null));
        phoneNumbers.add(sp.getString(MainSettingsActivity.PHONE_NUMBER_PREF2, null));
        phoneNumbers.add(sp.getString(MainSettingsActivity.PHONE_NUMBER_PREF3, null));
        phoneNumbers.add(sp.getString(MainSettingsActivity.PHONE_NUMBER_PREF4, null));
        phoneNumbers.add(sp.getString(MainSettingsActivity.PHONE_NUMBER_PREF5, null));
    }

    public void sendSMS()
    {
        //Construct the text message
        String textMessage = "--THIS IS JUST A TEST-- ";

        textMessage = textMessage + message.replace("{name}", name) + " : ";

        if (sendLocation)
            textMessage = textMessage + coordinates.getLocation();

        System.out.println("Prepared Message\n-----------\n" + textMessage);

        //Sends the text message to contacts
        SmsManager smsManager = SmsManager.getDefault();
        for (int i = 0 ; i < phoneNumbers.size() ; i++)
        {
            if (phoneNumbers.get(i) != null)
            {
                System.out.println("Sending notification to: " + phoneNumbers.get(i));
                smsManager.sendTextMessage(phoneNumbers.get(i), null, textMessage, null, null);
            }
        }

        System.out.println("SOS Message dispatched!");
    }

}
