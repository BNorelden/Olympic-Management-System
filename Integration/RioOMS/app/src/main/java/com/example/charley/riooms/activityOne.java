package com.example.charley.riooms;

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

public class activityOne extends AppCompatActivity {
    private TextView tt;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        linearLayout = (LinearLayout) findViewById(R.id.L2);
        //tt = findViewById(R.id.tv1);


        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //TextView tv=new TextView(this);
        tt = new TextView(this);
        tt.setText("SEARCH ACTIVITY");
        tt.setLayoutParams(lparams);
        //tv.setText("test");
        linearLayout.addView(tt);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent0 = new Intent(activityOne.this, userView.class);
                        startActivity(intent0);

                        return true;
                    case R.id.navigation_Filter:

                        return true;
                    case R.id.navigation_Tickets:
                        Intent intent2 = new Intent(activityOne.this,activityTwo.class);
                        startActivity(intent2);
                        return true;
                    case R.id.navigation_notifications:
                        Intent intent3 = new Intent(activityOne.this,activityThree.class);
                        startActivity(intent3);

                        return true;
                }
                return false;

            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
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
