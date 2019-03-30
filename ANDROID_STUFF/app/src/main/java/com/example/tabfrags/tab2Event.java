package com.example.tabfrags;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

public class tab2Event extends Fragment {

    private static final String TAG = "Tab2Event";


    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2event,container,false);


        LinearLayout layout = getActivity().findViewById(R.id.Linear2);
        //TextView tview= new TextView(getActivity().getApplicationContext());
/*
                            THIS IS THE PROGRAMMATIC CODE BUT IT ISN'T WORKING IN TABS YET
        for (int j = 0; j < 4; j++) {
            //TextView tview= new TextView(getActivity());
            TextView tview= new TextView(getContext()); //this is probably whats wrong
            Button btn= new Button(getContext());       //this is probably whats wrong

            tview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            tview.setId(j+100);
            btn.setId(j);

            tview.setText("TextView"+ (j+100));
            btn.setText("Button"+ j);

            layout.addView(tview);
            tview.setPadding(50, 100, 150, 0);
            //LinearLayout.LayoutParams tviewparam = (LinearLayout.LayoutParams)btn.getLayoutParams();
            // tviewparam.setMargins(100, 150, 0, 0); //substitute parameters for left, top, right, bottom
            // tview.setLayoutParams(tviewparam);

            layout.addView(btn);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)btn.getLayoutParams();
            params.setMargins(850, -100, 150, 50); //substitute parameters for left, top, right, bottom
            btn.setLayoutParams(params);

        }  */


        return view;
    }
}


