package com.example.charley.riooms;

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

public class activityThreeEmp extends AppCompatActivity {
    private TextView tt;
    LinearLayout linearLayout;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_emp);

        linearLayout = (LinearLayout) findViewById(R.id.L4);

        /*
        context = getApplicationContext();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //String currUser = mAuth.getCurrentUser().getUid(); //charley's error
        String currUser = "someone";
        ticketList alist = new ticketList();
        alist.displayList(currUser,linearLayout, context);

        */

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //TextView tv=new TextView(this);
        tt = new TextView(this);
        tt.setText("TESTING ACTIVITY 3");
        tt.setLayoutParams(lparams);
        //tv.setText("test");
        linearLayout.addView(tt);




        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_emp);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_regAth:

                        //mTextMessage.setVisibility(View.VISIBLE);

                        // mTextMessage.setPadding(120,0,0,0);
                        // mTextMessage.setText(R.string.title_home);
                        // here goes the code for

                        Intent intent0 = new Intent(activityThreeEmp.this, empView.class);
                        startActivity(intent0);

                        return true;
                    case R.id.navigation_sched:

                        Intent intent = new Intent(activityThreeEmp.this, scheduleEvent.class);
                        startActivity(intent);

                        // here goes the code for filter
                        return true;
                    case R.id.navigation_Filter:

                        Intent intent2 = new Intent(activityThreeEmp.this,activityOneEmp.class);
                        startActivity(intent2);

                        return true;
                    case R.id.navigation_Tickets:

                        Intent intent4 = new Intent(activityThreeEmp.this,activityTwoEmp.class);
                        startActivity(intent4);

                        return true;
                    case R.id.navigation_notifications:


                        return true;
                }
                return false;

            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(4);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sign_out, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            // do something here

            FirebaseAuth.getInstance().signOut();

            Intent intents = new Intent(activityThreeEmp.this, MainActivity.class);
            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);//IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intents);

            finish();

        }

        return super.onOptionsItemSelected(item);

    }


}
