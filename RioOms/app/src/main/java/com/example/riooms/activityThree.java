package com.example.riooms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;

public class activityThree extends AppCompatActivity {
    private TextView tt;
    LinearLayout linearLayout;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        linearLayout = (LinearLayout) findViewById(R.id.L4);

        /*
        context = getApplicationContext();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //String currUser = mAuth.getCurrentUser().getUid(); //charley's error
        String currUser = "someone";
        ticketList alist = new ticketList();
        alist.displayList(currUser,linearLayout, context);

        */






        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent0 = new Intent(activityThree.this, userView.class);
                        startActivity(intent0);

                        return true;
                    case R.id.navigation_Filter:
                        Intent intent1 = new Intent(activityThree.this,activityOne.class);
                        startActivity(intent1);


                        return true;
                    case R.id.navigation_Tickets:
                        Intent intent2 = new Intent(activityThree.this,activityTwo.class);
                        startActivity(intent2);

                        return true;
                    case R.id.navigation_notifications:

                        return true;
                }
                return false;

            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

/*
         BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent0 = new Intent(activityOne.this,userView.class);
                        startActivity(intent0);

                        return true;
                    case R.id.navigation_Filter:
                        // mTextMessage.setPadding(0,0,0,0);
                        // mTextMessage.setVisibility(View.INVISIBLE);       // alone sets it always to invisible
                        //setContentView(R.layout.tab3event); switch to frag tab
                        //setContentView(R.id.Linear3);     //figure this out
                        //inActivity.this, activityOne.class);
                        // startActivity(intent);
                        // mTextMessage.setText(R.string.title_Filter);
                        //mTextMessage.setVisibility(View.INVISIBLE);
                        // here goes the code for filter
                        return true;
                    case R.id.navigation_Tickets:

                        return true;
                    case R.id.navigation_notifications:

                        return true;
                }
                return false;
            }
        }; */



    }




}
