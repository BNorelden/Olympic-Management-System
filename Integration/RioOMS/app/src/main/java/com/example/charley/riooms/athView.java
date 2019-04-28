package com.example.charley.riooms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class athView extends AppCompatActivity {
    private static String TAG = "athView";
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

                    Intent intent = new Intent(athView.this, activityOneAth.class);
                    //Intent intent = new Intent(athView.this, athleteView.class);
                    startActivity(intent);

                    // here goes the code for filter
                    return true;
                case R.id.navigation_Tickets:

                    Intent intent2 = new Intent(athView.this,activityTwoAth.class);
                    startActivity(intent2);

                    return true;
                case R.id.navigation_notifications:

                    Intent intent3 = new Intent(athView.this,activityThreeAth.class);
                    startActivity(intent3);


                    return true;

                case R.id.navigation_autograph:

                    Intent intent4 = new Intent(athView.this,scheduleAuto.class);
                    startActivity(intent4);


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ath_view);

        db = FirebaseFirestore.getInstance();

        Context context = getApplicationContext();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_ath);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        linearLayout = (LinearLayout) findViewById(R.id.L1);

        //eList etest = new eList();
        //etest.displayeList(linearLayout, context);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        String currUser = mAuth.getCurrentUser().getUid();

        DocumentReference user1 = db.collection("ATHLETE").document(currUser);

        CollectionReference myEvents = db.collection("EVENTS");

        //myEvents.whereEqualTo()


    }


}
