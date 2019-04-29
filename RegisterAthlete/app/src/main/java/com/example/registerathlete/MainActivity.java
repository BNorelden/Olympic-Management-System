package com.example.registerathlete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;

import Source.Athlete;
import Source.InputException;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText name, phone, age, email, country, gender, sport;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CollectionReference fsref = db.collection("Test");
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        country = findViewById(R.id.country);
        gender = findViewById(R.id.gender);
        sport = findViewById(R.id.sport);
        b = findViewById(R.id.btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String athleteName = name.getText().toString();
                String phoneNum = phone.getText().toString();
                int athleteAge = Integer.parseInt(age.getText().toString());
                String athleteEmail = email.getText().toString();
                String athleteCountry = country.getText().toString();
                char athleteGender = gender.getText().toString().charAt(0);
                ArrayList<String> sports = new ArrayList<String>();
                sports.add(sport.getText().toString());

                try {
                    Athlete athlete = new Athlete(athleteName, phoneNum, athleteAge, athleteEmail, athleteCountry, athleteGender, sports);

                    fsref.document(athleteName).set(athlete, SetOptions.merge());
                }
                catch(InputException e) {}

            }
        });

    }

}
