package com.example.tabfrags;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static android.support.constraint.Constraints.TAG;
import static com.example.tabfrags.tab2Event.swapS;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.getLong;
public class popUpDynamic extends Activity implements View.OnClickListener {

    FirebaseFirestore fs;
    TextView tView;
    Button b2;
    String holds = swapS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_dynamic);

         tView = findViewById(R.id.tView);
         b2 = findViewById(R.id.b2);
         b2.setOnClickListener(this);

        DisplayMetrics dm =  new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.85), (int)(height*0.85));



        fs = FirebaseFirestore.getInstance();


        /*
        fs.collection("EVENTS")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        String ya2 = "";
                        int counter = 0;

                        if (task.isSuccessful()) {

                        }

                    } }); */


        //DocumentReference fsref =  fs.collection("EVENTS").document();
       // CollectionReference fsc = fs.collection("EVENTS");
        DocumentReference fsref =  fs.collection("EVENTS").document(holds); //works

        fsref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();

                    //(String name, String category, char type, String venue, int startDay, int startMonth, int startHr, int startMin,
                    //					  int endDay, int endMonth, int endHr, int endMin, int year, String zone, float price, int seats)

                    String name = doc.getString("event Name");
                    String cat = doc.getString("category");
                    String type = doc.getString("type");
                    char t = type.charAt(0);
                    String venue = doc.getString("venue");
                    String docID = doc.getId();
                    int startDay = parseInt(doc.getString("startDay"));
                    int startMonth = parseInt(doc.getString("startMonth"));
                    int startHr = parseInt(doc.getString("startHr"));
                    int startMin = parseInt(doc.getString("startMin"));
                    int endDay = parseInt(doc.getString("endDay"));
                    int endMonth = parseInt(doc.getString("endMonth"));
                    int endHr = parseInt(doc.getString("endHr"));
                    int endMin = parseInt(doc.getString("endMin"));
                    int year = parseInt(doc.getString("year"));
                    String zone = doc.getString("zone");
                    float price = parseFloat(doc.getString("price"));
                    int seats = parseInt(doc.getString("seats"));

                   // Event test1 = new Event(name, cat, t , venue, startDay, startMonth , startHr, startMin, endDay, endMonth, endHr, endMin, year, zone, price, seats);

                  /*  Event test1 = new Event(doc.getString("event Name"), doc.getString("category"), t , doc.getString("venue"), parseInt(doc.getString("startDay")),
                            parseInt(doc.getString("startMonth")), parseInt(doc.getString("startHr")), parseInt(doc.getString("startMin")), parseInt(doc.getString("endDay")),
                            parseInt(doc.getString("endMonth")), parseInt(doc.getString("endHr")), parseInt(doc.getString("endMin")), parseInt(doc.getString("year")),
                            doc.getString("zone"), parseFloat(doc.getString("price")), parseInt(doc.getString("seats")) );
                            */


                    StringBuilder data = new StringBuilder("");


                    data.append("Event Name: ").append(name);
                    data.append("\nCat: ").append(cat);
                    data.append("\nType: ").append(t);
                    data.append("\nVenue: ").append(venue);
                    data.append("\nStart Date: ").append(startMonth+"/"+startDay+"/"+year);
                    data.append("\nStart Time: ").append(startHr +":"+ startMin +" "+ zone);
                    data.append("\nEnd Date: ").append(endMonth+"/"+endDay+"/"+year);
                    data.append("\nEnd Time: ").append(endHr +":"+ endMin +" "+ zone);
                    data.append("\nPrice: ").append(price);                                               // Works beautifully but haven't tested it dynamically
                    data.append("\nseats: ").append(seats);


                    tView.setText(data.toString());



                }
            }
        });


       // readSingleContact();
        //readAll();
    }





    @Override
    public void onClick(View v){

        if (v.getId()==R.id.b2){

            Intent intent = new Intent(popUpDynamic.this, PurchaseTic.class);
            startActivity(intent);
        }

    }

    private void readSingleContact(){

        DocumentReference fsref =  fs.collection("EVENTS").document("Men's Floor Exercise"); // this was for testing

        fsref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc = task.getResult();

                    //(String name, String category, char type, String venue, int startDay, int startMonth, int startHr, int startMin,
                    //					  int endDay, int endMonth, int endHr, int endMin, int year, String zone, float price, int seats)

                    String name = doc.getString("event Name");
                    String cat = doc.getString("category");
                    String type = doc.getString("type");
                    char t = type.charAt(0);
                    String venue = doc.getString("venue");

                    int startDay = parseInt(doc.getString("startDay"));
                    int startMonth = Integer.valueOf(doc.getString("startMonth"));
                    int startHr = parseInt(doc.getString("startHr"));
                    int startMin = parseInt(doc.getString("startMin"));
                    int endDay = parseInt(doc.getString("endDay"));
                    int endMonth = parseInt(doc.getString("endMonth"));
                    int endHr = parseInt(doc.getString("endHr"));
                    int endMin = parseInt(doc.getString("endMin"));
                    int year = parseInt(doc.getString("year"));
                    String zone = doc.getString("zone");
                    float price = parseFloat(doc.getString("price"));
                    int seats = parseInt(doc.getString("seats"));




                    StringBuilder data = new StringBuilder("");
                    data.append("Event Name: ").append(name);
                    data.append("\nCat: ").append(cat);
                    data.append("\nType: ").append(t);

                    tView.setText(data.toString());
                    String ya1 = "Event: "+ name + "\n" + "Cat: " +  cat + "  Type: " + t + "\n" + "Start Date: " + startMonth + "/" +startDay + "/" + year + "\nStart Time: " +
                            startHr +":" + startMin ;
                    tView.setText(ya1);
                   // Event test1 = new Event( name, cat, t, venue, startDay, startMonth, startHr, startMin,
                   // endDay, endMonth, endHr, endMin, year, zone, price, seats);

                   // tView.setText(startDay + " " + startMonth + " " + startHr + " " + startMin +" "+ price+ " \n"+ zone +"\n"+ seats);



                   /* String ya1 = "Event: "+ doc.getString("event Name");
                    String ya2 = "Venue: " + doc.getString("venue");
                    String ya4 = "Category: " + doc.getString("category");
                    String ya5 = "Price: " + doc.getString("price")+"\t Seats: "+ doc.getString("seats");       */
                    // String ya2 = "place: "+ doc.getString("place") + "                  Price: $"
                    //         + doc.getLong("price") +"     Seats:"+ doc.getLong("seats");
                    //String ya2 = "place: "+ doc.getString("place") + "      Time: " + doc.getString("time[0]") + ":" + doc.getString("time[1]") + " - "
                    // + doc.getString("time[2]") + ":" + doc.getString("time[3]");

                    //String ya3 = "date: "+ doc.getString("date[0]") + "/" + doc.getString("date[1]") + doc.getString("date[2]");
                    //CODE ABOVE DOES NOT WORK FOR ARRAYS



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


    private void readAll() {            //it should read all the documents and just get all Id's with a counter


        fs.collection("EVENTS")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {


                        String ya2 = "";
                        int counter=0;

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                counter++;


                                ya2 = ya2 + " C:"+counter+" " + document.getId() + "\n";

                                tView.setText(ya2);


                                // }


                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}



