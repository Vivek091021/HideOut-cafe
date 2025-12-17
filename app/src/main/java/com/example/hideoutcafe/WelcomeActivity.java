package com.example.hideoutcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class WelcomeActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String name, number;
    CircularProgressButton btn;
    EditText tname,tnumber;
    Bundle b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        btn = findViewById(R.id.btnSave);
        tname = findViewById(R.id.fullname);
        tnumber = findViewById(R.id.number);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        if(getIntent().getExtras() !=null) {
            b = getIntent().getExtras();
            number = b.getString("key");
        }

        tnumber.setText(number);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tname.getText().toString() != null && number != null) {
                    name = tname.getText().toString();

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("mobile", number);
                    hashMap.put("name", name);
                    databaseReference.child("users")
                            .child(number)
                            .setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(WelcomeActivity.this, "Data Added successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(WelcomeActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });

                }
            }
        });


    }
}