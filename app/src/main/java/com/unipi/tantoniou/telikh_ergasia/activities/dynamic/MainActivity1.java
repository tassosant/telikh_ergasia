package com.unipi.tantoniou.telikh_ergasia.activities.dynamic;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.unipi.tantoniou.telikh_ergasia.R;
import com.unipi.tantoniou.telikh_ergasia.database.ValueEventListenerCustom;
import com.unipi.tantoniou.telikh_ergasia.customActivity.CustomMenuActivity;

public class MainActivity1 extends CustomMenuActivity {
    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference storiesReference,storyReference;
    ValueEventListenerCustom valueEventListenerCustom;
    ImageView storyImage;

    TextView textViewTitle;
    MyTts myTts;

    Button tellTheStory;

    SharedPreferences preferences;

    int chosenStory=0;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("com.unipi.tantoniou.telikh_ergasia", MODE_PRIVATE);

        setContentView(R.layout.activity_main1);
        drawMenu(R.id.drawerLayout_MainActivity1);
        addNavOnClickEventListeners();

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        storiesReference = firebaseDatabase.getReference("Stories");

        storyImage = findViewById(R.id.storyImage);
        storyImage.setImageDrawable(getResources().getDrawable(R.drawable.home_image));
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewTitle.setText("Welcome to audio Fairy Tales");
        tellTheStory = findViewById(R.id.button3);
        tellTheStory.setVisibility(View.INVISIBLE);

        myTts = new MyTts(this);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void tellStory(View view){
        switch (chosenStory){
            case 1:
                setCounter("Story1");
                break;
            case 2:
                setCounter("Story2");
                break;
            case 3:
                setCounter("Story3");
                break;
            case 4:
                setCounter("Story4");
                break;
            case 5:
                setCounter("Story5");
                break;
            case 6:
                setCounter("Story6");
                break;
        }
        myTts.speak(textViewTitle.getText().toString());

    }

    public void getStory1(View view){
        storyReference = storiesReference.child("Story1");
        ValueEventListenerCustom valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        chosenStory = 1;


    }
    public void getStory2(View view){
        storyReference = storiesReference.child("Story2");
        ValueEventListenerCustom valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        chosenStory = 2;

    }
    public void getStory3(View view){
        storyReference = storiesReference.child("Story2");
        ValueEventListenerCustom valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        chosenStory = 3;
        setCounter("Story3");
    }
    public void getStory4(View view){
        storyReference = storiesReference.child("Story2");
        ValueEventListenerCustom valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        chosenStory = 4;
        setCounter("Story4");
    }

    public void getStory5(View view){
        storyReference = storiesReference.child("Story2");
        ValueEventListenerCustom valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        chosenStory = 5;
        setCounter("Story5");
    }
    public void getStory6(View view){
        storyReference = storiesReference.child("Story2");
        ValueEventListenerCustom valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        chosenStory = 6;
        setCounter("Story6");
    }

    public void setCounter(String story){
        int counter = preferences.getInt(story, 0);
        counter++;
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(story, counter);
        editor.apply();

    }
}