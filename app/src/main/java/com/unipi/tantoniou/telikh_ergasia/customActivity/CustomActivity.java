package com.unipi.tantoniou.telikh_ergasia.customActivity;

import androidx.appcompat.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.google.android.material.navigation.NavigationView;
import com.unipi.tantoniou.telikh_ergasia.R;
import com.unipi.tantoniou.telikh_ergasia.activities.dynamic.MainActivity;
import com.unipi.tantoniou.telikh_ergasia.activities.dynamic.MainActivity2;

import java.util.HashMap;

public abstract class CustomActivity extends AppCompatActivity{
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public ActionBar actionBar;



//    public static HashMap<>

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        addXmlNavView();
    }

    public void drawMenu(Integer layoutId){
        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        this.drawerLayout = findViewById(layoutId);
        this.actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        this.drawerLayout.addDrawerListener(this.actionBarDrawerToggle);
        this.actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bindIconToActionBar(this.actionBar);
        this.actionBar = this.getSupportActionBar();
        assert this.actionBar != null;
        this.actionBar.setDisplayHomeAsUpEnabled(true);
    }


    public void bindIconToActionBar(ActionBar actionBar){
//        this.actionBar.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addNavOnClickEventListeners(){

//        Intent intent = new Intent(this, MainActivity2.class);
//        intent.putExtra("mykey1",editText.getText().toString());
//        startActivity(intent);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            Intent intent=null;

            if(id==R.id.story1 && validateNotSameActivity(MainActivity.class)) {
                intent = new Intent(this, MainActivity.class);
                this.drawerLayout = findViewById(R.id.drawerLayout_MainActivity1);
            }
            if(id==R.id.story2 && validateNotSameActivity(MainActivity2.class)) {
                intent = new Intent(this, MainActivity2.class);
                this.drawerLayout = findViewById(R.id.drawerLayout_MainActivity2);
            }
            if(intent!=null) {
                startActivity(intent);
            }
            if(this.drawerLayout!=null) {
                Log.d(getLocalClassName(),"Closing Drawer!!!");
                this.drawerLayout.closeDrawer(GravityCompat.START);
            }
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addXmlNavView(){
        // Create NavigationView programmatically
        NavigationView navigationView = new NavigationView(this);
        // Assign an ID
        navigationView.setId(R.id.navigation_view);
        // Set layout parameters
        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        params.gravity = GravityCompat.START; // Align to start
        navigationView.setLayoutParams(params);

        // Set the menu for the NavigationView
        navigationView.inflateMenu(R.menu.navigation_menu);

        // Add NavigationView to DrawerLayout
        this.drawerLayout.addView(navigationView);
    }

    private boolean validateNotSameActivity(Class className){
        if(this.getClass()==className){
            return false;
        }
        return true;
    }
}
