package com.bluemethod.paniccalculator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Class to manage the users location and fetch coordinates as a string
 *
 * @author Daniel Bliss
 */
public class Coordinates extends Calculator
{
    private FusedLocationProviderClient locationClient;
    private String mostRecentLocation;

    private final Context context;

    public Coordinates(Context context)
    {
        this.context = context;
    }

    /**
     * Fetches the user's exact coordinates
     *
     * @return the users coordinates as a string
     */
    @SuppressLint("MissingPermissions")
    public String getLocation() {

        if (checkPermission()) {
            requestPermissions();
        }

         locationClient = LocationServices.getFusedLocationProviderClient(this);

        locationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<android.location.Location>() {
            @Override
            public void onComplete(@NonNull Task<android.location.Location> task) {
                android.location.Location location = task.getResult();
                if (location == null) {
                    System.out.println("Uh oh");
                    //TODO: Panic
                } else {
                    mostRecentLocation = "N°" + location.getLatitude() + " W°" + location.getLongitude();
                }
            }
        });

        return mostRecentLocation;
    }

    /**
     * Checks if the user has fine and coarse location
     *
     * @return true if the user has the required permissions
     */
    public boolean checkPermission()
    {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Asks for permission from the user for location
     */
    public void requestPermissions()
    {
        ActivityCompat.requestPermissions((Activity) context, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 44);
    }
}
