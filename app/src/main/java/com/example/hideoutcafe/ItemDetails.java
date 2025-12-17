package com.example.hideoutcafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class ItemDetails extends AppCompatActivity {

    TextView _title, _description, _price;
    ImageView imageView;
    Button button_add_to_cart;

    MaterialButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);


        imageView = findViewById(R.id.imageView);
        _title = findViewById(R.id._title);
        _description = findViewById(R.id._description);
        _price = findViewById(R.id._price);
        button_add_to_cart = findViewById(R.id.button_add_to_cart);


        Glide.with(this).load(getIntent().getStringExtra("image")).into(imageView);
        _title.setText(getIntent().getStringExtra("name"));
        _price.setText(getIntent().getStringExtra("price"));
        _description.setText(getIntent().getStringExtra("ingredients")+getIntent().getStringExtra("popularity"));


        button_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ItemDetails.this, ""+_title.getText()+_price.getText(), Toast.LENGTH_SHORT).show();
            }
        });














        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
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