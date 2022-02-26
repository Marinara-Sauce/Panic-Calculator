package com.bluemethod.paniccalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

            // -- Add phone numbers -- //

            EditTextPreference number1 = (EditTextPreference) findPreference("contact1");
            assert number1 != null;
            number1.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    modifyPreference(MainSettingsActivity.PHONE_NUMBER_PREF1, (String) newValue);
                    return true;
                }
            });

            number1.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_PHONE);
                }
            });

            EditTextPreference number2 = (EditTextPreference) findPreference("contact2");
            assert number2 != null;
            number2.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    modifyPreference(MainSettingsActivity.PHONE_NUMBER_PREF2, (String) newValue);
                    return true;
                }
            });

            number2.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_PHONE);
                }
            });

            EditTextPreference number3 = (EditTextPreference) findPreference("contact3");
            assert number3 != null;
            number3.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    modifyPreference(MainSettingsActivity.PHONE_NUMBER_PREF3, (String) newValue);
                    return true;
                }
            });

            number3.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_PHONE);
                }
            });

            EditTextPreference number4 = (EditTextPreference) findPreference("contact4");
            assert number4 != null;
            number4.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    modifyPreference(MainSettingsActivity.PHONE_NUMBER_PREF4, (String) newValue);
                    return true;
                }
            });

            number4.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_PHONE);
                }
            });

            EditTextPreference number5 = (EditTextPreference) findPreference("contact5");
            assert number5 != null;
            number5.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    modifyPreference(MainSettingsActivity.PHONE_NUMBER_PREF5, (String) newValue);
                    return true;
                }
            });

            number5.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_PHONE);
                }
            });

            EditTextPreference sosCode = (EditTextPreference) findPreference("sos_code");
            assert sosCode != null;
            sosCode.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                }
            });

            sosCode.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    modifyPreference(MainSettingsActivity.SOS_CODE_PREF, (String) newValue);
                    return true;
                }
            });

            EditTextPreference settingsCode = (EditTextPreference) findPreference("settings_code");
            assert settingsCode != null;
            settingsCode.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
                @Override
                public void onBindEditText(@NonNull EditText editText) {
                    editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
                }
            });

            settingsCode.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    modifyPreference(MainSettingsActivity.SETTINGS_CODE_PREF, (String) newValue);
                    return true;
                }
            });

            return view;
        }

        private void modifyPreference(String key, String value)
        {
            System.out.print("Writing " + value + " to " + key + "...");
            //SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
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