package Source;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.List;

public class Driver {

    private static User user = null;
    private static Athlete athlete = null;
    private static Employee employee = null;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void initUser(DocumentSnapshot document) throws Exception {

        Gson gson = new Gson();

        JsonElement json = gson.toJsonTree(document.getData());
        user = gson.fromJson(json, User.class);

        EventSchedule eventSchedule = EventSchedule.getInstance();
        querySchedule(eventSchedule);

    }

    private static void querySchedule(final EventSchedule schedule) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("SCHEDULE").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    List<DocumentSnapshot> documents = task.getResult().getDocuments();

                    for(DocumentSnapshot doc : documents) {
                        Event event;

                        Gson gson = new Gson();

                        JsonElement json = gson.toJsonTree(doc.getData());
                        event = gson.fromJson(json, Event.class);

                        try {
                            schedule.addEvent(event);
                        }
                        catch(Exception e) {}
                    }
                }
            }
        });

    }

    public static User getUser() {

        return user;
    }

}
