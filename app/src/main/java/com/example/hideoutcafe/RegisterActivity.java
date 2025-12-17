package com.example.hideoutcafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    CircularProgressButton btnRegister;
    EditText txtEmail, txtPassword;
    FirebaseAuth mAuth;
    TextView login;
    ButtonAnimation a = new ButtonAnimation();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRegister = findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.txtNumber);
        txtPassword = findViewById(R.id.txtCode);
        login = findViewById(R.id.txtLogin);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegister.startAnimation();
                try {

                    String email = String.valueOf(txtEmail.getText());
                    String password = String.valueOf(txtPassword.getText());

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information

                                        FirebaseUser user = mAuth.getCurrentUser();

                                        a.stopAnimation(RegisterActivity.this,btnRegister,true);
                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Do something after 5s = 5000ms
                                                btnRegister.revertAnimation();
                                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                        }, 2000);

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        a.stopAnimation(RegisterActivity.this,btnRegister,false);
                                        final Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                // Do something after 5s = 5000ms
                                                btnRegister.revertAnimation();
                                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        }, 2000);


                                    }
                                }
                            });
                }catch (Exception e){

                    a.stopAnimation(RegisterActivity.this,btnRegister,false);
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // Do something after 5s = 5000ms
                            btnRegister.revertAnimation();
                        }
                    }, 2000);
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}