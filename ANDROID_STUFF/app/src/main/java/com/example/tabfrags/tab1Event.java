package com.example.tabfrags;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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


public class tab1Event extends Fragment implements View.OnClickListener {

    private static final String TAG = "Tab1Event";
    FirebaseFirestore fs;

    private Button btnTEST;
    TextView tView, tView3;

    Button b;

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

        fs = FirebaseFirestore.getInstance();

        readSingleContact();

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
        if(v.getId()==R.id.tView) {     // THIS PART FULLY WORKS

            startActivity(new Intent(getActivity(),POP.class));
        }

        else if (v.getId()==R.id.b){ // CLICKING ON THIS CRASHES THE APP
            // startActivity(new Intent(tab1Event.this.getActivity(), Main2Activity.class));
            startActivity(new Intent(getActivity(), Main3Activity.class));
        }


    }

    private void readSingleContact(){

        DocumentReference contact =  fs.collection("events").document("diving");
        contact.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();

                    String ya1 = "Event: "+ doc.getString("name")+"    Type: " + doc.getString("type");
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


