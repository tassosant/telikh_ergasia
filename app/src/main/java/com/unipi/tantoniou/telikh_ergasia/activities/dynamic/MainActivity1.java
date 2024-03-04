package com.unipi.tantoniou.telikh_ergasia.activities.dynamic;

import android.os.Bundle;
import android.view.View;
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

    ImageView storyImage;

    TextView textViewTitle;
    MyTts myTts;

    String story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main1);
        drawMenu(R.id.drawerLayout_MainActivity1);

        addNavOnClickEventListeners();
        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseDatabase = FirebaseDatabase.getInstance();
        storiesReference = firebaseDatabase.getReference("Stories");
        storyReference = storiesReference.child("Story1");
        storyImage = findViewById(R.id.storyImage);
        textViewTitle = findViewById(R.id.textViewTitle);
        getStory();
        story = "Beauty and the beast";
        myTts = new MyTts(this);

    }



    public void getStory(){
//        ValueEventListenerCustom valueEventListenerCustom = new ValueEventListenerCustom(storyImage,storageReference);
        ValueEventListenerCustom valueEventListenerCustom = new ValueEventListenerCustom(storageReference);
        valueEventListenerCustom.setStoryImage(storyImage);
        valueEventListenerCustom.setTitle(textViewTitle);
//        storyReference.addListenerForSingleValueEvent(new ValueEventListenerCustom(storyImage,storageReference));
        storyReference.addListenerForSingleValueEvent(valueEventListenerCustom);
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void tellStory(View view){
        myTts.speak(story);
    }

}