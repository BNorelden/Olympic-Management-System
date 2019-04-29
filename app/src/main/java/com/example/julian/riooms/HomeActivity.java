package com.example.julian.riooms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.annotation.Nullable;

import Source.Driver;
import Source.Event;
import Source.EventSchedule;
import Source.User;

public class HomeActivity extends AppCompatActivity {

    User user;
    EventSchedule eventSchedule;

    TextView uName, uNum, uAge, uEmail;
    TextView eName, eCategory, eType, eVenue, startDate, endDate, ePrice, eSeats;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = Driver.getUser();
        eventSchedule = EventSchedule.getInstance();

        uName = findViewById(R.id.uName);
        uNum = findViewById(R.id.uNum);
        uAge = findViewById(R.id.uAge);
        uEmail = findViewById(R.id.uEmail);

        eName = findViewById(R.id.eName);
        eCategory = findViewById(R.id.eCategory);
        eType = findViewById(R.id.eType);
        eVenue = findViewById(R.id.eVenue);
        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        ePrice = findViewById(R.id.ePrice);
        eSeats = findViewById(R.id.eSeats);

        uName.setText(user.getName());
        uNum.setText(user.getPhoneNum());
        uAge.setText(user.getAge());
        uEmail.setText(user.getEmail());

        Event e = eventSchedule.getEvents().get(0);
        eName.setText(e.getName());
        eCategory.setText(e.getCategory());
        eType.setText(e.getType());
        eVenue.setText(e.getVenue());
        startDate.setText(e.getStartDateTime());
        endDate.setText(e.getEndDateTime());
        ePrice.setText(e.getPrice());
        eSeats.setText(e.getSeats());

    }

}
