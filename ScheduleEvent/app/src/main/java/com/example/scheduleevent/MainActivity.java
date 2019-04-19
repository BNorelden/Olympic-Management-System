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

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText nameField;
    EditText cateField;
    Button Submit;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.nameField);
        Submit = findViewById(R.id.Submit);
        Submit.setOnClickListener(this);
        String EventName = nameField.getText().toString().trim();
        Map<String, Object> event = new HashMap<>();
        //take event name into a separate string in addition to put
        //event.put(every text field available one at a time)

        event.put("first", EventName);

        db.collection("Test2").document(EventName)
                .set(event);
// Add a new document with a generated ID
        //db.collection("users")
         //       .add(user)
         //       .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
         //           @Override
         //           public void onSuccess(DocumentReference documentReference) {
         //               Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
         //           }
         //       })
         //       .addOnFailureListener(new OnFailureListener() {
         //           @Override
         //         public void onFailure(@NonNull Exception e) {
         //               Log.w(TAG, "Error adding document", e);
         //           }
         //       });
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
