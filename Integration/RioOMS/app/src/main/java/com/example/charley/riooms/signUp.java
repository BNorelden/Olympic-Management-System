package com.example.charley.riooms;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;
    private CollectionReference userRef;
    private DocumentReference publicUserRef;

    EditText emailField, passwordField, confirmPassField;
    EditText nameField, phoneField, ageField;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        //FirebaseFirestore db = FirebaseFirestore.getInstance();

        db = FirebaseFirestore.getInstance();
        userRef = db.collection("user");
        publicUserRef = db.document("user/publicUser");

        emailField = findViewById(R.id.signup_email_field);
        passwordField = findViewById(R.id.signup_password_field);
        confirmPassField = findViewById(R.id.confirm_password_field);

        nameField = findViewById(R.id.name_field);
        phoneField = findViewById(R.id.phone_field);
        ageField = findViewById(R.id.age_field);

        register = findViewById(R.id.register_action);

        register.setOnClickListener(this);

    }

    private void register(){

        //todo after email change activity to prompt for name, age, phone num ....
        //todo once this works change to fragments for the next page to add name and other information
        //create an object with this data and store to cloud firestore
        //creating new page for more data can be done using fragments

        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        String confirmPass = confirmPassField.getText().toString().trim();

        //String id = mAuth.getUid();
        //String name = nameField.getText().toString().trim();
        //String phoneNum = phoneField.getText().toString().trim();
        //String age = ageField.getText().toString().trim();
        //int ageNum = Integer.parseInt(age);

        /*Map<String, Object> com.example.charley.riooms.user = new HashMap<>();
        //todo add support for first and last name
        com.example.charley.riooms.user.put("fName", name);
        com.example.charley.riooms.user.put("email", mAuth.getUid());
        com.example.charley.riooms.user.put("phone", phoneNum);
        com.example.charley.riooms.user.put("age", age); */

        //db cam be user, public, com.example.charley.riooms.employee ...
        //users user =new user()....
        //userRef.set(user)

        if(password.equals(confirmPass)){

                            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        //todo after sign ip should you go to main page or login apage

                                        //String id = mAuth.getUid();
                                        String id = mAuth.getCurrentUser().getUid();
                                        String name = nameField.getText().toString().trim();
                                        String phoneNum = phoneField.getText().toString().trim();
                                        String age = ageField.getText().toString().trim();
                                        int ageNum = Integer.parseInt(age);

                                        usersTEST user = new usersTEST(id, name, phoneNum, ageNum);

                                       /* userRef.set(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {

                                                    }
                                                });*/

                                       userRef.document(id).set(user);

                                        startActivity(new Intent(getApplicationContext(), homePage.class));

                                    } else {

                                        Toast.makeText(signUp.this, "Email or password invalid!", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
        }

        else{

            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.register_action:

                register();

                break;

        }

    }
}
