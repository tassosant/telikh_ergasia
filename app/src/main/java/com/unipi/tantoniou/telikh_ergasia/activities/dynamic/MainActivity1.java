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

    Button stopTheStory;

    SharedPreferences preferences;

    String story = "";

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
        stopTheStory = findViewById(R.id.button);
        stopTheStory.setVisibility(View.INVISIBLE);

        myTts = new MyTts(this);

    }

    @Override
    protected void onStop(){
        super.onStop();
        myTts.stopSpeaking();

    }

    @Override
    protected void onPause(){
        super.onPause();
        myTts.stopSpeaking();

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
        myTts.speak(this.valueEventListenerCustom.getText());
        System.out.println("EVA: "+this.valueEventListenerCustom.getText());
    }

    public void stopStory(View view){
        myTts.stopSpeaking();
    }

    public void getStory1(View view){
        // beauty and the beast
        myTts.stopSpeaking();
        storyReference = storiesReference.child("Story1");
        this.valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        valueEventListenerCustom.setText(story);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        stopTheStory.setVisibility(View.VISIBLE);
        chosenStory = 1;


    }
    public void getStory2(View view){
        // jack
        myTts.stopSpeaking();
        storyReference = storiesReference.child("Story2");
        this.valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        valueEventListenerCustom.setText(story);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        stopTheStory.setVisibility(View.VISIBLE);
        chosenStory = 2;

    }
    public void getStory3(View view){
        // aladin
        myTts.stopSpeaking();
        storyReference = storiesReference.child("Story3");
        this.valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        valueEventListenerCustom.setText(story);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        stopTheStory.setVisibility(View.VISIBLE);
        chosenStory = 3;
        setCounter("Story3");
    }
    public void getStory4(View view){
        // fifi and teady
        myTts.stopSpeaking();
        storyReference = storiesReference.child("Story4");
        this.valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        valueEventListenerCustom.setText(story);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        stopTheStory.setVisibility(View.VISIBLE);
        chosenStory = 4;
        setCounter("Story4");
    }

    public void getStory5(View view){
        // mathew is up
        myTts.stopSpeaking();
        storyReference = storiesReference.child("Story5");
        this.valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        valueEventListenerCustom.setText(story);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        stopTheStory.setVisibility(View.VISIBLE);
        chosenStory = 5;
        setCounter("Story5");
    }
    public void getStory6(View view){
        // quantum butterfly
        myTts.stopSpeaking();
        storyReference = storiesReference.child("Story6");
        this.valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
        valueEventListenerCustom.setText(story);
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
        tellTheStory.setVisibility(View.VISIBLE);
        stopTheStory.setVisibility(View.VISIBLE);
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