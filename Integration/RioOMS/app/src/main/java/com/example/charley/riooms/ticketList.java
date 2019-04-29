package com.example.charley.riooms;

//test to see if it code eliminate repeated code
//Used to modularize the code, since this process is
//repeated for all users, (users, athletes, employees)
// It works, now have to see how if a user is signed out to ask them to sign in/up with a popup?
//to view ticketlist
import android.content.Context;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

import static android.support.constraint.Constraints.TAG;

public class ticketList {

    //FirebaseAuth mAuth;
    String userId;
    FirebaseFirestore db;
    LinearLayout.LayoutParams layoutparams;
    LinearLayout ticketLayout;
    Context context1;

    /*CardView ticketCards;
    Context context1;
    LinearLayout.LayoutParams layoutparams;
    TextView eventName, dateText, timeText, venueName, seatsText;
    LinearLayout ticketLayout;
    Button viewTicketsBtn;*/

    public void displayList(String currUserId, View layout, final Context context){

        //FirebaseAuth mAuth;
        //FirebaseFirestore db = FirebaseFirestore.getInstance();
        userId="Bilal Norelden";
        //userId = currUserId;
        db = FirebaseFirestore.getInstance();
        context1 = context;

        ticketLayout = (LinearLayout) layout;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // only if ticket test works
        //CollectionReference TicketReference = db.collection(id);
        //todo test for toast message for empty ticket list
        //CollectionReference TicketReference = db.collection("USER").document(userId).collection("Ticket");
        CollectionReference TicketReference = db.collection("ATHLETE").document(userId).collection("Ticket");
      //  CollectionReference TicketReferenceA = db.collection("ATHLETE").document(userId).collection("Ticket");
      //  CollectionReference TicketReferenceE = db.collection("EMPLOYEE").document(userId).collection("Ticket");

        TicketReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){

                    //read each result, for each result create a card
                    for(QueryDocumentSnapshot document : task.getResult()) {


                        displayTic tic = new displayTic();
                        tic.dis(context1, document, ticketLayout);
                    }

/*
                        ticketCards = new CardView(context1);

                        layoutparams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                // LayoutParams.WRAP_CONTENT
                                //LayoutParams.MATCH_PARENT,
                                //LayoutParams.MATCH_PARENT
                                //LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT

                        );

                        layoutparams.setMargins(30,16,8, 30);
                        layoutparams.height = 500;
                        layoutparams.width = 1380;

                        ticketCards.requestLayout();

                        ticketCards.setLayoutParams(layoutparams);

                        ticketCards.setCardElevation(20);

                        String event = document.getString("event Name");
                        DocumentReference ticket = document.getDocumentReference("ticket");
                       // String event = ticket.getString("event Name");


                        eventName = new TextView(context1);
                        eventName.setLayoutParams(layoutparams);
                        eventName.setText("Event: " + event);
                        eventName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                        LinearLayout.LayoutParams eventNameLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        eventNameLayout.setMargins(24,8,8,8);
                        eventNameLayout.width = 750;
                        eventNameLayout.height = 175;

                        eventName.setLayoutParams(eventNameLayout);

                        String venue = document.getString("venueName");
                        venueName = new TextView(context1);
                        venueName.setText("Venue: " + venue);
                        venueName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                        LinearLayout.LayoutParams venueLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        venueLayout.setMargins(24,190,8,8);
                        venueLayout.width = 750;
                        venueLayout.height = 175;

                        venueName.setLayoutParams(venueLayout);

                        String date = document.getString("date");
                        dateText = new TextView(context1);
                        dateText.setText("Date: " + date);
                        dateText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                        LinearLayout.LayoutParams dateLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        dateLayout.setMargins(870,8,8,8);
                        dateLayout.width = 750;
                        dateLayout.height = 175;

                        dateText.setLayoutParams(dateLayout);

                        String time = document.getString("time");
                        timeText = new TextView(context1);
                        timeText.setText("Time; " + time);
                        timeText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

                        LinearLayout.LayoutParams timeLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        timeLayout.setMargins(870,150,8,8);
                        timeLayout.width = 750;
                        timeLayout.height = 175;

                        timeText.setLayoutParams(timeLayout);

                        String seats = document.getString("seats");
                        seatsText = new TextView(context1);
                        seatsText.setText("Seats: " + seats);
                        seatsText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                        LinearLayout.LayoutParams seatsLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        seatsLayout.setMargins(24,370,0,0);
                        seatsLayout.width = 750;
                        seatsLayout.height = 175;

                        seatsText.setLayoutParams(seatsLayout);

                        viewTicketsBtn = new Button(context1);
                        //viewTicketsBtn.setOnClickListener(this);
                        viewTicketsBtn.setText("View Ticket!");

                        LinearLayout.LayoutParams btnLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        btnLayout.setMargins(850,270,8,8);
                        btnLayout.width = 500;
                        btnLayout.height = 200;

                        viewTicketsBtn.setLayoutParams(btnLayout);

                        //todo, maybe declare outside to not use final
                        final String docID = document.getId();
                        viewTicketsBtn.setTag(docID);
                        viewTicketsBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Toast.makeText(context1, "Button Works" + docID, Toast.LENGTH_LONG).show();
                                //todo, the string docID works and is different for each card
                                // todo can be used here to do the popup and display QR code

                            }
                        });

                        ticketCards.addView(eventName);
                        ticketCards.addView(venueName);
                        ticketCards.addView(dateText);
                        ticketCards.addView(timeText);
                        ticketCards.addView(seatsText);
                        ticketCards.addView(viewTicketsBtn);

                        ticketLayout.addView(ticketCards);

                        //todo button listener and same thing for event, + fix event
                        //when button is pressed
                        // String qr = document.getString("seats"); or document title?
                        //show qr code
                        //String qr = document.getId();


                    } // end of for loop query*/

                }

                else {
                    //todo, write to log instead, toast if query is empty
                    Toast.makeText(context1, "No tickets! Please Purchase!", Toast.LENGTH_LONG);

                }

            }
        });




    }

}
