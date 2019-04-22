package com.example.scheduleevent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText nameField, cateField, typeField, venueField;
    EditText dayField, monthField, hourField, minField, yearField;
    EditText endDayField, endMonthField, endHourField, endMinField;
    EditText zoneField, priceField, seatField;

    Button Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.nameField);
        cateField = findViewById(R.id.cateField);
        typeField = findViewById(R.id.typeField);
        venueField = findViewById(R.id.venueField);

        dayField = findViewById(R.id.dayField);
        monthField = findViewById(R.id.monthField);
        hourField = findViewById(R.id.hourField);
        minField = findViewById(R.id.minField);
        yearField = findViewById(R.id.yearField);

        zoneField = findViewById(R.id.zoneField);
        priceField = findViewById(R.id.priceField);
        seatField = findViewById(R.id.seatField);

        endDayField = findViewById(R.id.endDayField);
        endMonthField =findViewById(R.id.endMonthField);
        endHourField = findViewById(R.id.endHourField);
        endMinField = findViewById(R.id.endMinField);

        Submit = findViewById(R.id.Submit);
        Submit.setOnClickListener(this);

    }
    public void submitEvent()
    {
        String EventName = nameField.getText().toString().trim();
        String category = cateField.getText().toString().trim();
        String type = typeField.getText().toString().trim();

        String day = dayField.getText().toString().trim();
        String month = monthField.getText().toString().trim();
        String hour = hourField.getText().toString().trim();
        String min = minField.getText().toString().trim();
        String year = yearField.getText().toString().trim();

        String zone = zoneField.getText().toString().trim();
        String price = priceField.getText().toString().trim();
        String seat = seatField.getText().toString().trim();

        String endDay = endDayField.getText().toString().trim();
        String endMonth = endMonthField.getText().toString().trim();
        String endHour = endHourField.getText().toString().trim();
        String endMin = endMinField.getText().toString().trim();

        Map<String, Object> event = new HashMap<>();
        //take event name into a separate string in addition to put
        //event.put(every text field available one at a time)

        event.put("Name", EventName);
        event.put("Category", category);
        event.put("Type", type);

        event.put("Day", day);
        event.put("Month", month);
        event.put("Hour", hour);
        event.put("Minute", min);
        event.put("Year", year);

        event.put("Zone", zone);
        event.put("Price", price);
        event.put("Seat", seat);

        event.put("End Day", endDay);
        event.put("End Month", endMonth);
        event.put("End Hour", endHour);
        event.put("End Minute", endMin);

        //Can change "Test2" to desired collection
        db.collection("Test2").document(EventName)
                .set(event);
        //TODO add onSuccessListener to confirm success and return employee to previous Activity
        //TODO add onFailListener to stop submission and re-enter information
    }
    @Override
    public void onClick(View v)
    {
            switch(v.getId())
            {
                case R.id.Submit:
                    submitEvent();
                    break;
            }
    }
}
