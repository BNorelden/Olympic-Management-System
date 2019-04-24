package com.example.charley.riooms;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.support.constraint.Constraints.TAG;

import static com.example.charley.riooms.eList.swapS;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class popUpDynamic extends Activity implements View.OnClickListener {

    FirebaseFirestore fs;
    TextView tView, tViewDesc;
    Button b2;
    String holds = swapS;
    DocumentReference fsref;
    EditText etext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_dynamic);

         tView = findViewById(R.id.tView);
         b2 = findViewById(R.id.b2);
         b2.setOnClickListener(this);
        etext = findViewById(R.id.eText);
        tViewDesc = findViewById(R.id.tViewDesc);
        DisplayMetrics dm =  new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.85), (int)(height*0.85));

        fs = FirebaseFirestore.getInstance();

        CollectionReference ds = fs.collection("EVENTS");
        fsref =  fs.collection("EVENTS").document(holds); //works

        fsref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();


                    String name = doc.getString("event Name");
                    String cat = doc.getString("category");
                    String type = doc.getString("type");
                    char t = type.charAt(0);
                    String venue = doc.getString("venue");
                    String docID = doc.getId();
                    int startDay = parseInt(doc.getString("startDay"));
                    int startMonth = parseInt(doc.getString("startMonth"));
                    int startHr = parseInt(doc.getString("startHr"));
                    int startMin = parseInt(doc.getString("startMin"));
                    int endDay = parseInt(doc.getString("endDay"));
                    int endMonth = parseInt(doc.getString("endMonth"));
                    int endHr = parseInt(doc.getString("endHr"));
                    int endMin = parseInt(doc.getString("endMin"));
                    int year = parseInt(doc.getString("year"));
                    String zone = doc.getString("zone");
                    float price = parseFloat(doc.getString("price"));
                    int seats = parseInt(doc.getString("seats"));


                    StringBuilder data = new StringBuilder("");

                    data.append("Event Name: ").append(name);
                    data.append("\nCat: ").append(cat);
                    data.append("\nType: ").append(t);
                    data.append("\nVenue: ").append(venue);
                    data.append("\nStart Date: ").append(startMonth+"/"+startDay+"/"+year);
                    data.append("\nStart Time: ").append(startHr +":"+ startMin +" "+ zone);
                    data.append("\nEnd Date: ").append(endMonth+"/"+endDay+"/"+year);
                    data.append("\nEnd Time: ").append(endHr +":"+ endMin +" "+ zone);
                    data.append("\nPrice: ").append(price);                                               // Works beautifully but haven't tested it dynamically
                    data.append("\nseats: ").append(seats);


                    tView.setText(data.toString());

                }
            }
        });

    }


    @Override
    public void onClick(View v){

        if (v.getId()==R.id.b2){

            //Intent intent = new Intent(popUpDynamic.this, PurchaseTic.class);
            //startActivity(intent);

            fsref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                Map<String, Object> seatsdec = new HashMap<>();

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    String ticcounts = etext.getText().toString(); //input to string
                    int ticcount = parseInt(ticcounts);             // input string to int
                    if (task.isSuccessful()) {

                        DocumentSnapshot doc = task.getResult();

                        int seatsint = parseInt(doc.getString("seats"));
                        if (ticcount > seatsint && seatsint != 0) {
                            b2.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Cannot do that, not enough tickets",
                                    Toast.LENGTH_LONG).show();
                            etext.getText().clear();
                        } else if (seatsint <= 0) {
                            tView.setText("SORRY, SOLD OUT!!!");
                            tView.setTextSize(30);
                            b2.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "TICKETS ARE SOLD OUT",
                                    Toast.LENGTH_LONG).show();
                        }

                        else if (ticcount <= 0) {
                            b2.setVisibility(View.VISIBLE);
                            Toast.makeText(getApplicationContext(), "Enter Positive Value",
                                    Toast.LENGTH_LONG).show();
                            etext.getText().clear();
                        }
                         else {
                            b2.setVisibility(View.VISIBLE);
                            seatsint = seatsint - ticcount;
                            String seatsf = Integer.toString(seatsint);
                            seatsdec.put("seats", seatsf);

                            Toast.makeText(getApplicationContext(), "Your purchased " + ticcount + " and there is " + seatsf + "Tickets left  !!!",
                                    Toast.LENGTH_LONG).show();


                            fsref.set(seatsdec, SetOptions.merge());
                        }
                    }

                }
            });
        }

    }

    private void readSingleContact(){

        DocumentReference fsref =  fs.collection("EVENTS").document("Men's Floor Exercise"); // this was for testing

        fsref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();

                    //(String name, String category, char type, String venue, int startDay, int startMonth, int startHr, int startMin,
                    //					  int endDay, int endMonth, int endHr, int endMin, int year, String zone, float price, int seats)

                    String name = doc.getString("event Name");
                    String cat = doc.getString("category");
                    String type = doc.getString("type");
                    char t = type.charAt(0);
                    String venue = doc.getString("venue");

                    int startDay = parseInt(doc.getString("startDay"));
                    int startMonth = Integer.valueOf(doc.getString("startMonth"));
                    int startHr = parseInt(doc.getString("startHr"));
                    int startMin = parseInt(doc.getString("startMin"));
                    int endDay = parseInt(doc.getString("endDay"));
                    int endMonth = parseInt(doc.getString("endMonth"));
                    int endHr = parseInt(doc.getString("endHr"));
                    int endMin = parseInt(doc.getString("endMin"));
                    int year = parseInt(doc.getString("year"));
                    String zone = doc.getString("zone");
                    float price = parseFloat(doc.getString("price"));
                    int seats = parseInt(doc.getString("seats"));




                    StringBuilder data = new StringBuilder("");
                    data.append("Event Name: ").append(name);
                    data.append("\nCat: ").append(cat);
                    data.append("\nType: ").append(t);

                    tView.setText(data.toString());
                    String ya1 = "Event: "+ name + "\n" + "Cat: " +  cat + "  Type: " + t + "\n" + "Start Date: " + startMonth + "/" +startDay + "/" + year + "\nStart Time: " +
                            startHr +":" + startMin ;
                    tView.setText(ya1);


                }
            }
        });

    }


    private void readAll() {            //it should read all the documents and just get all Id's with a counter


        fs.collection("EVENTS")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        String ya2 = "";
                        int counter=0;

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                counter++;


                                ya2 = ya2 + " C:"+counter+" " + document.getId() + "\n";

                                tView.setText(ya2);

                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}
