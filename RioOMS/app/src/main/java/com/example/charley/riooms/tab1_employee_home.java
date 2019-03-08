package com.example.charley.riooms;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class tab1_employee_home extends Fragment {

    Button signoutBtn;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_employee_home, container, false);

        //todo add support for signout, and how its done in a fragment

        mAuth = FirebaseAuth.getInstance();

        signoutBtn = (Button) rootView.findViewById(R.id.signout);

        return rootView;
    }



}
