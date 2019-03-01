package com.example.charley.riooms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TicketDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_display);

        //mTextView.setText("Welcome to Dynamic TextView");

        //add the qr code based on the even programatically
        //user can have multiple or empty ticket list
        //ticket list in scroll

        /*
        *
        * protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout picLL = new LinearLayout(this);
        picLL.layout(0, 0, 100, 100);
        picLL.setLayoutParams(new LayoutParams(100, 100));
        picLL.setOrientation(LinearLayout.HORIZONTAL);
        ImageView myImage = new ImageView(this);
        myImage.setImageResource(R.drawable.ic_launcher);
        picLL.addView(myImage);
        setContentView(picLL);
    }
        *
        * */
    }
}
