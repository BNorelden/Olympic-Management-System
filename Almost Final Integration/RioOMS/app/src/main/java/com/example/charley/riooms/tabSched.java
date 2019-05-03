package com.example.charley.riooms;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class tabSched extends Fragment{

    LinearLayout eLayout;private static final String TAG = "scheduleEvent";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText name, cat, type, venue, stday, stmonth
            , sthour, stmin, endday, endmonth, endhour, endmin, year, zone, price, seats;
    Button Submit;


    String nameS, catS,typeS,venueS,stdayS,stmonthS,sthourS,stminS,enddayS,endmonthS,endhourS,endminS,
            yearS,zoneS,priceS,seatsS;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sched_event, container, false);

        eLayout = rootView.findViewById(R.id.events);

        final CollectionReference fsref =  db.collection("Test2");

        name = rootView.findViewById(R.id.name);
        cat = rootView.findViewById(R.id.cat);
        type = rootView.findViewById(R.id.type);
        venue = rootView.findViewById(R.id.venue);
        stday = rootView.findViewById(R.id.stday);
        stmonth = rootView.findViewById(R.id.stmonth);
        sthour = rootView.findViewById(R.id.sthour);
        stmin = rootView.findViewById(R.id.stmin);
        endday = rootView.findViewById(R.id.endday);
        endmonth = rootView.findViewById(R.id.endmonth);
        endhour = rootView.findViewById(R.id.endhour);
        endmin = rootView.findViewById(R.id.endmin);
        year = rootView.findViewById(R.id.year);
        zone = rootView.findViewById(R.id.zone);
        price = rootView.findViewById(R.id.price);
        seats = rootView.findViewById(R.id.seats);




        Submit = rootView.findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameS = name.getText().toString();
                catS = cat.getText().toString();
                typeS = type.getText().toString();
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
                                    Toast.makeText(getContext(), "Time Confilt Error!", Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(scheduleAuto.this, "NO CONFLICT!!!!!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                                    Log.d("motorcycle", "if works");
                                }
                                else {
                                    Toast.makeText(getContext(), "headache", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                });

            }

        });


        return rootView;
    }


}
