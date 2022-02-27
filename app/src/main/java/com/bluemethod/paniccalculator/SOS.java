package com.bluemethod.paniccalculator;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.SmsManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

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

    private String code;
    private String settingsCode;

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
        message = sp.getString(MainSettingsActivity.MESSAGE_PREF, " is in an unsafe situation, is unable to speak, and needs help. These are their coordinates:");
        sendLocation = sp.getBoolean(MainSettingsActivity.SEND_LOCATION_PREF, true);

        phoneNumbers.clear();

        //Phone numbers
        phoneNumbers.add(sp.getString(MainSettingsActivity.PHONE_NUMBER_PREF1, null));
        phoneNumbers.add(sp.getString(MainSettingsActivity.PHONE_NUMBER_PREF2, null));
        phoneNumbers.add(sp.getString(MainSettingsActivity.PHONE_NUMBER_PREF3, null));
        phoneNumbers.add(sp.getString(MainSettingsActivity.PHONE_NUMBER_PREF4, null));
        phoneNumbers.add(sp.getString(MainSettingsActivity.PHONE_NUMBER_PREF5, null));

        //Code
        code = sp.getString(MainSettingsActivity.SOS_CODE_PREF, "5555");
        settingsCode = sp.getString(MainSettingsActivity.SETTINGS_CODE_PREF, "1234");
    }

    public void sendSMS(String code)
    {
        //Load any loose settings we may have missed
        loadSettings();

        //Check the code is correct
        if (!code.equals(this.code))
        {
            System.out.println("Incorrect Code: Received - " + code + " Expected - " + this.code);
            return;
        }

        //Construct the text message
        String TEST_MESSAGE = "--THIS IS JUST A TEST-- ";

        //Sends the text message to contacts
        SmsManager smsManager = SmsManager.getDefault();
        for (int i = 0 ; i < phoneNumbers.size() ; i++)
        {
            if (phoneNumbers.get(i) != null)
            {
                String text = TEST_MESSAGE + name + message;

                System.out.println("Sending notification to: " + phoneNumbers.get(i));
                System.out.println("Text: " + text);
                smsManager.sendTextMessage(phoneNumbers.get(i), null,
                        text, null, null);

                if (sendLocation)
                {
                    String location = coordinates.getLocation();
                    smsManager.sendTextMessage(phoneNumbers.get(i), null,
                            name + "'s Coordinates: " + location, null, null);
                }


            }
        }

        System.out.println("SOS Message dispatched!");
    }

    public String getSettingsCode()
    {
        return settingsCode;
    }

    public String getSOSCode()
    {
        return code;
    }

}
