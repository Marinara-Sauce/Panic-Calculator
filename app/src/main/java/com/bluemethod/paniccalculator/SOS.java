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

        //loadSettings();
        loadDefaultSettings();
    }

    /**
     * Temp function that loads the default settings
     */
    private void loadDefaultSettings()
    {
        name = "Jimmy";
        message = "Needs help";
        sendLocation = true;
        phoneNumbers.add("+11234");
    }

    /**
     * Loads stored settings from the phone
     */
    private void loadSettings()
    {
        final String PREFERENCE_NAME = "app-settings";

        final String USER_NAME_PREF = "name";
        final String MESSAGE_PREF = "message";
        final String SEND_LOCATION_PREF = "sendLocation";
        final String PHONE_NUMBER_PREF = "phoneNumbers";

        SharedPreferences sp = this.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        //Begin settings variables
        name = sp.getString(USER_NAME_PREF, "Jim");
        message = sp.getString(MESSAGE_PREF, "Help");
        sendLocation = sp.getBoolean(SEND_LOCATION_PREF, true);

        Set<String> num = new ArraySet<>();
        num.add("+14015730020");

        Set<String> numbers = sp.getStringSet(PHONE_NUMBER_PREF, num);

        if (name == null || message == null || numbers == null)
        {
            name = "Jimmy";
            message = "needs healing";
            //TODO: Run setup
        }

        //Parse the phone number array
        //for (int i = 0 ; i < numbers.size() ; i++)
        //     phoneNumbers.add((String) numbers.toArray()[i]);

        phoneNumbers.add("+14015730020");
    }

    public void sendSMS()
    {
        //Construct the text message
        String textMessage = "--THIS IS JUST A TEST--\n\n";

        textMessage = textMessage + name + message;

        if (sendLocation)
            textMessage = textMessage + "\n\n" + coordinates.getLocation();

        //Sends the text message to contacts
        SmsManager smsManager = SmsManager.getDefault();
        for (int i = 0 ; i < phoneNumbers.size() ; i++)
            smsManager.sendTextMessage(phoneNumbers.get(i), null, textMessage, null, null);
    }

}
