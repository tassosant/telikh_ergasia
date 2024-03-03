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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class CustomActivity extends AppCompatActivity{
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public ActionBar actionBar;

    //map them with menu items
    public static final HashMap<Integer, LayoutObject> layoutObjects= new HashMap<Integer,LayoutObject>()

    {{
        put(R.id.story1,new LayoutObject(R.id.story1,R.id.drawerLayout_MainActivity1,MainActivity.class));
        put(R.id.story2,new LayoutObject(R.id.story2,R.id.drawerLayout_MainActivity2,MainActivity2.class));
    }};

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
            LayoutObject layoutObject = layoutObjects.get(id);
            if(layoutObject!=null){
                if(validateNotSameActivity(layoutObject.getActivityClass())){
                    intent = new Intent(this, layoutObject.getActivityClass());
                    this.drawerLayout = findViewById(layoutObject.getDrawerLayoutId());
                }else{
                    this.drawerLayout.closeDrawer(GravityCompat.START);
                }
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

    //if clicking the same activity item, it does nothing
    private boolean validateNotSameActivity(Class className){
        if(this.getClass()==className){
            return false;
        }
        return true;
    }
}
