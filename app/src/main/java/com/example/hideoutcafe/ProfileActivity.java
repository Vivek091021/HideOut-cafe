package com.example.hideoutcafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.TextView;

import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton;

public class ProfileActivity extends AppCompatActivity {
    ButtonAnimation a = new ButtonAnimation();
    Custom custom = new Custom();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        EditText txtusername  = findViewById(R.id.txtName);
        EditText txtmobile  = findViewById(R.id.txtNumber);
        CircularProgressButton btnSave  = findViewById(R.id.btnSave);
        txtusername.setText(getIntent().getStringExtra("username"));
        txtmobile.setText(getIntent().getStringExtra("mobile"));

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
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSave.startAnimation();
                custom.updateNameByMobile(txtmobile.getText().toString().substring(3),txtusername.getText().toString());
                a.stopAnimation(ProfileActivity.this, btnSave, true);
            }
        });
    }


}