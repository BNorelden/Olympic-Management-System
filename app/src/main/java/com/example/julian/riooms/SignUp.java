package com.example.julian.riooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import Source.InputException;
import Source.User;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private CollectionReference userRef;

    EditText emailField, passwordField, confirmPassField, nameField, phoneField, ageField;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirebaseFirestore db;

        mAuth = FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
        userRef = db.collection("USER");

        emailField = findViewById(R.id.signup_email_field);
        passwordField = findViewById(R.id.signup_password_field);
        confirmPassField = findViewById(R.id.confirm_password_field);

        nameField = findViewById(R.id.name_field);
        phoneField = findViewById(R.id.phone_field);
        ageField = findViewById(R.id.age_field);

        register = findViewById(R.id.register_action);
        register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.register_action:
                register();
                break;
        }

    }

    public void register() {

        final String email, password, confirmPass, name, phoneNum, age;

        try {
            email = emailField.getText().toString();
            password = passwordField.getText().toString();
            confirmPass = confirmPassField.getText().toString();
            name = nameField.getText().toString();
            phoneNum = phoneField.getText().toString();
            age = ageField.getText().toString();

            if (password.equals(confirmPass)) {
                try {
                    final User user = new User(name, phoneNum, age, email);
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        String id;

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                id = task.getResult().getUser().getUid();
                                userRef.document(id).set(user);

                                Toast.makeText(SignUp.this, "Welcome " + name + ". Please log in", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            } else {
                                Toast.makeText(SignUp.this, "" + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }

                    });
                }
                catch (InputException i) {
                    Toast.makeText(SignUp.this, "" + i, Toast.LENGTH_LONG).show();
                }
            } else
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_LONG).show();
        }
        catch(IllegalArgumentException a) {
            Toast.makeText(SignUp.this, "Some fields are empty. Please fill out all fields", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(SignUp.this, "Something went wrong. Please try again later" + e, Toast.LENGTH_LONG).show();
        }


    }

}
