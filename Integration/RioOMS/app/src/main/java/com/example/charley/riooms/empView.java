package com.example.charley.riooms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class empView extends AppCompatActivity {
    private static String TAG = "empView";
    private TextView eventName, dateText, timeText, venueName, seatsText;
    private CardView eventCards;
    LayoutParams layoutparams;
    Button viewTicketsBtn;

   // FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText name, phone, age, email, country, gender, sport;
    Button b;



    LinearLayout linearLayout;
    FirebaseFirestore db;
    /* to read
    db.collection("users")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        }); */

    //Context context = getActivity().getApplicationContext();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_regAth:

                    //mTextMessage.setVisibility(View.VISIBLE);

                   // mTextMessage.setPadding(120,0,0,0);
                   // mTextMessage.setText(R.string.title_home);
                    // here goes the code for
                    return true;
                case R.id.navigation_sched:

                    Intent intent = new Intent(empView.this, scheduleEvent.class);
                    startActivity(intent);

                    // here goes the code for filter
                    return true;

                case R.id.navigation_Filter:

                    Intent intent2 = new Intent(empView.this,activityOneEmp.class);
                    startActivity(intent2);

                    return true;
                case R.id.navigation_Tickets:

                    Intent intent3 = new Intent(empView.this,activityTwoEmp.class);
                    startActivity(intent3);

                    return true;
                case R.id.navigation_notifications:

                    Intent intent4 = new Intent(empView.this,activityThreeEmp.class);
                    startActivity(intent4);


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_emp_view);
        setContentView(R.layout.activity_reg_ath);

        db = FirebaseFirestore.getInstance();

        Context context = getApplicationContext();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_emp);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        linearLayout = (LinearLayout) findViewById(R.id.L1);

        //eList etest = new eList();
        //etest.displayeList(linearLayout, context);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_out, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            // do something here

            FirebaseMessaging.getInstance().unsubscribeFromTopic("security");

            FirebaseAuth.getInstance().signOut();

            Intent intents = new Intent(empView.this, MainActivity.class);
            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);//IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intents);

            finish();

        }

        return super.onOptionsItemSelected(item);

    }
}
