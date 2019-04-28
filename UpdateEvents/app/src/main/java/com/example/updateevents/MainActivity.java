package com.example.updateevents;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "Update Events";
    LinearLayout linearLayout;
    FirebaseFirestore db;
    EditText Ename, Evenue, cat, type, stday, stmonth
            , sthour, stmin, endday, endmonth, endhour, endmin, year, zone, price, seats;

    String name, venue, catS,typeS,stdayS,stmonthS,sthourS,stminS,enddayS,endmonthS,endhourS,endminS,
            yearS,zoneS,priceS,seatsS;
    Spinner spinner;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = findViewById(R.id.linearL);
        Ename = findViewById(R.id.editName);
        Evenue = findViewById(R.id.editVenue);
        cat = findViewById(R.id.editCat);
        type = findViewById(R.id.type);
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

        btn = findViewById(R.id.updateBtn);

        spinner = findViewById(R.id.spinner);

        String[] events = new String[]{


                //the event string array but currently empty, gets filled when it reads from firestore
        };

        final List<String> eventsList = new ArrayList<>(Arrays.asList(events));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,eventsList);

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerArrayAdapter);

       db = FirebaseFirestore.getInstance();

        db.collection("EVENTS")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (final QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());

                                final String docid = document.getId();



                                eventsList.add(docid);
                                spinnerArrayAdapter.notifyDataSetChanged();


                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                               final DocumentReference dr = db.collection("EVENTS").document(spinner.getSelectedItem().toString());


                                dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        DocumentSnapshot doc = task.getResult();

                                        name = doc.getString("event Name");
                                        venue = doc.getString("venue");
                                        catS = doc.getString("category");
                                        typeS =doc.getString("type");
                                        stdayS = doc.getString("startDay");
                                        stmonthS = doc.getString("startMonth");
                                        sthourS = doc.getString("startHr");
                                        stminS = doc.getString("startMin");
                                        enddayS = doc.getString("endDay");
                                        endmonthS = doc.getString("endMonth");
                                        endhourS = doc.getString("endHr");
                                        endminS = doc.getString("endMin");
                                        yearS = doc.getString("category");
                                        zoneS = doc.getString("zone");
                                        priceS = doc.getString("price");
                                        seatsS = doc.getString("seats");


                                        Ename.setText(name);
                                        Evenue.setText(venue);
                                        cat.setText((catS));
                                        type.setText(typeS);
                                        stday.setText(stdayS);
                                        stmonth.setText(stmonthS);
                                        sthour.setText(sthourS);
                                        stmin.setText(stminS);
                                        endday.setText(enddayS);
                                        endmonth.setText(endmonthS);
                                        endhour.setText(endhourS);
                                        endmin.setText(endminS);
                                        year.setText(yearS);
                                        zone.setText(zoneS);
                                        price.setText(priceS);
                                        seats.setText(seatsS);


                                        btn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                Map<String, Object> changes = new HashMap<>();
                                                changes.put("event Name", Ename.getText().toString());
                                                changes.put("category", cat.getText().toString());
                                                changes.put("type", type.getText().toString());
                                                changes.put("venue", Evenue.getText().toString());
                                                changes.put("startDay", stday.getText().toString());
                                                changes.put("startMonth", stmonth.getText().toString());
                                                changes.put("startHr", sthour.getText().toString());
                                                changes.put("startMin", stmin.getText().toString());
                                                changes.put("endDay", endday.getText().toString());
                                                changes.put("endMonth", endmonth.getText().toString());
                                                changes.put("endHr", endhour.getText().toString());
                                                changes.put("endMin", endmin.getText().toString());
                                                changes.put("year", year.getText().toString());
                                                changes.put("zone", zone.getText().toString());
                                                changes.put("price", price.getText().toString());
                                                changes.put("seats", seats.getText().toString());

                                                Toast.makeText(MainActivity.this, "This event has been changed: "+ docid, Toast.LENGTH_SHORT).show();

                                                dr.set(changes, SetOptions.merge());
                                                //once click should update the event info
                                                //eventsList.add("Apple");
                                                //spinnerArrayAdapter.notifyDataSetChanged();

                                            }
                                        });
                                    }   });

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parentView) {
                                        // your code here
                                    }

                                });


                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });

        }

    }
