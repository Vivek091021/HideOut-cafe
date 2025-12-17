package com.example.hideoutcafe;

import static androidx.core.app.PendingIntentCompat.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;


public class LoginActivity extends AppCompatActivity {


    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser currentUser;

    ButtonAnimation a = new ButtonAnimation();
    String varificationID;
    CircularProgressButton btn, btnVerify;
    LinearLayout l1, l2;

    EditText txtNumber;
    PinView pinView;
    static String number;



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.

//        SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
//        String defaultValue = null;
//        String device_id = sharedPref.getString(getString(R.string.device_id), defaultValue);
        //check exiting user
//        String did= Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//        if (device_id != null && device_id.equals(did)) {
//
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//            LoginActivity.this.finish();
//            System.exit(0);
//        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }



        l1 = findViewById(R.id.layout1);
        l2 = findViewById(R.id.layout2);
        txtNumber = findViewById(R.id.txtNumber);
        pinView = findViewById(R.id.pinview);
        btn = (CircularProgressButton) findViewById(R.id.btnSave);
        btnVerify = (CircularProgressButton) findViewById(R.id.btnVerifyOTP);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = String.valueOf(txtNumber.getText());


                btn.startAnimation();
                try {


                    if (number != null) {
                        sendvarificationcode(number);


                    } else {
                        a.stopAnimation(LoginActivity.this, btn, false);

                    }

                } catch (Exception e) {
                    a.stopAnimation(LoginActivity.this, btn, false);


                }


            }
        });
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnVerify.startAnimation();
                try {

                    String code = String.valueOf(pinView.getText());
                    if (code == null) {
                        a.stopAnimation(LoginActivity.this, btnVerify, false);

                    } else {

                        verifyCode(code);

                    }

                } catch (Exception e) {
                    a.stopAnimation(LoginActivity.this, btnVerify, false);


                }
            }
        });


    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(varificationID, code);
        signinByCredentials(credential);
    }

    private void signinByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

//                    SharedPreferences sharedPref = LoginActivity.this.getPreferences(Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPref.edit();
//                    editor.putString(getString(R.string.device_id), Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
//                    editor.apply();


                    databaseReference.child("users").child(number).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {


                                a.stopAnimation(LoginActivity.this, btnVerify, true);
                                Toast.makeText(LoginActivity.this, "User exist", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();


                            } else {
                                Toast.makeText(LoginActivity.this, "Number is not exist", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);

                                Bundle b = new Bundle();
                                b.putString("key", number);
                                intent.putExtras(b);
                                startActivity(intent);
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(LoginActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });


                    a.stopAnimation(LoginActivity.this, btnVerify, true);

                }
            }
        });
    }


    private void sendvarificationcode(String phoneNumber) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91" + phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            final String code = credential.getSmsCode();
            if (code != null) {
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(LoginActivity.this, "verification Failed" + String.valueOf(e), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(s, token);
            varificationID = s;
            Toast.makeText(LoginActivity.this, "Code send to entered mobile number", Toast.LENGTH_SHORT).show();
            a.stopAnimation(LoginActivity.this, btn, true);
            l1.setVisibility(View.GONE);
            l2.setVisibility(View.VISIBLE);
        }
    };


}