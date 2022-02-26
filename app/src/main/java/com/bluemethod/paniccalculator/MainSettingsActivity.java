package com.bluemethod.paniccalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import java.security.Key;

public class MainSettingsActivity extends AppCompatActivity {

    // -- Setting Key Values -- //
    public static final String PREFERENCE_NAME = "app-preferences";
    public static final String USER_NAME_PREF = "name";
    public static final String MESSAGE_PREF = "message";
    public static final String SEND_LOCATION_PREF = "sendLocation";
    public static final String PHONE_NUMBER_PREF = "phoneNumbers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent = new Intent(this, Calculator.class);
        startActivity(intent);
        return false;
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view = super.onCreateView(inflater, container, savedInstanceState);

            EditTextPreference name = (EditTextPreference) findPreference("name");
            assert name != null;
            name.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    modifyPreference(MainSettingsActivity.USER_NAME_PREF, (String) newValue);
                    return true;
                }
            });

            EditTextPreference message = (EditTextPreference) findPreference("custom_message");
            assert message != null;
            message.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    String message = (String) newValue;
                    if (message.isEmpty()) message = "{name} is in an unsafe situation, is unable to speak, and needs help. Coordinates are:";

                    modifyPreference(MainSettingsActivity.MESSAGE_PREF, (String) message);
                    return true;
                }
            });

            SwitchPreferenceCompat location = (SwitchPreferenceCompat) findPreference("attach_coords");
            assert location != null;
            location.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    modifyPreference(MainSettingsActivity.SEND_LOCATION_PREF, (boolean) newValue);
                    return true;
                }
            });

            return view;
        }

        private void modifyPreference(String key, String value)
        {
            System.out.print("Writing " + value + " to " + key + "...");
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
            System.out.println("Complete!");
        }

        private void modifyPreference(String key, boolean value)
        {
            System.out.print("Writing " + value + " to " + key + "...");
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
            System.out.println("Complete!");
        }
    }
}