package com.example.charley.riooms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class activityThree extends AppCompatActivity {
    private TextView tt;
    LinearLayout linearLayout;
    Context context;

    CardView notCards;
    LinearLayout.LayoutParams layoutparams;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        linearLayout = (LinearLayout) findViewById(R.id.scroller);

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

        FirebaseFirestore db = FirebaseFirestore.getInstance();


        //TODO DO THIS//////////////
        CollectionReference notRef = db.collection("NOTIFICATIONS");


        notRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){

                    for(QueryDocumentSnapshot docs : task.getResult()){

                        context = getApplicationContext();

                        notCards = new CardView(context);

                        layoutparams = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                // LayoutParams.WRAP_CONTENT
                                //LayoutParams.MATCH_PARENT,
                                //LayoutParams.MATCH_PARENT
                                //LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT

                        );

                        layoutparams.setMargins(30, 16, 8, 30);
                        layoutparams.height = 350;
                        layoutparams.width = 1380;

                        notCards.requestLayout();
                        notCards.setLayoutParams(layoutparams);
                        notCards.setCardElevation(20);

                        String docName = docs.getId();
                        String message = docs.get("message").toString();

                        TextView docId = new TextView(context);
                        TextView docMes = new TextView(context);

                        docId.setLayoutParams(layoutparams);
                        docId.setText(docName);
                        docId.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                        LinearLayout.LayoutParams docLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        docLayout.setMargins(24, 8, 8, 8);
                        docLayout.width = 750;
                        docLayout.height = 175;

                        docId.setLayoutParams(docLayout);

                        docMes.setLayoutParams(layoutparams);
                        docMes.setText(message);
                        docMes.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                        LinearLayout.LayoutParams docMesLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        docMesLayout.setMargins(24, 200, 8, 8);
                        docMesLayout.width = 750;
                        docMesLayout.height = 175;

                        docMes.setLayoutParams(docMesLayout);

                        notCards.addView(docId);
                        notCards.addView(docMes);

                        linearLayout.addView(notCards);

                    }


                }

            }
        });

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

            Intent intents = new Intent(activityThree.this, MainActivity.class);
            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);//IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intents);

            finish();

        }


        return super.onOptionsItemSelected(item);
    }

}
