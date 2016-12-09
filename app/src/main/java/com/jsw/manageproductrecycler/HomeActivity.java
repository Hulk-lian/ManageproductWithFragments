package com.jsw.manageproductrecycler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.jsw.manageproductrecycler.Settings.AccountSettings;
import com.jsw.manageproductrecycler.Settings.GeneralSettings;

public class HomeActivity extends AppCompatActivity {
    Intent intent;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_account_settings:
                intent = new Intent(this, AccountSettings.class);
                startActivity(intent);
                break;
            case R.id.action_general_settings:
                intent = new Intent(this, GeneralSettings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
