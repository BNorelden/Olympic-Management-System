package com.example.tabfrags;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class tab1Event extends Fragment implements View.OnClickListener {

    public static int c;
    private static final String TAG = "Tab1Event";
    FirebaseFirestore fs;

    Button btnTEST;
    TextView tView, tView3;
    Button b,btn0;

                            //TOMORROW MERGE THE OTHER CODES AND THEN TYY PAGINATION IF NOT GOOD ENOUGH WE TRY STATIC categories
                            //DYNAMIC BUTTONS N TEXT + SCROLLABLE + DON'T REMEMBER REST

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1event,container,false);

        tView = view.findViewById(R.id.tView);
        tView.setOnClickListener(this);
        tView3 = view.findViewById(R.id.tView3);
        b = view.findViewById(R.id.b);
        b.setOnClickListener(this);
        btnTEST = view.findViewById(R.id.btnTEST);

        btn0 = view.findViewById(R.id.btn0);
        btn0.setOnClickListener(this);

        fs = FirebaseFirestore.getInstance();

       // readSingleContact(); THIS IS FOR FIRST TEXT VIEW
        readSingleContact();
        readAll();  //2nd TEXTView

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onClick(View v){
        if(v.getId()==R.id.tView) {

            startActivity(new Intent(getActivity(),POP.class));
        }

        else if(v.getId()==R.id.btn0) {

            startActivity(new Intent(getActivity(),popUpDynamic.class));
        }

        else if (v.getId()==R.id.b){
            // startActivity(new Intent(tab1Event.this.getActivity(), Main2Activity.class));
            startActivity(new Intent(getActivity(), PurchaseTic.class));
        }

        else if (v.getId()==R.id.b2){ // CLICKING ON THIS CRASHES THE APP
            // startActivity(new Intent(tab1Event.this.getActivity(), Main2Activity.class));
            startActivity(new Intent(getActivity(), PurchaseTic.class));
        }


    }



    private void readAll() {            //it should read all the documents and just get all Id's with a counter
// MAYBE TRY MAPPINGGGGGGGGGGGG

        //fs.collection("CSV-Events").document("aquatics").collection("backstroke") //This Works
        fs.collection("CSV-Events").document("archery").collection("archery")
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
                                c = counter;
                               // Event e1 = new Event(document.getString("Venue"),);
                               // Event e1= new Event("testevent", "diving", 'E', "Location", 3, 5, 14, 45, 17, 6, 2, 15, 2008,
                               //         "EDT",122.22f,100);

                                ya2 = ya2 + "C:"+counter+" " + document.getId() + "\n" + document.getString("Venue")+ "\n";      //NOTE: getId() gets the document names, getData() get everything
                                tView3.setText(ya2);

                            }

                            //tView.setText(Integer.toString(c));
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


      private void readSpec() {                          //This works for specified field, in the code below, gets all diving category from events collection

          fs.collection("events")
                  .whereEqualTo("category", "diving")
                  .get()
                  .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                      @Override
                      public void onComplete(@NonNull Task<QuerySnapshot> task) {
                          String ya2 = " ";
                          int counter=0;
                          if (task.isSuccessful()) {
                              for (QueryDocumentSnapshot document : task.getResult()) {
                                  Log.d(TAG, document.getId() + " => " + document.getData());

                                  ya2 = ya2 + ("Event: " + document.getString("name") + "\n");
                                  tView3.setText(ya2);
                              }
                              c = counter;
                              tView.setText(Integer.toString(c));
                          } else {
                              Log.d(TAG, "Error getting documents: ", task.getException());
                          }
                      }
                  });
      }



       /* DocumentReference contact =  fs.collection("events").document("diving");
        contact.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();

                    String ya1 = "Event: "+ doc.getString("name")+"    Type: " + doc.getString("type");
                    //String ya2 = "Email: "+ doc.getString("email");
                    tView3.setText(ya1);

                }
            }
        });
    } */


       //
    private void readSingleContact(){
    //    DocumentReference contact =  fs.collection("CSV-Events").document("archery")
    //            .collection("archery").document("Men's Team");
        DocumentReference contact =  fs.collection("EVENTS").document("Men's Floor Exercise");
        //DocumentReference contact =  fs.collection("events").document("diving");
        contact.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();

                    String ya1 = "Event: "+ doc.getString("event Name")+"\nVenue: " + doc.getString("venue");

                    //String ya2 = "Email: "+ doc.getString("email");


                    tView.setText(ya1);
                    //tView3.setText(ya2);


         /*
                    OR USING STRING BUILDER
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder data = new StringBuilder("");
                    data.append("Name: ").append(doc.getString("name"));
                    data.append("\nEmail: ").append(doc.getString("email"));
                    data.append("\nPhone: ").append(doc.getString("phone"));

                    yup.setText(data.toString());
         */

                }
            }
        });

}

}


