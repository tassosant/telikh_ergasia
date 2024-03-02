package com.unipi.tantoniou.telikh_ergasia.activities.dynamic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.unipi.tantoniou.telikh_ergasia.R;
import com.unipi.tantoniou.telikh_ergasia.customActivity.CustomActivity;

public class MainActivity2 extends CustomActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        drawMenu(R.id.drawerLayout_MainActivity2);
        addNavOnClickEventListeners();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}