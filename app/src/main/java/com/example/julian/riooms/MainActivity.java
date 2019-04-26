package com.example.julian.riooms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private DocumentReference userRef;
    private DocumentReference empRef;
    private DocumentReference athRef;
    EditText emailField, passwordField;
    Button loginBtn, signupBtn, guestBtn;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);

        loginBtn = findViewById(R.id.login_action);
        signupBtn = findViewById(R.id.signup_action);
        guestBtn = findViewById(R.id.cont_guest);

        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        guestBtn.setOnClickListener(this);

        FirebaseMessaging.getInstance().subscribeToTopic("notifications");

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.login_action:
                userLogin();
                break;
            case R.id.signup_action:
                signUp();
                break;
            case R.id.cont_guest:
                guestLogin();
                break;
        }

    }

    public void userLogin() {

        try {
            email = emailField.getText().toString();
            password = passwordField.getText().toString();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        String currUser = mAuth.getCurrentUser().getUid();

                        userRef = db.collection("USER").document(currUser);
                        athRef = db.collection("ATHLETE").document(currUser);
                        empRef = db.collection("EMPLOYEE").document(currUser);

                        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();

                                    if (document.exists()) {
                                        //todo User View Activities
                                        Toast.makeText(MainActivity.this, "User Logs In!", Toast.LENGTH_LONG).show();
                                    } else {
                                        Log.d("Doc error", "error in finding doc");
                                    }
                                } else {
                                    Log.d("Doc error", "Error in oncomplete doc listener");
                                }

                            }

                        });

                        athRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();

                                    if (document.exists()) {
                                        //todo toast that this works
                                    } else {
                                        Log.d("Doc error", "error in finding doc");
                                    }
                                } else {
                                    Log.d("Doc error", "Error in oncomplete doc listener");
                                }

                            }

                        });

                        empRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();

                                    if (document.exists()) {
                                        //todo toast that this works
                                    } else {
                                        Log.d("Doc error", "error in finding doc");
                                    }
                                } else {
                                    Log.d("Doc error", "Error in oncomplete doc listener");
                                }

                            }

                        });
                    } else
                        Toast.makeText(MainActivity.this, "Incorrect email or password", Toast.LENGTH_LONG).show();

                }

            });
        }
        catch(IllegalArgumentException a) {
            Toast.makeText(MainActivity.this, "Please enter email and password to log in", Toast.LENGTH_LONG).show();
        }
        catch(Exception e) {
            Toast.makeText(MainActivity.this, "Something went wrong. Please try again later", Toast.LENGTH_LONG).show();
        }

    }

    public void signUp() {

        startActivity(new Intent(getApplicationContext(), SignUp.class));

    }

    public void guestLogin() {

        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    //todo Make app compatible for anonymous login
                    Toast.makeText(MainActivity.this, "Guest Login Works!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Something went wrong. Please try again later", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

}
