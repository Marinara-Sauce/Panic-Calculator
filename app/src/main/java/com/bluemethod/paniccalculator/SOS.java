package com.bluemethod.paniccalculator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Handles SOS information, maintains contacts and settings
 */
public class SOS extends AppCompatActivity {

    private final FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(this);
    private String recentLocationText;

    @SuppressLint("MissingPermissions")
    public String getLocation() {

        if (checkPermission()) {
            requestPermissions();
            return "Location not found, permission denied";
        }

        String locationText;

        locationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location == null) {
                    System.out.println("Uh oh");
                    //TODO: Panic
                } else {
                    recentLocationText = "N°" + location.getLatitude() + " W°" + location.getLongitude();
                }
            }
        });

        locationText = recentLocationText;
        recentLocationText = "";

        return locationText;
    }

    public boolean checkPermission()
    {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermissions()
    {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 44);
    }

    public void sendSMS()
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage("+14015730020", null, getLocation(), null, null);
    }

}
