package com.example.riooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    private TextView tt;
    private TextView mTextMessage;
    LinearLayout linearLayout;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    /* to read
    db.collection("users")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        }); */

    //Context context = getActivity().getApplicationContext();
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    //mTextMessage.setVisibility(View.VISIBLE);

                    mTextMessage.setPadding(120,150,0,0);
                    mTextMessage.setText(R.string.title_home);
                    // here goes the code for
                    return true;
                case R.id.navigation_Filter:

                    Intent intent = new Intent(MainActivity.this, activityOne.class);
                    startActivity(intent);
                   // mTextMessage.setText(R.string.title_Filter);
                    //mTextMessage.setVisibility(View.INVISIBLE);
                    // here goes the code for filter
                    return true;
                case R.id.navigation_Tickets:

                    Intent intent2 = new Intent(MainActivity.this,activityTwo.class);
                    startActivity(intent2);
                 /*   mTextMessage.setVisibility(View.VISIBLE);
                    mTextMessage.setPadding(250,500,50,50);
                    mTextMessage.setTextSize(20);
                    mTextMessage.setText(R.string.title_noTickets); */
                    return true;
                case R.id.navigation_notifications:

                    Intent intent3 = new Intent(MainActivity.this,activityThree.class);
                    startActivity(intent3);

                    /*mTextMessage.setVisibility(View.VISIBLE);
                    mTextMessage.setPadding(0,0,0,0);

                    mTextMessage.setText(R.string.title_notifications); */
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //LinearLayout Llayout = findViewById(R.id.L1);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        linearLayout = (LinearLayout) findViewById(R.id.L1);

        TextView t3 = new TextView(this);

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //TextView tv=new TextView(this);
        t3.setLayoutParams(lparams);
        //tv.setText("test");
        linearLayout.addView(t3);



        db.collection("EVENTS")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            String did = "";
                            String holder = "";
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                did =  document.getId();
                                holder = holder + did;
                            }

                            mTextMessage.setText(holder);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }

                });
        //navigation.setOnNavigationItemReselectedListener();
    }


}
