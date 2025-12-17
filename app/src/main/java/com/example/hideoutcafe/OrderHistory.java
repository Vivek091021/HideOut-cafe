package com.example.hideoutcafe;

import static com.example.hideoutcafe.MainActivity.mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseError;

import java.util.List;

public class OrderHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        dbHelper = new DBHelper(this);
        recyclerView = findViewById(R.id.orderHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String userId = mobile; // Example user ID
        dbHelper.getAllData(userId, new CartDataValueEventListener() {
            @Override
            public void onDataLoaded(List<CartData> cartDataList) {
                // Handle the list of cart data
                // For example, update UI, populate RecyclerView, etc.
                CartDataAdapter adapter = new CartDataAdapter(cartDataList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTheme(R.style.Base_Theme_HideoutCafe);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}