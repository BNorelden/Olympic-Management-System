package com.example.tabfrags;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static com.example.tabfrags.tab2Event.swapS;
import static java.lang.Integer.parseInt;

public class PurchaseTic extends AppCompatActivity {

    private static final String TAG = "PurchaseTic";

    FirebaseFirestore fs;
    String holds2 = swapS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



        fs = FirebaseFirestore.getInstance();

        final DocumentReference fsref = fs.collection("EVENTS").document(holds2); //works





        fsref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            Map<String, Object> seatsdec = new HashMap<>();

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {

                    DocumentSnapshot doc = task.getResult();


                    int seatsint = parseInt(doc.getString("seats"));
                    seatsint = seatsint-1;
                    String seatsf = Integer.toString(seatsint);
                    seatsdec.put("seats", seatsf);


                    Toast.makeText(getApplicationContext(), "Tickets left "+ seatsf+" !!!" ,
                            Toast.LENGTH_LONG).show();

                   //fsref.set(seatsdec.toString(), SetOptions.merge());
                    fsref.set(seatsdec, SetOptions.merge());
                }

            }
        });

    }


}
