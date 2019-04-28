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

public class activityTwoEmp extends AppCompatActivity {
    private TextView tt;
    LinearLayout linearLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_emp);

        linearLayout = (LinearLayout) findViewById(R.id.L3);

        Context context = getApplicationContext();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //String currUser = mAuth.getCurrentUser().getUid(); //charley's error
        String currUser = "someone";
        ticketList alist = new ticketList();
        alist.displayList(currUser,linearLayout, context);
        //tt = findViewById(R.id.tv1);


        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //TextView tv=new TextView(this);
        tt = new TextView(this);
        tt.setText("TESTING ACTIVITY 2");
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

                        Intent intent0 = new Intent(activityTwoEmp.this, empView.class);
                        startActivity(intent0);

                        return true;
                    case R.id.navigation_sched:

                        Intent intent = new Intent(activityTwoEmp.this, scheduleEvent.class);
                        startActivity(intent);

                        // here goes the code for filter
                        return true;

                    case R.id.navigation_Filter:

                        Intent intent2 = new Intent(activityTwoEmp.this,activityOneEmp.class);
                        startActivity(intent2);

                        return true;
                    case R.id.navigation_Tickets:

                        return true;
                    case R.id.navigation_notifications:

                        Intent intent4 = new Intent(activityTwoEmp.this,activityThreeEmp.class);
                        startActivity(intent4);

                        return true;
                }
                return false;

            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);


    }

}
