package com.bluemethod.paniccalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreferenceCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainSettingsActivity extends AppCompatActivity {

    // -- Setting Key Values -- //
    public static final String PREFERENCE_NAME = "app-preferences";
    public static final String USER_NAME_PREF = "name";
    public static final String MESSAGE_PREF = "message";
    public static final String SEND_LOCATION_PREF = "sendLocation";
    public static final String PHONE_NUMBER_PREF1 = "phoneNumbers1";
    public static final String PHONE_NUMBER_PREF2 = "phoneNumbers2";
    public static final String PHONE_NUMBER_PREF3 = "phoneNumbers3";
    public static final String PHONE_NUMBER_PREF4 = "phoneNumbers4";
    public static final String PHONE_NUMBER_PREF5 = "phoneNumbers5";
    public static final String SOS_CODE_PREF = "sosCode";
    public static final String SETTINGS_CODE_PREF = "settingsCode";

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
        //Some brief settings to look out for
        SharedPreferences sharedPreferences = this.getApplicationContext().getSharedPreferences(MainSettingsActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);

        String name = sharedPreferences.getString(USER_NAME_PREF, "NO_NAME_FOUND");
        String number = sharedPreferences.getString(PHONE_NUMBER_PREF1, "NO_NUMBER_FOUND");

        if (name.equals("NO_NAME_FOUND") || number.equals("NO_NUMBER_FOUND") || name.length() <= 1 || number.length() <= 1)
        {
            View view = findViewById(android.R.id.content);
            Snackbar.make(view, "Ensure that Name and Emergency Contact 1 are set to continue",
                    Snackbar.LENGTH_LONG).show();

            return false;
        }

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
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
            View view = super.onCreateView(inflater, container, savedInstanceState);

            EditTextPreference name = findPreference("name");
            assert name != null;
            name.setOnPreferenceChangeListener((preference, newValue) -> {
                modifyPreference(MainSettingsActivity.USER_NAME_PREF, (String) newValue);
                return true;
            });

            EditTextPreference message = findPreference("custom_message");
            assert message != null;
            message.setOnPreferenceChangeListener((preference, newValue) -> {
                String message1 = (String) newValue;
                if (message1.isEmpty()) message1 = "{name} is in an unsafe situation, is unable to speak, and needs help. Coordinates are:";

                modifyPreference(MainSettingsActivity.MESSAGE_PREF, message1);
                return true;
            });

            SwitchPreferenceCompat location = findPreference("attach_coords");
            assert location != null;
            location.setOnPreferenceChangeListener((preference, newValue) -> {
                modifyPreference(MainSettingsActivity.SEND_LOCATION_PREF, (boolean) newValue);
                return true;
            });

            // -- Add phone numbers -- //

            EditTextPreference number1 = findPreference("contact1");
            assert number1 != null;
            number1.setOnPreferenceChangeListener((preference, newValue) -> {
                modifyPreference(MainSettingsActivity.PHONE_NUMBER_PREF1, (String) newValue);
                return true;
            });

            number1.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_PHONE));

            EditTextPreference number2 = findPreference("contact2");
            assert number2 != null;
            number2.setOnPreferenceChangeListener((preference, newValue) -> {
                modifyPreference(MainSettingsActivity.PHONE_NUMBER_PREF2, (String) newValue);
                return true;
            });

            number2.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_PHONE));

            EditTextPreference number3 = findPreference("contact3");
            assert number3 != null;
            number3.setOnPreferenceChangeListener((preference, newValue) -> {
                modifyPreference(MainSettingsActivity.PHONE_NUMBER_PREF3, (String) newValue);
                return true;
            });

            number3.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_PHONE));

            EditTextPreference number4 = findPreference("contact4");
            assert number4 != null;
            number4.setOnPreferenceChangeListener((preference, newValue) -> {
                modifyPreference(MainSettingsActivity.PHONE_NUMBER_PREF4, (String) newValue);
                return true;
            });

            number4.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_PHONE));

            EditTextPreference number5 = findPreference("contact5");
            assert number5 != null;
            number5.setOnPreferenceChangeListener((preference, newValue) -> {
                modifyPreference(MainSettingsActivity.PHONE_NUMBER_PREF5, (String) newValue);
                return true;
            });

            number5.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_PHONE));

            EditTextPreference sosCode = findPreference("sos_code");
            assert sosCode != null;
            sosCode.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED));

            sosCode.setOnPreferenceChangeListener((preference, newValue) -> {
                modifyPreference(MainSettingsActivity.SOS_CODE_PREF, (String) newValue);
                return true;
            });

            EditTextPreference settingsCode = findPreference("settings_code");
            assert settingsCode != null;
            settingsCode.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED));

            settingsCode.setOnPreferenceChangeListener((preference, newValue) -> {
                modifyPreference(MainSettingsActivity.SETTINGS_CODE_PREF, (String) newValue);
                return true;
            });

            return view;
        }

        private void modifyPreference(String key, String value)
        {
            System.out.print("Writing " + value + " to " + key + "...");
            SharedPreferences sharedPreferences = getContext().getSharedPreferences(MainSettingsActivity.PREFERENCE_NAME, Context.MODE_PRIVATE);
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