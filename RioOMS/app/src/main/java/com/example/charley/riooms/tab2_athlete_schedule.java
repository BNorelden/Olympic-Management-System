package com.example.charley.riooms;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Layout;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class tab2_athlete_schedule extends Fragment {

    CardView eventCards;
    Context context;
    LayoutParams layoutparams;
    TextView eventName, date, time, venue;
    LinearLayout eventLayout;
    Button buyBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_athlete_schedule, container, false);

        //Scroll View
        //ScrollView eventScroll = (ScrollView) rootView.findViewById(R.id.eventScroll);
        eventLayout = (LinearLayout) rootView.findViewById(R.id.cardLayout);

        /*
        *
        * todo
        * scroll listner for pagination to detect bottom of scroll
        *
        * do pagination and query and fix cardview output
        *
        *
        *
        * */

        context = getActivity().getApplicationContext();

        for(int i = 0; i < 5; i++){

            eventCards = new CardView(context); //, null, R.style.eventCardStyle);


            layoutparams = new LayoutParams(
                    LayoutParams.WRAP_CONTENT,
                   // LayoutParams.WRAP_CONTENT
                    //LayoutParams.MATCH_PARENT,
                    //LayoutParams.MATCH_PARENT
                    //LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT

            );

            layoutparams.setMargins(30,16,8, 30);
            layoutparams.height = 500;
            layoutparams.width = 1380;

            eventCards.requestLayout();

            eventCards.setLayoutParams(layoutparams);

            eventCards.setCardElevation(20);

            eventName = new TextView(context);

            eventName.setLayoutParams(layoutparams);

            eventName.setText("Women's Synchronized 3m Springboard");

            eventName.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

            LayoutParams eventNameLayout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            eventNameLayout.setMargins(24,8,8,8);

            eventNameLayout.width = 750;
            eventNameLayout.height = 175;

            eventName.setLayoutParams(eventNameLayout);

            venue = new TextView(context);

            venue.setText("Olympic Stadium");

            venue.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

            LayoutParams venueLayout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            venueLayout.setMargins(24,370,8,8);

            venueLayout.width = 750;
            venueLayout.height = 175;

            venue.setLayoutParams(venueLayout);

            date = new TextView(context);

            date.setText("03/25/2019");

            date.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

            LayoutParams dateLayout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            dateLayout.setMargins(980,8,8,8);

            dateLayout.width = 750;
            dateLayout.height = 175;

            date.setLayoutParams(dateLayout);

            time = new TextView(context);

            time.setText("10:00 AM");

            time.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

            LayoutParams timeLayout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            timeLayout.setMargins(1000,150,8,8);

            timeLayout.width = 300;
            timeLayout.height = 175;

            time.setLayoutParams(timeLayout);

            buyBtn = new Button(context);

            buyBtn.setText("Buy Ticket");

            LayoutParams buyBtnLayout = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            buyBtnLayout.setMargins(900,270,8,8);

            buyBtnLayout.width = 450;
            buyBtnLayout.height = 200;

            buyBtn.setLayoutParams(buyBtnLayout);

            eventCards.addView(eventName);
            eventCards.addView(venue);
            eventCards.addView(date);
            eventCards.addView(time);
            eventCards.addView(buyBtn);

            //eventCards.setLayoutParams(layoutparams);

            eventLayout.addView(eventCards);

        }


        return rootView;
    }

}
