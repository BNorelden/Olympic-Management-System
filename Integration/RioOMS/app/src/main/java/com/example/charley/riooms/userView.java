package com.example.charley.riooms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class userView extends AppCompatActivity {
    private static String TAG = "userView";
    private TextView eventName, dateText, timeText, venueName, seatsText;
    private CardView eventCards;
    LayoutParams layoutparams;
    Button viewTicketsBtn;



    LinearLayout linearLayout;
    FirebaseFirestore db;
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

                   // mTextMessage.setPadding(120,0,0,0);
                   // mTextMessage.setText(R.string.title_home);
                    // here goes the code for
                    return true;
                case R.id.navigation_Filter:

                    Intent intent = new Intent(userView.this, activityOne.class);
                    startActivity(intent);

                    // here goes the code for filter
                    return true;
                case R.id.navigation_Tickets:

                    Intent intent2 = new Intent(userView.this,activityTwo.class);
                    startActivity(intent2);

                    return true;
                case R.id.navigation_notifications:

                    Intent intent3 = new Intent(userView.this,activityThree.class);
                    startActivity(intent3);


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        db = FirebaseFirestore.getInstance();

        Context context = getApplicationContext();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        linearLayout = (LinearLayout) findViewById(R.id.L1);

        eList etest = new eList();
        etest.displayeList(linearLayout, context);

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
            finish();

        }


        return super.onOptionsItemSelected(item);
    }
}
