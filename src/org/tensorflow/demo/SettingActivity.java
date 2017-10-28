package org.tensorflow.demo;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class SettingActivity extends PreferenceActivity implements Preference.OnPreferenceClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        getActionBar().setTitle("설정");

        Preference pAppName = (Preference)findPreference("keyhelp");
        Preference pAppVersion = (Preference)findPreference("keycontact");

        pAppName.setOnPreferenceClickListener(this);
        pAppVersion.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference)
    {
        // 도움말 선택시
        if(preference.getKey().equals("keyhelp"))
        {
            //intent will be in here
        }
        else if(preference.getKey().equals("keycontact"))
        {
        }
        return false;
    }
}
