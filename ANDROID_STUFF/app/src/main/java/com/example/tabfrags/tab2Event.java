package com.example.tabfrags;
import android.content.Context;
import android.content.Intent;
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
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import static com.example.tabfrags.tab1Event.c;


public class tab2Event extends Fragment { //implements View.OnClickListener

    private static final String TAG = "Tab2Event";

    static String swapS; //hold the document name so i can use it in other activities


    private TextView tview;
    private Button btnTEST,btn;

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


        layout = view.findViewById(R.id.Linear2);

        context = getActivity().getApplicationContext();



        return view;
    }

    private void readAll() {            //it should read all the documents and just get all Id's with a counter



        fs.collection("EVENTS")
                //        fs.collection("CERb")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        final Map<String, Object> data1 = new HashMap<>();  //*********************************** MAP
                        String ya2 = "";
                        int counter=0;

                        if (task.isSuccessful()) {

                            Toast.makeText(getActivity(), "Works!",
                                    Toast.LENGTH_LONG).show();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                final String did = document.getId();            //  DOCID************************************

                                counter++;
                                cerCount = counter;


                                data1.put("Event " +cerCount, did); //************************************************MAP

                                //ya2 = ya2 + "C:"+counter+" " + document.getId() + "\n";      //NOTE: getId() gets the document names, getData() get everything
                                ya2 =  "C:"+counter+" " + document.getId() + "\n";
                                holder = ya2;

                                context = getActivity().getApplicationContext();

                                    tview = new TextView(context);
                                    tview.setId(cerCount+100); //first id should be 101 for textviews


                                    btn= new Button(context);
                                    btn.setId(cerCount+1000);  // first id should be 1001 for buttons





                                /***************************************************Dynamic TextView********************************************/

                                    tview.setTextColor(Color.argb(100, 28, 240, 134));
                                    tview.setText(holder+"\n");

                                    tview.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);


                                    tview.setPadding(150, 150, 0, 0);

                                    layout.addView(tview);

                                /***************************************************Dynamic Button********************************************/

                                    btn.setText("purchase "+ cerCount);
                                                      // btn.setWidth(50);
                                                       // btn.setHeight(50);

                                                       // LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)btn.getLayoutParams();
                                                       // params.setMargins(850, -100, 150, 50); //substitute parameters for left, top, right, bottom
                                                       // btn.setPadding(150, 50, 150, 50);
                                                       // btn.
                                    layout.addView(btn);
                                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)btn.getLayoutParams();
                                    params.setMargins(850, -200, 200, 50); //substitute parameters for left, top, right, bottom
                                    btn.setLayoutParams(params);
                                                       // btn.setLayoutParams (new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                                                        //btn.setPadding(650, -100, 0, 0);

                                                       // btn.setLayoutParams(params);
                                                       // layout.addView(btn);

                                                   // }
                                tview.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Toast.makeText(getActivity(), "We got this working with doc id \n" + did/* data1.get("Event 1")*/,Toast.LENGTH_SHORT).show();
                                        swapS = did;
                                        startActivity(new Intent(getActivity(),popUpDynamic.class));
                                    }
                                });

                                btn.setOnClickListener(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        swapS = did;

                                        Intent intent = new Intent(getActivity(), PurchaseTic.class);
                                        startActivity(intent);

                                    }
                                });

                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


}

