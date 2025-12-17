package com.example.hideoutcafe;

import static com.example.hideoutcafe.MainActivity.mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentActivity extends AppCompatActivity {
DBHelper dbHelper;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PaymentActivity.this,MainActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        dbHelper = new DBHelper(getApplicationContext());
        TextView subT = findViewById(R.id.sub_total);
        TextView total = findViewById(R.id.total_amt);
        subT.setText(String.valueOf(dbHelper.sumOfPrice()));
        total.setText(String.valueOf(dbHelper.sumOfPrice()));


        Button pay_btn = findViewById(R.id.pay_btn);
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.addToFirebase(mobile);
                dbHelper.deleteAll();
                Toast.makeText(getApplicationContext(), "Your PAYMENT Successfully AND MOJ KARO", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
} 