package com.example.android.waitlist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.widget.Toast;

public class SettingFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref);
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();

        PreferenceScreen pf = getPreferenceScreen();
        int nPrefs = pf.getPreferenceCount();
        String value;

        for(int i=0; i<nPrefs; i++) {
            Preference p = pf.getPreference(i);
            value = pf.getSharedPreferences().getString(p.getKey(), "");
            setPreferenceSummary(p, value);
        }

        /*
        Preference p = pf.getPreference(0);
        String value = sharedPreferences.getString(p.getKey(), "");
        setPreferenceSummary(p, value);*/
    }

    private void setPreferenceSummary(Preference preference,String value) {
        ListPreference listPreference = (ListPreference) preference;
        int prefIndex = listPreference.findIndexOfValue(value);
        if (prefIndex >= 0) {
            listPreference.setSummary(listPreference.getEntries()[prefIndex]);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (preference!= null) {
            String value = sharedPreferences.getString(key, "");
            setPreferenceSummary(preference, value);
        }
    }
}