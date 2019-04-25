package com.example.julian.riooms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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



    }



}
