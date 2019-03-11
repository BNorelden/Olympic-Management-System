import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TestDriver {

    public static void main(String args[]) {

        Event event1 = new Event();
        Event event2 = new Event();
        Event event3 = new Event();
        Event event4 = new Event();
        Event event5 = new Event();

        event1.name = "Olympic 100m";
        event1.category = "Track and Field";
        event1.type = 'E';
        event1.venue = "Olympic Track";
        event1.startDateTime = ZonedDateTime.parse("20/07/2016 18:00 BRT", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));
        event1.endDateTime = ZonedDateTime.parse("20/07/2016 20:00 BRT", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));

        event2.name = "100m Awards";
        event2.category = null;
        event2.type = 'C';
        event2.venue = "Olympic Track";
        event2.startDateTime = ZonedDateTime.parse("27/07/2016 23:00 BRT", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));
        event2.endDateTime = ZonedDateTime.parse("28/07/2016 01:00 BRT", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));

        event3.name = "Individual Road Race";
        event3.category = "Road Cycling";
        event3.type = 'E';
        event3.venue = "1000m Offroad Track";
        event3.startDateTime = ZonedDateTime.parse("31/07/2016 09:00 BRT", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));
        event3.endDateTime = ZonedDateTime.parse("31/07/2016 10:30 BRT", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));

        event4.name = "Lance Autograph Session";
        event4.category = null;
        event4.type = 'S';
        event4.venue = "Olympic Stadium";
        event4.startDateTime = ZonedDateTime.parse("08/08/2016 13:00 BRT", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));
        event4.endDateTime = ZonedDateTime.parse("08/08/2016 14:00 BRT", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));

        event5.name = "Karch vs. Ana";
        event5.category = "Indoor Volleyball";
        event5.type = 'E';
        event5.venue = "Olympic Stadium";
        event5.startDateTime = ZonedDateTime.parse("10/08/2016 21:00 BRT", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));
        event5.endDateTime = ZonedDateTime.parse("10/08/2016 23:00 BRT", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm z"));

        EventSchedule eventSchedule = new EventSchedule();

        try {
            eventSchedule.addEvent(event1);
            eventSchedule.addEvent(event2);
            eventSchedule.addEvent(event3);
            eventSchedule.addEvent(event4);
            eventSchedule.addEvent(event5);
            
            System.out.println("Search Name: Karch vs. Ana");
            System.out.println(eventSchedule.searchName("Karch vs. Ana"));
            
            System.out.println("Search Category: Track and Field");
            ArrayList list1 = eventSchedule.searchCat("Track and Field");
            for(int i = 0; i < list1.size(); i++)
                System.out.println(list1.get(i));

            System.out.println("Search Type: Competition Event (E)");
            ArrayList list2 = eventSchedule.searchType('E');
            for(int i = 0; i < list2.size(); i++)
                System.out.println(list2.get(i));

            System.out.println("Search Venue: Olympic Track");
            ArrayList list3 = eventSchedule.searchVen("Olympic Track");
            for(int i = 0; i < list3.size(); i++)
                System.out.println(list3.get(i));

            System.out.println("Search Date: 08/08/2016");
            ArrayList list4 = eventSchedule.searchDate("08/08/2016");
            for(int i = 0; i < list4.size(); i++)
                System.out.println(list4.get(i));

            System.out.println("Search Time: 23:00");
            ArrayList list5 = eventSchedule.searchTime("23:00");
            for(int i = 0; i < list5.size(); i++)
                System.out.println(list5.get(i));

            eventSchedule.dropEvent(event4);
            eventSchedule.dropEvent(event4);

        }
        catch (InputException i) {
            System.out.println(i.get());
        }
        catch (TimeConflictException t) {
            System.out.println(t.get());
        }

    }

}