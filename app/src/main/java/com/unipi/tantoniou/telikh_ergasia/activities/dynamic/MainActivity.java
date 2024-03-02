package com.unipi.tantoniou.telikh_ergasia.activities.dynamic;

import android.os.Bundle;

import com.unipi.tantoniou.telikh_ergasia.R;
import com.unipi.tantoniou.telikh_ergasia.customActivity.CustomActivity;


public class MainActivity extends CustomActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        // drawer layout instance to toggle the menu icon to open
//        // drawer and back button to close drawer
//        drawerLayout = findViewById(R.id.drawerLayout_MainActivity);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
//
//        // pass the Open and Close toggle for the drawer layout listener
//        // to toggle the button
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//
//        // to make the Navigation drawer icon always appear on the action bar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawMenu(R.id.drawerLayout_MainActivity1);
        addNavOnClickEventListeners();
    }





    //not sure
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}