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

    TextView age;

    TextView writer;
    String text;

    private int id;




//    public ValueEventListenerCustom(ImageView imageView, StorageReference storageReference) {
//        this.storageReference = storageReference;
//        this.storyImage = imageView;
//        // EVA:
//        initProperties();
//    }

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
        if (this.storyImage!=null){
            getImage(snapshot);
        }
//        if (this.text!=null){
            getElementText(snapshot);
//        }
        if (this.title!=null){
            getElementTitle(snapshot);
        }


        if(this.writer!=null) {
            getElementWriter(snapshot);
        }

        if(this.age!=null){
            getElementAge(snapshot);
        }

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
//        this.text.setText(dataSnapshot.child("Text").getValue().toString());
        this.text=dataSnapshot.child("Text").getValue().toString();
    }

    private void getElementId(DataSnapshot dataSnapshot){
       //this.id = dataSnapshot.child("Id").getValue(Integer.class);
    }

    private void getElementWriter(DataSnapshot dataSnapshot){
        StringBuilder stringBuilder = new StringBuilder("Writer:");
        stringBuilder.append(dataSnapshot.child("Writer").getValue().toString());
       this.writer.setText(stringBuilder.toString());
    }

    private void getElementAge(DataSnapshot dataSnapshot){
        StringBuilder stringBuilder = new StringBuilder("Age:");
        stringBuilder.append(dataSnapshot.child("Age").getValue().toString());
        this.age.setText(stringBuilder);
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public TextView getAge() {
        return age;
    }

    public void setAge(TextView age) {
        this.age = age;
    }
}
