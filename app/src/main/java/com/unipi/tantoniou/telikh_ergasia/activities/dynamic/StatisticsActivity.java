package com.unipi.tantoniou.telikh_ergasia.activities.dynamic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.unipi.tantoniou.telikh_ergasia.R;
import com.unipi.tantoniou.telikh_ergasia.customActivity.CustomMenuActivity;

public class StatisticsActivity extends CustomMenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        drawMenu(R.id.drawerLayout_StatisticsActivity);
        addNavOnClickEventListeners();
    }
}