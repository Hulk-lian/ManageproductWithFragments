package com.jtcode.manageproductfragment.Settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.jtcode.manageproductfragment.R;


public class AccountSettings extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.account_settings);
    }

    //hay que usar la clase anonima para poder validarlo antes de que se cambie

}
