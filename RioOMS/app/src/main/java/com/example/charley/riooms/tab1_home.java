package com.example.charley.riooms;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class tab1_home extends Fragment implements View.OnClickListener {

    Button signoutBtn;
    Button openAct;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        //todo add support for signout, and how its done in a fragment

        mAuth = FirebaseAuth.getInstance();

        signoutBtn = (Button) rootView.findViewById(R.id.signout);

        openAct = (Button) rootView.findViewById(R.id.openActivity);

        signoutBtn.setOnClickListener(this);

        openAct.setOnClickListener(this);

        return rootView;
    }

    private void signout(){

        mAuth.signOut();

        FirebaseAuth.getInstance().signOut();

        //startActivity(new Intent(this, MainActivity.class));
        //startActivity(new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        getActivity().finish();

        //testing if button could open up an activity on click
        /*Intent intent = new Intent(getActivity(), employeeView.class);
        startActivity(intent);*/


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signout:

                signout();

                break;

            /*case R.id.openActivity:

                Intent intent = new Intent(getActivity(), employeeView.class);
                startActivity(intent);

                break;*/

        }

    }
}
