package com.unipi.tantoniou.telikh_ergasia.activities.dynamic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.ArrayMap;
import android.widget.ImageView;
import android.widget.TextView;

import com.unipi.tantoniou.telikh_ergasia.R;
import com.unipi.tantoniou.telikh_ergasia.customActivity.CustomMenuActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticsActivity extends CustomMenuActivity {

    SharedPreferences preferences;
    ImageView image;
    TextView favouriteStory;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        drawMenu(R.id.drawerLayout_StatisticsActivity);
        addNavOnClickEventListeners();

        image = findViewById(R.id.storyImage2);
        image.setImageDrawable(getResources().getDrawable(R.drawable.home_image));


        favouriteStory = findViewById(R.id.textView3);
        favouriteStory.setText("You have no favourite stories yet!");
        textView = findViewById(R.id.textView);
        textView.setText("You have no favourite stories yet!");
        getStats();
    }

    public void getStats(){
        preferences = getSharedPreferences("com.unipi.tantoniou.telikh_ergasia", MODE_PRIVATE);

        Map<String, Integer> statistics = new ArrayMap<>();
        statistics.put("Story1", preferences.getInt("Story1", 0));
        statistics.put("Story2",preferences.getInt("Story2", 0));
        statistics.put("Story3",preferences.getInt("Story3", 0));
        statistics.put("Story4",preferences.getInt("Story4", 0));
        statistics.put("Story5",preferences.getInt("Story5", 0));
        statistics.put("Story6", preferences.getInt("Story6", 0));
        StringBuffer text = new StringBuffer();

        for (String key : statistics.keySet()) {
            text.append(getTitle(key) + ": " + statistics.get(key)+" times\n");
        }
        textView.setText(text);
        String favourite = getFavourite(statistics);
        favouriteStory.setText(favourite);
    }

    public String getFavourite( Map<String, Integer> statistics){

        String maxSt="You have no favourite stories yet!";
        Integer max=0;
        for (String key : statistics.keySet()) {
            if (max<statistics.get(key)){
                maxSt = key;
                max = statistics.get(key);
            }
        }
        if (max==0){
            return maxSt;
        }
        return "Your favourite story is: "+ getTitle(maxSt) + "\nYou have heard it " + max+" times!";
    }

    public String getTitle(String story){
        switch (story){
            case "Story1":
                return "Beauty and the beast";

            case "Story2":
                return "Jack and the Beanstalk";

            case "Story3":
                return "Aladdin";

            case "Story4":
                return "Fiffy and Teddy";

            case "Story5":
                return "Matthew is up";

            case "Story6":
                return "Quantum Butterfly";

            default:
                return "";
        }
    }

}