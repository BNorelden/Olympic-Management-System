package Source;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import com.example.julian.riooms.MainActivity;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Driver {

    private static User user = null;
    private static Athlete athlete = null;
    private static Employee employee = null;
    private static EventSchedule eventSchedule;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void initUser(DocumentSnapshot document) throws Exception {

        String name = (String) document.get("name");
        String phoneNum = (String) document.get("phoneNum");
        String age = (String) document.get("age");
        String email = (String) document.get("email");

        user = new User(name, phoneNum, age, email);

        eventSchedule = EventSchedule.getInstance();

        //todo implement the add event in Employee
        //upon doing the above, the following code will become obsolete
        String zone = "EST";
        Event e1 = new Event("Men's 200m Backstroke", "Diving", "E", "Olympic Aquatics Stadium",
                "10", "6", "7", "0", "10", "6", "9", "15", "2016", zone,
                "99.99", "1");
        ArrayList<String> sport1 = new ArrayList<String>();
        sport1.add("Diving");
        sport1.add("Gymnastics");
        ArrayList<String> sport2 = new ArrayList<String>();
        sport2.add("Diving");
        sport2.add("Track");
        e1.addParticipant(new Athlete("Julian Sangillo", "2017022254", "21", "juliansangillo@gmail.com",
                "United States", "M", sport1));
        e1.addParticipant(new Athlete("Usain Bolt", "8005672254", "32", "usainbolt@gmail.com",
                "United States", "M", sport2));
        e1.addParticipant(new Athlete("Novak Djokovic", "3457223345", "30", "novakdjokovic@gmail.com",
                "Russia", "M", sport2));
        e1.addParticipant(new Athlete("Rafael Nadal", "6789433289", "25", "rafaelnadal@gmail.com",
                "Italy", "M", sport1));
        e1.addParticipant(new Athlete("Michael Phelps", "5514336723", "26", "michaelphelps@gmail.com",
                "United States", "M", sport1));
        e1.addParticipant(new Athlete("Bilal Norelden", "2018876519", "23", "bilalnorelden@gmail.com",
                "United States", "M", sport2));
        e1.addParticipant(new Athlete("Simone Biles", "3448980021", "40", "simonebiles@gmail.com",
                "Britain", "M", sport1));
        e1.addParticipant(new Athlete("John Isner", "4419998888", "38", "johnisner@gmail.com",
                "France", "M", sport2));
        Event e2 = new Event("Men's 200m Backstroke Ceremony", "", "C", "Olympic Aquatics Stadium",
                "12", "6", "12", "0", "12", "6", "13", "0", "2016", zone,
                "0", "1");
        Event e3 = new Event("Men's Floor Exercise", "Gymnastics", "E", "Rio Olympic Arena",
                "20", "6", "10", "15", "20", "6", "13", "30", "2016", zone,
                "89.99", "1");
        e3.addParticipant(new Athlete("Julian Sangillo", "2017022254", "21", "juliansangillo@gmail.com",
                "United States", "M", sport1));
        e3.addParticipant(new Athlete("Rafael Nadal", "6789433289", "25", "rafaelnadal@gmail.com",
                "Italy", "M", sport1));
        e3.addParticipant(new Athlete("Michael Phelps", "5514336723", "26", "michaelphelps@gmail.com",
                "United States", "M", sport1));
        e3.addParticipant(new Athlete("Simone Biles", "3448980021", "40", "simonebiles@gmail.com",
                "Britain", "M", sport1));
        Event e4 = new Event("Men's Floor Exercise Ceremony", "", "C", "Rio Olympic Arena",
                "22", "6", "15", "45", "22", "6", "16", "45", "2016", zone,
                "0", "1");
        Event e5 = new Event("Women's 10km Marathon Swimming", "Aquatics", "E", "Fort Copacabana",
                "3", "7", "14", "30", "3", "7", "17", "0", "2016", zone,
                "99.99", "1");
        ArrayList<String> sport3 = new ArrayList<String>();
        sport3.add("Aquatics");
        sport3.add("Boxing");
        sport3.add("Archery");
        e5.addParticipant(new Athlete("Sloane Stephens", "1111111111", "20", "sloanestephens@gmail.com",
                "France", "F", sport3));
        e5.addParticipant(new Athlete("Rafaela Silva", "2222222222", "30", "rafaelasilva@gmail.com",
                "Italy", "F", sport3));
        e5.addParticipant(new Athlete("Junyi Kim", "3333333333", "26", "junyikim@gmail.com",
                "South Korea", "F", sport3));
        e5.addParticipant(new Athlete("Chloe Leurquin", "4444444444", "31", "chloeleurquin@gmail.com",
                "France", "F", sport3));
        e5.addParticipant(new Athlete("Dolores Hernandez", "5555555555", "25", "doloresh@gmail.com",
                "Spain", "F", sport3));
        e5.addParticipant(new Athlete("Edidiong Ofonime Odiong", "6666666666", "34", "odionge@gmail.com",
                "China", "F", sport3));
        e5.addParticipant(new Athlete("Ronda Rousey", "7777777777", "32", "rondarousey@gmail.com",
                "United States", "F", sport3));
        e5.addParticipant(new Athlete("Sania Mirza", "8888888888", "19", "saniamirza@gmail.com",
                "United States", "F", sport3));
        Event e6 = new Event("Women's 10km Marathon Swimming Ceremony", "", "C", "Fort Copacabana",
                "5", "7", "12", "0", "5", "7", "13", "0", "2016", zone,
                "0", "1");
        Event e7 = new Event("Women's Light (57-60kg)", "Boxing", "E", "Riocentro - Pavilion 6",
                "16", "7", "20", "30", "16", "7", "22", "0", "2016", zone,
                "59.99", "1");
        e7.addParticipant(new Athlete("Sloane Stephens", "1111111111", "20", "sloanestephens@gmail.com",
                "France", "F", sport3));
        e7.addParticipant(new Athlete("Rafaela Silva", "2222222222", "30", "rafaelasilva@gmail.com",
                "Italy", "F", sport3));
        e7.addParticipant(new Athlete("Junyi Kim", "3333333333", "26", "junyikim@gmail.com",
                "South Korea", "F", sport3));
        e7.addParticipant(new Athlete("Chloe Leurquin", "4444444444", "31", "chloeleurquin@gmail.com",
                "France", "F", sport3));
        e7.addParticipant(new Athlete("Dolores Hernandez", "5555555555", "25", "doloresh@gmail.com",
                "Spain", "F", sport3));
        e7.addParticipant(new Athlete("Edidiong Ofonime Odiong", "6666666666", "34", "odionge@gmail.com",
                "China", "F", sport3));
        e7.addParticipant(new Athlete("Ronda Rousey", "7777777777", "32", "rondarousey@gmail.com",
                "United States", "F", sport3));
        e7.addParticipant(new Athlete("Sania Mirza", "8888888888", "19", "saniamirza@gmail.com",
                "United States", "F", sport3));
        Event e8 = new Event("Women's Light (57-60kg) Ceremony", "", "C", "Riocentro - Pavilion 6",
                "18", "7", "12", "0", "18", "7", "14", "0", "2016", zone,
                "0", "1");
        Event e9 = new Event("Women's Team Archery", "Archery", "E", "Sambodromo",
                "31", "7", "15", "5", "31", "7", "16", "35", "2016", zone,
                "99.99", "1");
        e9.addParticipant(new Athlete("Sloane Stephens", "1111111111", "20", "sloanestephens@gmail.com",
                "France", "F", sport3));
        e9.addParticipant(new Athlete("Rafaela Silva", "2222222222", "30", "rafaelasilva@gmail.com",
                "Italy", "F", sport3));
        e9.addParticipant(new Athlete("Junyi Kim", "3333333333", "26", "junyikim@gmail.com",
                "South Korea", "F", sport3));
        e9.addParticipant(new Athlete("Chloe Leurquin", "4444444444", "31", "chloeleurquin@gmail.com",
                "France", "F", sport3));
        e9.addParticipant(new Athlete("Dolores Hernandez", "5555555555", "25", "doloresh@gmail.com",
                "Spain", "F", sport3));
        e9.addParticipant(new Athlete("Edidiong Ofonime Odiong", "6666666666", "34", "odionge@gmail.com",
                "China", "F", sport3));
        e9.addParticipant(new Athlete("Ronda Rousey", "7777777777", "32", "rondarousey@gmail.com",
                "United States", "F", sport3));
        e9.addParticipant(new Athlete("Sania Mirza", "8888888888", "19", "saniamirza@gmail.com",
                "United States", "F", sport3));
        Event e10 = new Event("Women's Team Archery Ceremony", "", "C", "Sambodromo",
                "2", "8", "7", "0", "2", "8", "8", "0", "2016", zone,
                "0", "1");
        //**************************************************************************************
        //**************************************************************************************

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        //todo grab existing event schedule from firebase
        eventSchedule.addEvent(e1);
        eventSchedule.addEvent(e2);
        eventSchedule.addEvent(e3);
        eventSchedule.addEvent(e4);
        eventSchedule.addEvent(e5);
        eventSchedule.addEvent(e6);
        eventSchedule.addEvent(e7);
        eventSchedule.addEvent(e8);
        eventSchedule.addEvent(e9);
        eventSchedule.addEvent(e10);

        db.collection("EVENT").document("Schedule").set(eventSchedule);

    }

    public static User getUser() {

        return user;
    }

    public static EventSchedule getSchedule() {

        return eventSchedule;
    }

}
