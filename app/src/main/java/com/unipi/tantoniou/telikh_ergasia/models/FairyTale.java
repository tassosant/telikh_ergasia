package com.unipi.tantoniou.telikh_ergasia.models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.unipi.tantoniou.telikh_ergasia.database.ValueEventListenerCustom;

public class FairyTale{

    ValueEventListenerCustom valueEventListenerCustom;
    StorageReference storageReference;

    DatabaseReference childReference;

    private String firebaseChild;

    private int id;
    private String title;
    private String text;

    private String writer;

    private String image;

    public FairyTale(int id, String title, String text, String writer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.writer = writer;
    }

    public FairyTale(String firebaseChild, DatabaseReference childReference) {
        this.firebaseChild = firebaseChild;
        this.childReference = childReference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    private void getAllValuesExceptImage(){
        DataSnapshot snapshot;
        this.valueEventListenerCustom = new ValueEventListenerCustom(this.storageReference);
        childReference.addValueEventListener(this.valueEventListenerCustom);
    }


}
