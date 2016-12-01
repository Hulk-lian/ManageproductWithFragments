package com.jsw.manageproductrecycler.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.jsw.manageproductrecycler.R;


public class AccountSettings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.account_settings);
    }

    //hay que usar la clase anonima para poder validarlo antes de que se cambie

}
