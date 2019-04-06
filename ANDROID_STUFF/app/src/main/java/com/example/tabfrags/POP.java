package com.example.tabfrags;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.GenericTypeIndicator;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import static java.lang.Long.getLong;

public class POP extends Activity implements View.OnClickListener {
    Button b2;
    FirebaseFirestore fs;
    TextView tView1, tView2, tView4, tView5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);

        b2 = findViewById(R.id.b2);
        b2.setOnClickListener(this);

        DisplayMetrics dm =  new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.85), (int)(height*0.85));

        tView1 = findViewById(R.id.tView1);
        tView2 = findViewById(R.id.tView2);
        tView4 = findViewById(R.id.tView4);
        tView5 = findViewById(R.id.tView5);

        fs = FirebaseFirestore.getInstance();

       /* GenericTypeIndicator<List<String>> gti = new GenericTypeIndicator<List<String>>() {};     NEED TO TEST THIS FOR ARRAYS

        String question = dataSnapshot.child("question").getValue(String.class);
        List<String> questionOptions = dataSnapshot.child("options").getValue(gti); */

        readSingleContact();

    }

    @Override
    public void onClick(View v){

        if (v.getId()==R.id.b2){
            //gotta change this to dynamic button and put it in readSingleContact and make another pop activity to be dynamic
            Intent intent = new Intent(POP.this, Main3Activity.class);
            startActivity(intent);
        }

    }

    private void readSingleContact(){
       // DocumentReference contact =  fs.collection("CSV-Events").document("archery")
         //       .collection("archery").document("Men's Team");
        DocumentReference contact =  fs.collection("EVENTS").document("Men's Floor Exercise");
        //DocumentReference contact =  fs.collection("events").document("diving");
        contact.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();
                    final String docID = doc.getId();
                    String ya1 = "Event: "+ doc.getString("event Name");
                    String ya2 = "Venue: " + doc.getString("venue");
                    String ya4 = "Category: " + doc.getString("category");
                    String ya5 = "Price: " + doc.getString("price")+"\t Seats: "+ doc.getString("seats");
                   // String ya2 = "place: "+ doc.getString("place") + "                  Price: $"
                   //         + doc.getLong("price") +"     Seats:"+ doc.getLong("seats");
                    //String ya2 = "place: "+ doc.getString("place") + "      Time: " + doc.getString("time[0]") + ":" + doc.getString("time[1]") + " - "
                    // + doc.getString("time[2]") + ":" + doc.getString("time[3]");

                    //String ya3 = "date: "+ doc.getString("date[0]") + "/" + doc.getString("date[1]") + doc.getString("date[2]");
                    //CODE ABOVE DOES NOT WORK FOR ARRAYS
                    tView1.setTextSize(25);
                    tView1.setText(ya1);

                    tView2.setTextSize(25);
                    tView2.setText(ya2);

                    tView4.setTextSize(25);
                    tView4.setText(ya4);

                    tView5.setTextSize(25);
                    tView5.setText(ya5);

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
