package com.jsw.manageproductrecycler;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.jsw.manageproductrecycler.Settings.AccountSettings;
import com.jsw.manageproductrecycler.Settings.GeneralSettings;

public class HomeActivity extends AppCompatActivity implements ManageProductFragment.ManageProductListener,ListProductFragment.ListProductListener {

    private Intent intent;
    private ListProductFragment listProductFragment;
    private ManageProductFragment manageProductFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listProductFragment=new ListProductFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framehome,listProductFragment).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_account_settings:
                intent = new Intent(HomeActivity.this, AccountSettings.class);
                startActivity(intent);
                break;
            case R.id.action_general_settings:
                intent = new Intent(HomeActivity.this, GeneralSettings.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public void showManageProduct(Bundle bundle) {
        manageProductFragment= new ManageProductFragment(bundle);
        android.support.v4.app.FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome,manageProductFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void showListProduct(Bundle bundle) {
        android.support.v4.app.FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome,listProductFragment);
        //ft.addToBackStack(null);
        ft.commit();
    }
}
