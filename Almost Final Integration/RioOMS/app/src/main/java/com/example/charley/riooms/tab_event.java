package com.example.charley.riooms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class tab_event extends Fragment {

    private FirebaseAuth mAuth;
    LinearLayout eLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event, container, false);

        //todo add support for signout, and how its done in a fragment

        mAuth = FirebaseAuth.getInstance();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        eLayout = rootView.findViewById(R.id.events);


        CollectionReference compEvents = db.collection("EVENTS");

        compEvents.whereEqualTo("type", "E").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){

                    for(QueryDocumentSnapshot document : task.getResult()){

                        eList events = new eList();
                        events.displayFilter(eLayout, getContext(), document);

                    }

                }

                else{

                    Toast.makeText(getContext(), "No tickets! Please Purchase!", Toast.LENGTH_LONG);

                }
            }
        });


        return rootView;
    }



}
