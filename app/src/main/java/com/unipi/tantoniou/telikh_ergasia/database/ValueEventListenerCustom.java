package com.unipi.tantoniou.telikh_ergasia.database;

import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;

public class ValueEventListenerCustom implements ValueEventListener {

    StorageReference storageReference;
    FirebaseDatabase firebaseDatabase;

    ImageView storyImage;

    TextView title;

    TextView writer;
    TextView text;

    private int id;




    public ValueEventListenerCustom(ImageView imageView, StorageReference storageReference) {
        this.storageReference = storageReference;
        this.storyImage = imageView;
        initProperties();
    }

    public ValueEventListenerCustom(StorageReference storageReference) {
        this.storageReference = storageReference;
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
        getImage(snapshot);
//        getElementText(snapshot);
        getElementTitle(snapshot);
//        getElementWriter(snapshot);
//        getElementId(snapshot);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    private void getImage(DataSnapshot snapshot){
        if(storyImage==null){
            return;
        }
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

    private void getElementTitle(DataSnapshot dataSnapshot){
       this.title.setText(dataSnapshot.child("Title").getValue().toString());
    }

    private void getElementText(DataSnapshot dataSnapshot){
        this.text.setText(dataSnapshot.child("Text").getValue().toString());
    }

    private void getElementId(DataSnapshot dataSnapshot){
       //this.id = dataSnapshot.child("Id").getValue(Integer.class);
    }

    private void getElementWriter(DataSnapshot dataSnapshot){
       this.writer.setText(dataSnapshot.child("Writer").getValue().toString());
    }

    private void getTitle(DataSnapshot snapshot){

    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getWriter() {
        return writer;
    }

    public void setWriter(TextView writer) {
        this.writer = writer;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }
}
