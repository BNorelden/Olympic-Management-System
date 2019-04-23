package com.example.charley.riooms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class homePage extends AppCompatActivity implements View.OnClickListener{

    Button signoutBtn;
    //TextView text;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mAuth = FirebaseAuth.getInstance();

        signoutBtn = findViewById(R.id.signout_action);
        //text = findViewById(R.id.textView);
        //text.setText(mAuth.getUid());

        signoutBtn.setOnClickListener(this);

    }

    private void signout(){

        mAuth.signOut();

        FirebaseAuth.getInstance().signOut();

        //startActivity(new Intent(this, MainActivity.class));
        //startActivity(new Intent(getApplicationContext(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        finish();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signout_action:

                signout();

                break;

        }

    }
}
