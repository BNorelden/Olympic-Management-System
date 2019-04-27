package com.example.charley.riooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class activityOneEmp extends AppCompatActivity {
    private TextView tt;
    LinearLayout linearLayout;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_emp);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_emp);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_regAth:
                        Intent intent0 = new Intent(activityOneEmp.this, empView.class);
                        startActivity(intent0);

                        return true;
                    case R.id.navigation_sched:


                        Intent intent2 = new Intent(activityOneEmp.this,scheduleEvent.class);
                        startActivity(intent2);

                        return true;
                    case R.id.navigation_Filter:

                        return true;
                    case R.id.navigation_Tickets:

                        Intent intent3 = new Intent(activityOneEmp.this,activityTwoEmp.class);
                        startActivity(intent3);

                        return true;
                    case R.id.navigation_notifications:

                        Intent intent4 = new Intent(activityOneEmp.this,activityThreeEmp.class);
                        startActivity(intent4);


                        return true;
                }
                return false;

            }
        });
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
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

// Create the adapter that will return a fragment for each of the three

        // primary sections of the activity.

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
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
                    tab_event tab1 =  new tab_event();
                    return tab1;

                case 1:
                    tab_cerem tab2 = new tab_cerem();
                    return tab2;

                case 2:
                    tab_auto tab3 = new tab_auto();
                    return tab3;

                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }


}
