package com.example.charley.riooms;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;


public class displayTic {
    CardView ticketCards;
    Context context1;
    LinearLayout.LayoutParams layoutparams;
    TextView eventName, dateText, timeText, venueName, ticCountText, timeTexte, dateTexte;
    LinearLayout ticketLayout;


   public void dis(Context context, QueryDocumentSnapshot document, LinearLayout ticketLayout1  ) {

       context1 = context;

       ticketLayout = ticketLayout1;

       ticketCards = new CardView(context1);

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
    /*****************************************/

        String date = document.getString("endMonth")  + "/" +  document.getString("endDay") ;
        dateText = new TextView(context1);
        dateText.setText("Date: " + date);
        dateText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

        LinearLayout.LayoutParams dateLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dateLayout.setMargins(870, 8, 8, 8);
        dateLayout.width = 750;
        dateLayout.height = 175;

        dateText.setLayoutParams(dateLayout);

        /*************************************/
        String datee = document.getString("endMonth")  + "/" +  document.getString("endDay") ;
        dateTexte = new TextView(context1);
        dateTexte.setText("EndDate: " + datee);
        dateTexte.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

        LinearLayout.LayoutParams dateLayoute = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dateLayoute.setMargins(870, 65  , 8, 8);
        dateLayoute.width = 750;
        dateLayoute.height = 175;

        dateTexte.setLayoutParams(dateLayoute);
       /*****************************************/

        String time = document.getString("startHr") + ":" + document.getString("startMin");
        timeText = new TextView(context1);
        timeText.setText("Time; " + time);
        timeText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

        LinearLayout.LayoutParams timeLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        timeLayout.setMargins(870, 150, 8, 8);
        timeLayout.width = 750;
        timeLayout.height = 175;

        timeText.setLayoutParams(timeLayout);

        /******************************************/

        String timee =  document.getString("endHr") + ":" + document.getString("endMin");
        timeTexte = new TextView(context1);
        timeTexte.setText("EndTime: " + timee);
        timeTexte.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);

        LinearLayout.LayoutParams timeLayoute = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        timeLayoute.setMargins(870, 225, 8, 8);
        timeLayoute.width = 750;
        timeLayoute.height = 175;

        timeTexte.setLayoutParams(timeLayoute);
        /*****************************************/
        String ticCount = document.getString("Tickets");
        ticCountText = new TextView(context1);
        ticCountText.setText("Tickets Purchased: " + ticCount);
        ticCountText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

        LinearLayout.LayoutParams ticCountLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ticCountLayout.setMargins(24, 370, 0, 0);
        ticCountLayout.width = 750;
        ticCountLayout.height = 175;


        ticCountText.setLayoutParams(ticCountLayout);



        //todo, maybe declare outside to not use final
        final String docID = document.getId();


        ticketCards.addView(eventName);
        ticketCards.addView(venueName);
        ticketCards.addView(dateText);
        ticketCards.addView(dateTexte);
        ticketCards.addView(timeText);
        ticketCards.addView(timeTexte);

        ticketCards.addView(ticCountText);


        ticketLayout.addView(ticketCards);

        //todo button listener and same thing for event, + fix event
        //when button is pressed
        // String qr = document.getString("ticCount"); or document title?
        //show qr code
        //String qr = document.getId();


    } // end of for loop query
}


