package com.example.charley.riooms;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.Map;

import static android.content.ContentValues.TAG;

public class eList {

    static String swapS;
    String userId;
    FirebaseFirestore db;

    CardView eventCards;
    Context context1;
    LinearLayout.LayoutParams layoutparams;
    TextView eventName, dateText, dateTexte, timeText, timeTexte, venueName, seatsText;

    LinearLayout linearLayout;
    Button viewTicketsBtn;

    public void displayeList(View layout, Context context){
    db = FirebaseFirestore.getInstance();
    context1 = context;

    linearLayout = (LinearLayout) layout;

    CollectionReference eve = db.collection("EVENTS");
    Query query = eve.whereEqualTo("event Name", true); //******************************
      //  eve.whereEqualTo(!=null)
        eve.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    //read each result, for each result create a card
                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                                //****************************************************************************
                        if (document.exists()) {                //***************** THERE IS WHERE IT CHECKS IF THE DOCUMENT IS EMPTY("NULL")
                            Map<String, Object> map = document.getData();
                            if (map.size() == 0) {
                            //if (document.get("startHr") == null) {
                                Log.d(TAG, "Document is empty!");       //****************************************************************************
                            } else {                                         // PRINTS THE STUFF WHEN ITS NOT EMPTY

                                final String did = document.getId();
                                //
                                //swapS = did;
                                eventCards = new CardView(context1);

                                layoutparams = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.WRAP_CONTENT,
                                        // LayoutParams.WRAP_CONTENT
                                        //LayoutParams.MATCH_PARENT,
                                        //LayoutParams.MATCH_PARENT
                                        //LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT

                                );

                                layoutparams.setMargins(30, 16, 8, 30);
                                layoutparams.height = 500;
                                layoutparams.width = 1380;

                                eventCards.requestLayout();

                                eventCards.setLayoutParams(layoutparams);

                                eventCards.setCardElevation(20);

                                String event = document.getString("event Name");

                                eventName = new TextView(context1);
                                eventName.setLayoutParams(layoutparams);
                                eventName.setText("Event: " + event);
                                eventName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                                LinearLayout.LayoutParams eventNameLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                eventNameLayout.setMargins(24, 8, 8, 8);
                                eventNameLayout.width = 750;
                                eventNameLayout.height = 175;

                                eventName.setLayoutParams(eventNameLayout);

                                String venue = document.getString("venue");
                                venueName = new TextView(context1);
                                venueName.setText("Venue: " + venue);
                                venueName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                                LinearLayout.LayoutParams venueLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                venueLayout.setMargins(24, 190, 8, 8);
                                venueLayout.width = 750;
                                venueLayout.height = 175;

                                venueName.setLayoutParams(venueLayout);

                                String date = document.getString("startMonth") + "/" + document.getString("startDay");

                                dateText = new TextView(context1);
                                dateText.setText("Date: " + date);
                                dateText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                                LinearLayout.LayoutParams dateLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                dateLayout.setMargins(870, 8, 8, 8);
                                dateLayout.width = 750;
                                dateLayout.height = 175;

                                dateText.setLayoutParams(dateLayout);
                                //***************
                                String datee = document.getString("endMonth")  + "/" +  document.getString("endDay") ;
                                dateTexte = new TextView(context1);
                                dateTexte.setText("EndDate: " + datee);
                                dateTexte.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                                LinearLayout.LayoutParams dateLayoute = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                dateLayoute.setMargins(870, 65  , 8, 8);
                                dateLayoute.width = 750;
                                dateLayoute.height = 175;

                                dateTexte.setLayoutParams(dateLayoute);


                                String time = document.getString("startHr") + ":" + document.getString("startMin");
                                timeText = new TextView(context1);
                                timeText.setText("Time: " + time);
                                timeText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                                LinearLayout.LayoutParams timeLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                timeLayout.setMargins(870, 130, 8, 8);
                                timeLayout.width = 750;
                                timeLayout.height = 175;

                                timeText.setLayoutParams(timeLayout);
                                //***************

                                String timee =  document.getString("endHr") + ":" + document.getString("endMin");
                                timeTexte = new TextView(context1);
                                timeTexte.setText("EndTime: " + timee);
                                timeTexte.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                                LinearLayout.LayoutParams timeLayoute = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                timeLayoute.setMargins(870, 185, 8, 8);
                                timeLayoute.width = 750;
                                timeLayoute.height = 175;

                                timeTexte.setLayoutParams(timeLayoute);

                                String seats = document.getString("seats");
                                seatsText = new TextView(context1);
                                seatsText.setText("Seats: " + seats);
                                seatsText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                                LinearLayout.LayoutParams seatsLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                seatsLayout.setMargins(24, 370, 0, 0);
                                seatsLayout.width = 750;
                                seatsLayout.height = 175;

                                seatsText.setLayoutParams(seatsLayout);

                                viewTicketsBtn = new Button(context1);
                                //viewTicketsBtn.setOnClickListener(this);
                                viewTicketsBtn.setText("View Event!");

                                LinearLayout.LayoutParams btnLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                btnLayout.setMargins(850, 270, 8, 8);
                                btnLayout.width = 500;
                                btnLayout.height = 200;

                                viewTicketsBtn.setLayoutParams(btnLayout);

                                int av = Integer.parseInt(seats);

                                if(av == 0){

                                    viewTicketsBtn.setVisibility(View.INVISIBLE);

                                    TextView soldOut = new TextView(context1);

                                    soldOut = new TextView(context1);
                                    soldOut.setText("SOLD OUT!");
                                    soldOut.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                                    soldOut.setTextColor(Color.parseColor("#FF0000"));

                                    LinearLayout.LayoutParams outLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                                    outLayout.setMargins(880, 350, 8, 8);
                                    outLayout.width = 500;
                                    outLayout.height = 200;

                                    soldOut.setLayoutParams(outLayout);

                                    eventCards.addView(soldOut);

                                }

                                eventCards.addView(eventName);
                                eventCards.addView(venueName);
                                eventCards.addView(dateText);
                                eventCards.addView(dateTexte);
                                eventCards.addView(timeText);
                                eventCards.addView(timeTexte);

                                eventCards.addView(seatsText);
                                eventCards.addView(viewTicketsBtn);

                                linearLayout.addView(eventCards);

                                //todo, maybe declare outside to not use final
                                final String docID = document.getId();
                                viewTicketsBtn.setTag(docID);
                                viewTicketsBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        swapS = did;
                                        Intent intent = new Intent(context1, popUpDynamic.class);
                                        //   Intent intent = new Intent(context1, PurchaseTic.class);

                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // THIS is needed so it does not cause an error
                                        //startActivity(new Intent(context1,popUpDynamic.class));
                                        context1.startActivity(intent);
                                        //Toast.makeText(context1, "Button Works" + docID, Toast.LENGTH_LONG).show();
                                        //todo, the string docID works and is different for each card
                                        // todo can be used here to do the popup and display QR code

                                    }
                                });

                                Log.d(TAG, "Document is not empty!");                  //***********************************************
                            }                                                               //where else ends to check if doc is empty
                        }


                    }
                }
            }
        });
    }

    public void displayFilter(LinearLayout layout, Context context, QueryDocumentSnapshot document){

        context1 = context;
        linearLayout = layout;

        if (document.exists()) {                //***************** THERE IS WHERE IT CHECKS IF THE DOCUMENT IS EMPTY("NULL")

            Map<String, Object> map = document.getData();

            if (map.size() == 0) {
           // if (document.get("startHr") == null) {
                Log.d(TAG, "Document is empty!");

                eventCards = new CardView(context1);

                layoutparams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        // LayoutParams.WRAP_CONTENT
                        //LayoutParams.MATCH_PARENT,
                        //LayoutParams.MATCH_PARENT
                        //LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT

                );

                layoutparams.setMargins(30, 16, 8, 30);
                layoutparams.height = 400;
                layoutparams.width = 1380;

                eventCards.requestLayout();

                eventCards.setLayoutParams(layoutparams);

                eventCards.setCardElevation(20);

                //String event = document.getString("event Name");
                String event = document.getId();

                TextView noEvent;
                noEvent = new TextView(context1);
                noEvent.setLayoutParams(layoutparams);
                noEvent.setText("Event: " + event);
                noEvent.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                LinearLayout.LayoutParams noEventLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                noEventLayout.setMargins(24, 8, 8, 8);
                noEventLayout.width = 1200;
                noEventLayout.height = 100;

                noEvent.setLayoutParams(noEventLayout);

                TextView not;
                not = new TextView(context1);
                not.setLayoutParams(layoutparams);
                not.setText("Event not yet scheduled. Please check back after event concludes!");
                not.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                LinearLayout.LayoutParams notLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                notLayout.setMargins(24, 100, 8, 8);
                notLayout.width = 1200;
                notLayout.height = 100;

                not.setLayoutParams(notLayout);


                eventCards.addView(noEvent);
                eventCards.addView(not);

                linearLayout.addView(eventCards);

                //****************************************************************************
            } else {                                         // PRINTS THE STUFF WHEN ITS NOT EMPTY

                final String did = document.getId();
                //
                //swapS = did;
                eventCards = new CardView(context1);

                layoutparams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        // LayoutParams.WRAP_CONTENT
                        //LayoutParams.MATCH_PARENT,
                        //LayoutParams.MATCH_PARENT
                        //LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT

                );

                layoutparams.setMargins(30, 16, 8, 30);
                layoutparams.height = 500;
                layoutparams.width = 1380;

                eventCards.requestLayout();

                eventCards.setLayoutParams(layoutparams);

                eventCards.setCardElevation(20);

                String event = document.getString("event Name");

                eventName = new TextView(context1);
                eventName.setLayoutParams(layoutparams);
                eventName.setText("Event: " + event);
                eventName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                LinearLayout.LayoutParams eventNameLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                eventNameLayout.setMargins(24, 8, 8, 8);
                eventNameLayout.width = 750;
                eventNameLayout.height = 175;

                eventName.setLayoutParams(eventNameLayout);

                String venue = document.getString("venue");
                venueName = new TextView(context1);
                venueName.setText("Venue: " + venue);
                venueName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                LinearLayout.LayoutParams venueLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                venueLayout.setMargins(24, 190, 8, 8);
                venueLayout.width = 750;
                venueLayout.height = 175;

                venueName.setLayoutParams(venueLayout);

                String date = document.getString("startMonth") + "/" + document.getString("startDay");

                dateText = new TextView(context1);
                dateText.setText("Date: " + date);
                dateText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                LinearLayout.LayoutParams dateLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                dateLayout.setMargins(870, 8, 8, 8);
                dateLayout.width = 750;
                dateLayout.height = 175;

                dateText.setLayoutParams(dateLayout);
                //***************
                String datee = document.getString("endMonth")  + "/" +  document.getString("endDay") ;
                dateTexte = new TextView(context1);
                dateTexte.setText("EndDate: " + datee);
                dateTexte.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                LinearLayout.LayoutParams dateLayoute = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                dateLayoute.setMargins(870, 65  , 8, 8);
                dateLayoute.width = 750;
                dateLayoute.height = 175;

                dateTexte.setLayoutParams(dateLayoute);


                String time = document.getString("startHr") + ":" + document.getString("startMin");
                timeText = new TextView(context1);
                timeText.setText("Time: " + time);
                timeText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                LinearLayout.LayoutParams timeLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                timeLayout.setMargins(870, 130, 8, 8);
                timeLayout.width = 750;
                timeLayout.height = 175;

                timeText.setLayoutParams(timeLayout);
                //***************

                String timee =  document.getString("endHr") + ":" + document.getString("endMin");
                timeTexte = new TextView(context1);
                timeTexte.setText("EndTime: " + timee);
                timeTexte.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);

                LinearLayout.LayoutParams timeLayoute = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                timeLayoute.setMargins(870, 185, 8, 8);
                timeLayoute.width = 750;
                timeLayoute.height = 175;

                timeTexte.setLayoutParams(timeLayoute);

                String seats = document.getString("seats");
                seatsText = new TextView(context1);
                seatsText.setText("Seats: " + seats);
                seatsText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                LinearLayout.LayoutParams seatsLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                seatsLayout.setMargins(24, 370, 0, 0);
                seatsLayout.width = 750;
                seatsLayout.height = 175;

                seatsText.setLayoutParams(seatsLayout);

                viewTicketsBtn = new Button(context1);
                //viewTicketsBtn.setOnClickListener(this);
                viewTicketsBtn.setText("View Event!");

                LinearLayout.LayoutParams btnLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                btnLayout.setMargins(850, 270, 8, 8);
                btnLayout.width = 500;
                btnLayout.height = 200;

                viewTicketsBtn.setLayoutParams(btnLayout);

                int av = Integer.parseInt(seats);

                if(av == 0){

                    viewTicketsBtn.setVisibility(View.INVISIBLE);

                    TextView soldOut = new TextView(context1);

                    soldOut = new TextView(context1);
                    soldOut.setText("SOLD OUT!");
                    soldOut.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                    soldOut.setTextColor(Color.parseColor("#FF0000"));

                    LinearLayout.LayoutParams outLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    outLayout.setMargins(880, 350, 8, 8);
                    outLayout.width = 500;
                    outLayout.height = 200;

                    soldOut.setLayoutParams(outLayout);

                    eventCards.addView(soldOut);

                }

                eventCards.addView(eventName);
                eventCards.addView(venueName);
                eventCards.addView(dateText);
                eventCards.addView(dateTexte);
                eventCards.addView(timeText);
                eventCards.addView(timeTexte);

                eventCards.addView(seatsText);
                eventCards.addView(viewTicketsBtn);

                linearLayout.addView(eventCards);

                //todo, maybe declare outside to not use final
                final String docID = document.getId();
                viewTicketsBtn.setTag(docID);
                viewTicketsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        swapS = did;
                        Intent intent = new Intent(context1, popUpDynamic.class);
                        //   Intent intent = new Intent(context1, PurchaseTic.class);

                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // THIS is needed so it does not cause an error
                        //startActivity(new Intent(context1,popUpDynamic.class));
                        context1.startActivity(intent);
                        //Toast.makeText(context1, "Button Works" + docID, Toast.LENGTH_LONG).show();
                        //todo, the string docID works and is different for each card
                        // todo can be used here to do the popup and display QR code

                    }
                });

                Log.d(TAG, "Document is not empty!");                  //***********************************************
            }                                                               //where else ends to check if doc is empty
        }

    }
}