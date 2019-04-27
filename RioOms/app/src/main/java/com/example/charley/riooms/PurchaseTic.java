package com.example.charley.riooms;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;


import static java.lang.Integer.parseInt;

import static com.example.charley.riooms.eList.swapS;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

public class PurchaseTic extends AppCompatActivity {

    private static final String TAG = "PurchaseTic";

    FirebaseFirestore fs;
    String holds2 = swapS;
    private Button btn;
    private EditText etext;
    LinearLayout LL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        LL = findViewById(R.id.LL);



        fs = FirebaseFirestore.getInstance();
        btn= new Button(this);

        final DocumentReference fsref = fs.collection("EVENTS").document(holds2); //works



        btn.setText("purchase");

        LL.addView(btn);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)btn.getLayoutParams();
        params.setMargins(850, 200, 200, 0);
        btn.setLayoutParams(params);

        etext = new EditText(this);

        etext.setPadding(250,600,200,50);




        LL.addView(etext);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fsref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                    Map<String, Object> seatsdec = new HashMap<>();

                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        String ticcounts = etext.getText().toString(); //input to string
                        int ticcount = parseInt(ticcounts);             // input string to int
                        if (task.isSuccessful()) {

                            DocumentSnapshot doc = task.getResult();
                            // make a dynamic button to confirm and edit text for amount


                            int seatsint = parseInt(doc.getString("seats"));
                            if (ticcount > seatsint){

                                Toast.makeText(getApplicationContext(), "Cannot do that, not enough tickets" ,
                                        Toast.LENGTH_LONG).show();
                                etext.getText().clear();
                            }
                            else if (seatsint <=0){
                                btn.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplicationContext(), "TICKETS ARE SOLD OUT" ,
                                        Toast.LENGTH_LONG).show();

                            }

                            else {
                                seatsint = seatsint - ticcount;
                                String seatsf = Integer.toString(seatsint);
                                seatsdec.put("seats", seatsf);

                                 Toast.makeText(getApplicationContext(), "Your purchased "+ ticcount + " and there is "+ seatsf +"Tickets left  !!!" ,
                                       Toast.LENGTH_LONG).show();


                                fsref.set(seatsdec, SetOptions.merge());
                            } }

                    }
                });



            }
        });


    }


}
