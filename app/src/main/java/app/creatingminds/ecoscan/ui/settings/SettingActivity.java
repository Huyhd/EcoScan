package app.creatingminds.ecoscan.ui.settings;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;

import app.creatingminds.ecoscan.R;

public class SettingActivity extends PreferenceActivity implements Preference.OnPreferenceClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.layout.activity_setting);

        if (getActionBar() != null)
            getActionBar().setTitle("설정");

        Preference pAppName = findPreference("keyhelp");
        Preference pAppVersion = findPreference("keycontact");

        pAppName.setOnPreferenceClickListener(this);
        pAppVersion.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        // 도움말 선택시
        if (preference.getKey().equals("keyhelp")) {
            //intent will be in here
        } else if (preference.getKey().equals("keycontact")) {
        }
        return false;
    }
}
