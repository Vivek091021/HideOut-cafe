package com.example.hideoutcafe;

import com.google.firebase.database.DatabaseError;

import java.util.List;

public interface CartDataValueEventListener {
    void onDataLoaded(List<CartData> cartDataList);
    void onCancelled(DatabaseError databaseError);
}