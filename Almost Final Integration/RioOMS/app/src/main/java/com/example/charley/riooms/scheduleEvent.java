package com.example.charley.riooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class scheduleEvent extends AppCompatActivity {
    private static final String TAG = "scheduleEvent";

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText name, cat, type, venue, stday, stmonth
            , sthour, stmin, endday, endmonth, endhour, endmin, year, zone, price, seats;
    Button Submit;


    String nameS, catS,typeS,venueS,stdayS,stmonthS,sthourS,stminS,enddayS,endmonthS,endhourS,endminS,
    yearS,zoneS,priceS,seatsS;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_event);

        /*final CollectionReference fsref =  db.collection("EVENTS");

        name = findViewById(R.id.name);
        cat = findViewById(R.id.cat);
        type = findViewById(R.id.type);
        venue = findViewById(R.id.venue);
        stday = findViewById(R.id.stday);
        stmonth = findViewById(R.id.stmonth);
        sthour = findViewById(R.id.sthour);
        stmin = findViewById(R.id.stmin);
        endday = findViewById(R.id.endday);
        endmonth = findViewById(R.id.endmonth);
        endhour = findViewById(R.id.endhour);
        endmin = findViewById(R.id.endmin);
        year = findViewById(R.id.year);
        zone = findViewById(R.id.zone);
        price = findViewById(R.id.price);
        seats = findViewById(R.id.seats);




        Submit = findViewById(R.id.Submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 nameS = name.getText().toString();
                 catS = cat.getText().toString();
                 typeS = type.getText().toString();
                 venueS = venue.getText().toString();
                 stdayS = stday.getText().toString();
                 stmonthS = stmonth.getText().toString();
                 sthourS = sthour.getText().toString();
                 stminS = stmin.getText().toString();
                 enddayS = endday.getText().toString();
                 endmonthS = endmonth.getText().toString();
                 endhourS = endhour.getText().toString();
                 endminS = endmin.getText().toString();
                 yearS = year.getText().toString();
                 zoneS = zone.getText().toString();
                 priceS = price.getText().toString();
                 seatsS = seats.getText().toString();




                Map<String, Object> event = new HashMap<>();

                event.put("event Name", nameS);
                event.put("category", catS);
                event.put("type", typeS);
                event.put("venue", venueS);
                event.put("startDay", stdayS);
                event.put("startMonth", stmonthS);
                event.put("startHr", sthourS);
                event.put("startMin", stminS);
                event.put("endDay", enddayS);
                event.put("endMonth", endmonthS);
                event.put("endHr", endhourS);
                event.put("endMin", endminS);
                event.put("year", yearS);
                event.put("zone", zoneS);
                event.put("price", priceS);
                event.put("seats", seatsS);

                String test = "";

                //fsref.document(nameS).set(event,SetOptions.merge());

                *//***************************docSNAPSHOT
                 fsref.whereEqualTo("startHr",sthourS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                for(DocumentSnapshot doc : task.getResult()){
                if(doc.getData().get("startHr") == null){
                // Toast.makeText(scheduleAuto.this, "Time Confilt Error!", Toast.LENGTH_SHORT).show();
                Toast.makeText(scheduleAuto.this, "NO CONFLICT!!!!!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                Log.d("motorcycle", "if works");
                }
                else {
                Toast.makeText(scheduleAuto.this, "headache", Toast.LENGTH_SHORT).show();
                }
                }
                }
                }
                });*//*


                fsref.whereLessThan("startHr",sthourS).whereGreaterThan("startHr",sthourS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if(task.isSuccessful()){
                            for(DocumentSnapshot doc : task.getResult()){
                                if(doc.exists()){
                                    Toast.makeText(scheduleEvent.this, "Time Confilt Error!", Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(scheduleAuto.this, "NO CONFLICT!!!!!!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                                    Log.d("motorcycle", "if works");
                                }
                                else {
                                    Toast.makeText(scheduleEvent.this, "headache", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                    }
                });

            }

        });*/

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_emp);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_regAth:
                        Intent intent0 = new Intent(scheduleEvent.this, empView.class);
                        startActivity(intent0);

                        return true;
                    case R.id.navigation_sched:

                        return true;
                    case R.id.navigation_Filter:

                        Intent intent2 = new Intent(scheduleEvent.this,activityOneEmp.class);
                        startActivity(intent2);

                        return true;
                    case R.id.navigation_Tickets:

                        Intent intent3 = new Intent(scheduleEvent.this,activityTwoEmp.class);
                        startActivity(intent3);

                        return true;
                    case R.id.navigation_notifications:

                        Intent intent4 = new Intent(scheduleEvent.this,activityThreeEmp.class);
                        startActivity(intent4);


                        return true;
                }
                return false;

            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){

                case 0:
                    tabSched tab1 =  new tabSched();
                    return tab1;

                case 1:
                    tabUpdate tab2 = new tabUpdate();
                    return tab2;

                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
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

            FirebaseMessaging.getInstance().unsubscribeFromTopic("security");

            FirebaseAuth.getInstance().signOut();

            Intent intents = new Intent(scheduleEvent.this, MainActivity.class);
            intents.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_SINGLE_TOP);//IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intents);

            finish();

        }

        return super.onOptionsItemSelected(item);

    }

}
