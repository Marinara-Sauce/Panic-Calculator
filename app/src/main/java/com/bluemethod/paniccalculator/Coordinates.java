package com.bluemethod.paniccalculator;

import android.Manifest;
import android.annotation.SuppressLint;
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
public class Coordinates extends AppCompatActivity
{
    private final FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(this);
    private String mostRecentLocation;

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
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Asks for permission from the user for location
     */
    public void requestPermissions()
    {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 44);
    }
}
