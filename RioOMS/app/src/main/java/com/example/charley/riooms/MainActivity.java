package com.example.charley.riooms;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    EditText emailField, passwordField;
    Button loginBtn, signupBtn, guestBtn;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.password_field);

        loginBtn = findViewById(R.id.login_action);
        signupBtn = findViewById(R.id.signup_action);
        guestBtn = findViewById(R.id.cont_guest);

        //findViewById(R.id.login_action).setOnClickListener(this);

        loginBtn.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        guestBtn.setOnClickListener(this);

    }

    private void userLogin(){

        email = emailField.getText().toString().trim();
        password = passwordField.getText().toString().trim();

        //check input, email, password not empty, pass longer than 6 ...

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //startActivity(new Intent(getApplicationContext(), homePage.class));
                            //todo
                            startActivity(new Intent(getApplicationContext(), userView.class));

                        }

                        else {

                            Toast.makeText(MainActivity.this, "Login has failed!", Toast.LENGTH_SHORT).show();
                            //Log.w("Log in error",email+ password);

                        }

                    }
                });

    }

    private void guestLogin(){

        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    startActivity(new Intent(getApplicationContext(), homePage.class));
                    //todo change to the new one with tabs once signout works
                    //startActivity(new Intent(getApplicationContext(), userView.class));

                }

                else{

                    Toast.makeText(MainActivity.this, "Login in Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void signup(){

        startActivity(new Intent(getApplicationContext(), signUp.class));

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.cont_guest:
                guestLogin();
                break;

            case R.id.signup_action:

                signup();

                break;

            case R.id.login_action:

                //findViewById(R.id.signup_action).setVisibility(View.VISIBLE);

                userLogin();

                break;
        }

    }
}
