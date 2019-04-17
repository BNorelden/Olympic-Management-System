package com.example.riooms;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    //Context context = getActivity().getApplicationContext();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    TextView test = new TextView(getApplicationContext()); // not working
                    test.setPadding(100,100,0,0); // duh
                    test.setText("test"); //not working

                    mTextMessage.setPadding(0,0,0,0);
                    mTextMessage.setText(R.string.title_home);
                    // here goes the code for
                    return true;
                case R.id.navigation_Filter:
                    mTextMessage.setPadding(0,0,0,0);

                    mTextMessage.setText(R.string.title_Filter);
                    // here goes the code for filter
                    return true;
                case R.id.navigation_Tickets:
                    mTextMessage.setPadding(250,500,50,50);
                    mTextMessage.setTextSize(20);
                    mTextMessage.setText(R.string.title_noTickets);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setPadding(0,0,0,0);

                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
