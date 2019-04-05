package com.example.tabfrags;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.tabfrags.tab1Event.c;

public class tab2Event extends Fragment {

    private static final String TAG = "Tab2Event";

    private TextView tview;
    private Button btnTEST;
    int o=c; // the public static int from tab1Event works
    int cerCount;
    String holder = "";
    LinearLayout layout;
    Context context;
    FirebaseFirestore fs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2event,container,false);

        fs = FirebaseFirestore.getInstance();
        readAll();
 /*
  *           ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView1);
        // Get the widgets reference from XML layout
        LinearLayout layout = findViewById(R.id.layout1);
        layout.setOrientation(LinearLayout.VERTICAL);

        //scrollView.addView(layout); aint working
        for (int j = 0; j < 12; j++) {
            TextView tview= new TextView(this);
            Button btn= new Button(this);

            tview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            tview.setId(j+100);
            btn.setId(j);

            tview.setText("TextView"+ (j+100));
            btn.setText("Btn "+ j);

            layout.addView(tview);
            tview.setPadding(150, 100, 150, 0);

            //LinearLayout.LayoutParams tviewparam = (LinearLayout.LayoutParams)btn.getLayoutParams();
           // tviewparam.setMargins(100, 150, 0, 0); //substitute parameters for left, top, right, bottom
           // tview.setLayoutParams(tviewparam);

            layout.addView(btn);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)btn.getLayoutParams();
            params.setMargins(850, -100, 200, 50); //substitute parameters for left, top, right, bottom
            btn.setLayoutParams(params);
  *
  *
  * */
        Toast.makeText(getActivity(), cerCount+holder,
                Toast.LENGTH_LONG).show();

        layout = view.findViewById(R.id.Linear2);

        context = getActivity().getApplicationContext();
/*
        for (int j = 0 ; j < cerCount; j++) {

            tview = new TextView(context);
            //+ Button btn= new Button(context);



            //tview.setText("Olympic Stadium");
            tview.setTextColor(Color.argb(100, 28, 240, 134));
            tview.setText(holder);

            tview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

            tview.setId(j+100);

            tview.setPadding(150, 150, 0, 0);
            //tview.requestLayout();
            //tview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            layout.addView(tview);

            //tview.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

           //+btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));

         //tview.setId(j+100);
          //+  btn.setId(j);

           // tview.setText("TextView"+ (j+100));
          // + btn.setText("Button"+ j);

           // layout.addView(tview);

           // tview.setPadding(50, 100, 150, 0);

           // LayoutParams tviewparam = (LayoutParams)tview.getLayoutParams();

           // tviewparam.setMargins(100, 150, 0, 0); //substitute parameters for left, top, right, bottom
          //  tview.setLayoutParams(tviewparam);

           // layout.addView(tview);
           //+ LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)btn.getLayoutParams();
         //+   params.setMargins(850, -100, 150, 50); //substitute parameters for left, top, right, bottom
          //+  btn.setLayoutParams(params);

        }   */


        return view;
    }

    private void readAll() {            //it should read all the documents and just get all Id's with a counter
// MAYBE TRY MAPPINGGGGGGGGGGGG

        //fs.collection("CSV-Events").document("aquatics").collection("backstroke") //This Works
        fs.collection("CERb")
                //fs.collection("events")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        String ya2 = "";
                        int counter=0;

                        if (task.isSuccessful()) {

                            Toast.makeText(getActivity(), "Works!",
                                    Toast.LENGTH_LONG).show();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                counter++;
                                cerCount = counter;

                                ya2 = ya2 + "C:"+counter+" " + document.getId() + "\n";      //NOTE: getId() gets the document names, getData() get everything
                                holder = ya2;

                                context = getActivity().getApplicationContext();

                              //  for (int j = 1 ; j <= cerCount; j++) {

                                    tview = new TextView(context);
                                    //+ Button btn= new Button(context);


                                    //tview.setText("Olympic Stadium");
                                    tview.setTextColor(Color.argb(100, 28, 240, 134));
                                    tview.setText(holder+"\n");

                                    tview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);

                                    tview.setId(cerCount + 100);

                                    tview.setPadding(150, 150, 0, 0);

                                    layout.addView(tview);

                               // }


                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}


