package com.bluemethod.paniccalculator;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Class to manage the users location and fetch coordinates
 * The class keeps a constant record of the most recent location
 *
 * @author Daniel Bliss
 */
public class Coordinates extends Calculator
{
    private String mostRecentLocation;

    private final Context context;

    public Coordinates(Context context)
    {
        this.context = context;
        updateLocation();
    }

    /**
     * Fetches the user's exact coordinates, stores it in the mostRecentLocation
     * global string. Constantly loops
     */
    @SuppressLint("MissingPermissions")
    public void updateLocation() {

        if (checkPermission()) {
            requestPermissions();
        }

        FusedLocationProviderClient locationClient = LocationServices.getFusedLocationProviderClient(context);

        LocationRequest locationRequest = new LocationRequest();

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                for (Location location : locationResult.getLocations())
                    mostRecentLocation = "N°" + location.getLatitude() + " W°" + location.getLongitude();
            }
        };

        locationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    public String getLocation()
    {
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
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, 44);
    }
}
