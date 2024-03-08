package com.unipi.tantoniou.telikh_ergasia.customActivity;

import androidx.appcompat.app.ActionBar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationView;
import com.unipi.tantoniou.telikh_ergasia.R;
import com.unipi.tantoniou.telikh_ergasia.activities.dynamic.MainActivity;
import com.unipi.tantoniou.telikh_ergasia.activities.dynamic.MainActivity1;
import com.unipi.tantoniou.telikh_ergasia.activities.dynamic.StatisticsActivity;

import java.util.HashMap;
import java.util.Locale;

public abstract class CustomMenuActivity extends AppCompatActivity{
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public ActionBar actionBar;

    public Spinner spinner;

    public static HashMap<Integer,String> mapPositionWithLang= new HashMap<Integer,String>(){{
        put(0,"en");
        put(1,"de");
        put(2,"el");
    }};

    //map them with menu items
    public static final HashMap<Integer, LayoutObject> layoutObjects= new HashMap<Integer,LayoutObject>()
    {{
        put(R.id.home,new LayoutObject(R.id.home,R.id.drawerLayout_MainActivity, MainActivity.class));
        put(R.id.story1,new LayoutObject(R.id.story1,R.id.drawerLayout_MainActivity1,MainActivity1.class));
        put(R.id.statistics,new LayoutObject(R.id.statistics,R.id.drawerLayout_StatisticsActivity, StatisticsActivity.class));
//        put(R.id.story2,new LayoutObject(R.id.story2,R.id.drawerLayout_MainActivity2, MainActivity1.class));
    }};

//    public static HashMap<>

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Activity started");



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

//        this.spinner = findViewById(R.id.spinner_language);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.language_options, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        this.spinner.setAdapter(adapter);
//        addSpinnerListeners(this.spinner);
//        this.drawerLayout.addDrawerListener(this.spinner);


        this.actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.actionBar = this.getSupportActionBar();
        assert this.actionBar != null;
        this.actionBar.setDisplayHomeAsUpEnabled(true);


//        Toolbar toolbar = findViewById(R.id.toolbar_language);
//        this.actionBar.setCustomView(toolbar);
//        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, this.drawerLayout, R.string.nav_open, R.string.nav_close);
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }



    public void addNavOnClickEventListeners(){

//        Intent intent = new Intent(this, MainActivity1.class);
//        intent.putExtra("mykey1",editText.getText().toString());
//        startActivity(intent);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        Menu menu = navigationView.getMenu();
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
            }else{

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

    private void addSpinnerListeners(Spinner spinner){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                String language = "en";


                language = mapPositionWithLang.get(position);
                SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
                String defLang = "en";
                int selectedPosition = sharedPreferences.getInt("SelectedLanguagePosition",0);


                if(selectedPosition!=position){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("SelectedLanguagePosition", position);
                    editor.apply();
                    setLocale(language);
                }


//                if(!defLang.equals(language)) {
//
//
//
//
//
////                    editor.putString("selectedLanguage",language);
//
//
//
//                }else if(!selectedLang.equals(language)){
////                    selectedPosition  = sharedPreferences.getInt("SelectedLanguagePosition",0);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putInt("SelectedLanguagePosition", position);
////                    editor.putString("selectedLanguage",language);
//
//                    editor.apply();
//                    setLocale(language);
//                }

//                    SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
//                    String languagePref = prefs.getString("My_Lang", "en"); // Default to English if not set
                    //setLocale(languagePref);
                // Handle language selection
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Another interface callback
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.language_menu, menu);
        MenuItem item = menu.findItem(R.id.language);
        this.spinner= (Spinner) item.getActionView();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.language_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner.setAdapter(adapter);

        // Set item selected listener, etc.
        addSpinnerListeners(this.spinner);
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        int selectedPosition = sharedPreferences.getInt("SelectedLanguagePosition",0);
        if(spinner!=null) {
            spinner.setSelection(selectedPosition);
        }

        return true;

    }

    @SuppressWarnings("deprecation")
    public void setLocale(String langCode) { // langCode = "en", "es", "fr", ...
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);
        Resources res = getResources();
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());

        // Save the current language in preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();

        editor.putString("My_Lang", langCode);
        editor.apply();
        // Refresh the current activity to apply the new language by recreating it
        reloadActivity();

    }


    private void reloadActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
