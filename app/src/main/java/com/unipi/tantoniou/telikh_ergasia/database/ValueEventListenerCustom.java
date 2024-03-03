package com.unipi.tantoniou.telikh_ergasia.database;

import android.graphics.BitmapFactory;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class ValueEventListenerCustom implements ValueEventListener {

    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;


    ImageView storyImage;
    public ValueEventListenerCustom(ImageView imageView, StorageReference storageReference) {
        this.storageReference =storageReference;
        this.storyImage = imageView;
        initProperties();
    }

    public ImageView getStoryImage() {
        return storyImage;

    }

    public void setStoryImage(ImageView storyImage) {
        this.storyImage = storyImage;
    }

    private void initProperties(){
//        storageReference = FirebaseStorage.getInstance().getReference();
//        firebaseDatabase = FirebaseDatabase.getInstance();

    }



    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                recipeDescription.setText(snapshot.child("Description").getValue().toString());
        String imageFile = snapshot.child("Image").getValue().toString();
        try {
            File file = File.createTempFile("temp","jpg");
            StorageReference imageRef = storageReference.child(imageFile);
            imageRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    storyImage.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
}
