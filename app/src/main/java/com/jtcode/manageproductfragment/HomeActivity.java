package com.jtcode.manageproductfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jtcode.manageproductfragment.Settings.AccountSettings;
import com.jtcode.manageproductfragment.Settings.GeneralSettings;

public class HomeActivity extends AppCompatActivity implements ManageProductFragment.ManageProductListener,ListProductFragment.ListProductListener {

    private Intent intent;
    private ListProductFragment listProductFragment;
    private ManageProductFragment manageProductFragment;
    Toast backtoast=null;
    Toolbar tool;
    NavigationView nView;
    DrawerLayout drawer;

    ActionBarDrawerToggle mActionBarToogle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_navigation);
        tool=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_home);
        drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        nView=(NavigationView)findViewById(R.id.navigationView);
        listProductFragment=new ListProductFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.framehome,listProductFragment).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupDrawerContent();
        mActionBarToogle=setUpDrawertoogle();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mActionBarToogle.syncState();
    }

    public void setupDrawerContent(){
        nView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                switch (item.getItemId()){
                    case R.id.action_home:
                        break;
                    case R.id.action_pharmacy:
                        break;
                    case R.id.action_solds:
                        break;

                    default:
                        item.setChecked(false);
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                setTitle(item.getTitle());
                return true;
            }
        });
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
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
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

    public ActionBarDrawerToggle setUpDrawertoogle(){
        return  new ActionBarDrawerToggle(this,drawer,tool,R.string.drawer_open,R.string.drawer_cerrado);
    }

    @Override
    public void showManageProduct(Bundle bundle) {
        manageProductFragment= new ManageProductFragment();
        android.support.v4.app.FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome,manageProductFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }

        else if(getSupportFragmentManager().getBackStackEntryCount()==0){ //si estamos en el home
            if(backtoast!=null && backtoast.getView().getWindowToken()!=null) {
                finish();
            } else {
                backtoast = Toast.makeText(this, "Press back to exit", Toast.LENGTH_SHORT);
                backtoast.show();
            }
        } else {
            super.onBackPressed();
        }
    }
    //////////////////////////////////////
    //////////////OPCION1////////////////
    /*
    Toast backtoast=null;
        public void onBackPressed() {
            if(USER_IS_GOING_TO_EXIT) {//comprobacion de la pila si esta vacia o tiene algun elemento
                if(backtoast!=null&&backtoast.getView().getWindowToken()!=null) {
                    finish();
                } else {
                    backtoast = Toast.makeText(this, "Press back to exit", Toast.LENGTH_SHORT);
                    backtoast.show();
                }
            } else {
                //other stuff...
                super.onBackPressed();
            }
        }
    */

    /////////////////////////////////////////
    ////////////////OPCION2/////////////////
    /*
    private long backPressedTime = 0;    // used by onBackPressed()

    @Override
    public void onBackPressed() {        // to prevent irritating accidental logouts
        long t = System.currentTimeMillis();
        if (t - backPressedTime > 2000) {    // 2 secs
            backPressedTime = t;
            Toast.makeText(this, "Press back again to logout",
                                Toast.LENGTH_SHORT).show();
        } else {    // this guy is serious
            // clean up
            super.onBackPressed();       // bye
        }
    }
    */

    @Override
    public void showListProduct(Bundle bundle) {
        android.support.v4.app.FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framehome,listProductFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
