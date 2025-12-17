package com.example.hideoutcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {

    CircularProgressButton btn;
    ButtonAnimation animation = new ButtonAnimation();
    TextView txtEmail;
    TextView txtLogin;

    FirebaseAuth auth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();
        btn= findViewById(R.id.btnReset);
        txtLogin = findViewById(R.id.txtLogin);
        txtEmail = findViewById(R.id.txtNumber);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn.startAnimation();
                try {
                    email = txtEmail.getText().toString().trim();
                    if (TextUtils.isEmpty(email)) {
                        animation.stopAnimation(Forgot_Password.this, btn, false);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 5s = 5000ms
                                btn.revertAnimation();
                            }
                        }, 2000);
                        Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        animation.stopAnimation(Forgot_Password.this, btn, true);
                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Do something after 5s = 5000ms
                                                btn.revertAnimation();
                                            }
                                        }, 2000);
                                        txtEmail.setText("");
                                        Toast.makeText(Forgot_Password.this, "Reset link sent to your email address", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                } catch (Exception e) {
                    animation.stopAnimation(Forgot_Password.this, btn, false);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            btn.revertAnimation();
                        }
                    }, 2000);
                    Toast.makeText(getApplication(), "Enter your registered email id", Toast.LENGTH_SHORT).show();
                }
            }

        });
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Forgot_Password.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}