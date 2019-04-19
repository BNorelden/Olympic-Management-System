package com.example.tabfrags;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class tabMainEvent extends Fragment {

    private static final String TAG = "Tab2Event";
    private EditText etext;
    private Button btnTEST;
    RelativeLayout rr;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabmainevent,container,false);



        rr = view.findViewById(R.id.rr);
        //etext = new EditText(getActivity().getApplicationContext());  //Edit text

        //  etext.setPadding(450,550,100,100);
        //etext.setText("0");
        // rr.addView(etext);

        btnTEST = (Button) view.findViewById(R.id.btnTEST);

        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 4",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}


