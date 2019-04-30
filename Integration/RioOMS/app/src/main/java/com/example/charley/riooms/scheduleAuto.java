package com.example.charley.riooms;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class scheduleAuto extends AppCompatActivity {
    private static final String TAG = "scheduleAuto";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText name, cat, type, venue, stday, stmonth
            , sthour, stmin, endday, endmonth, endhour, endmin, year, zone, price, seats;
    Button Submit;


    String nameS, catS,typeS,venueS,stdayS,stmonthS,sthourS,stminS,enddayS,endmonthS,endhourS,endminS,
    yearS,zoneS,priceS,seatsS;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_auto);

        final CollectionReference fsref =  db.collection("Test2");

        name = findViewById(R.id.name);
        cat = findViewById(R.id.cat);
        type = findViewById(R.id.type);
        venue = findViewById(R.id.venue);
        stday = findViewById(R.id.stday);
        stmonth = findViewById(R.id.stmonth);
        sthour = findViewById(R.id.sthour);
        stmin = findViewById(R.id.stmin);
        endday = findViewById(R.id.endday);
        endmonth = findViewById(R.id.endmonth);
        endhour = findViewById(R.id.endhour);
        endmin = findViewById(R.id.endmin);
        year = findViewById(R.id.year);
        zone = findViewById(R.id.zone);
        price = findViewById(R.id.price);
        seats = findViewById(R.id.seats);




        Submit = findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 nameS = name.getText().toString();
                 //catS = cat.getText().toString();
                 //typeS = type.getText().toString();
                 typeS = "S";
                 venueS = venue.getText().toString();
                 stdayS = stday.getText().toString();
                 stmonthS = stmonth.getText().toString();
                 sthourS = sthour.getText().toString();
                 stminS = stmin.getText().toString();
                 enddayS = endday.getText().toString();
                 endmonthS = endmonth.getText().toString();
                 endhourS = endhour.getText().toString();
                 endminS = endmin.getText().toString();
                 yearS = year.getText().toString();
                 zoneS = zone.getText().toString();
                 priceS = price.getText().toString();
                 seatsS = seats.getText().toString();




                Map<String, Object> event = new HashMap<>();

                event.put("event Name", nameS);
                event.put("category", catS);
                event.put("type", typeS);
                event.put("venue", venueS);
                event.put("startDay", stdayS);
                event.put("startMonth", stmonthS);
                event.put("startHr", sthourS);
                event.put("startMin", stminS);
                event.put("endDay", enddayS);
                event.put("endMonth", endmonthS);
                event.put("endHr", endhourS);
                event.put("endMin", endminS);
                event.put("year", yearS);
                event.put("zone", zoneS);
                event.put("price", priceS);
                event.put("seats", seatsS);

                String test = "";

                //fsref.document(nameS).set(event,SetOptions.merge());



/***************************docSNAPSHOT
                fsref.whereEqualTo("startHr",sthourS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(DocumentSnapshot doc : task.getResult()){
                                if(doc.getData().get("startHr") == null){
                                   // Toast.makeText(scheduleAuto.this, "Time Confilt Error!", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(scheduleAuto.this, "NO CONFLICT!!!!!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                                    Log.d("motorcycle", "if works");
                                }
                                else {
                                    Toast.makeText(scheduleAuto.this, "headache", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });*/


                fsref.whereLessThan("startHr",sthourS).whereGreaterThan("startHr",sthourS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()){
                            for(DocumentSnapshot doc : task.getResult()){
                                if(doc.exists()){
                                    Toast.makeText(scheduleAuto.this, "Time Confilt Error!", Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(scheduleAuto.this, "NO CONFLICT!!!!!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                                    Log.d("motorcycle", "if works");
                                }
                                else {
                                    Toast.makeText(scheduleAuto.this, "headache", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                });

            }

        });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_ath);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent0 = new Intent(scheduleAuto.this, athView.class);
                        startActivity(intent0);

                        return true;
                    case R.id.navigation_Filter:

                        Intent intent1 = new Intent(scheduleAuto.this,activityOneAth.class);
                        startActivity(intent1);

                        return true;
                    case R.id.navigation_Tickets:
                        Intent intent2 = new Intent(scheduleAuto.this,activityTwoAth.class);
                        startActivity(intent2);
                        return true;
                    case R.id.navigation_notifications:
                        Intent intent3 = new Intent(scheduleAuto.this,activityThreeAth.class);
                        startActivity(intent3);

                        return true;

                    case R.id.navigation_autograph:

                        // Intent intent4 = new Intent(scheduleAuto.this,scheduleAuto.class);
                        // startActivity(intent4);


                        return true;
                }
                return false;

            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);


    }

}
