import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Schedule {

    protected ArrayList<Event> event;

    public Schedule() {

        event = new ArrayList<Event>();

    }

    public void addEvent(Event e) throws InputException, TimeConflictException {

        if(event.contains(e))
            throw new InputException("Event already exists in schedule!");

        Event conflicted = conflict(e);
        if(conflicted != null)
            throw new TimeConflictException(e, conflicted);

        event.add(e);

    }

    public void dropEvent(Event e) throws InputException {

        if(!event.contains(e))
            throw new InputException("Event does not exist in this schedule!");

        event.remove(e);

    }

    public ArrayList searchType(char type) {

        ArrayList result = new ArrayList<Event>();

        for(int i = 0; i < event.size(); i++) {
            Event e = event.get(i);
            if(e.getType() == type)
                result.add(e);
        }

        return result;
    }

    public Event conflict(Event first) {

        Event second;
        int hoursApart = 1;
        String firstStartTime = first.getStartDateTime().minusHours(hoursApart).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String firstEndTime = first.getEndDateTime().plusHours(hoursApart).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        for(int i = 0; i < event.size(); i++) {
            second = event.get(i);
            String secondStartTime = second.getStartDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            String secondEndTime = second.getEndDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            if(firstEndTime.compareTo(secondStartTime) > 0 && firstStartTime.compareTo(secondEndTime) < 0)
                return second;
        }

        return null;
    }

}

class EventSchedule extends Schedule {

    private static EventSchedule schedule = new EventSchedule();
    
    EventSchedule() {

        super();

    }

    public static EventSchedule getInstance() {

        return schedule;
    }

    public void notifySecurity(Event ceremony) {

        //Inform security of award ceremony scheduled
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Security has been notified of " + ceremony);
        System.out.println("-------------------------------------------------------------------------------------------------");
        //Inform security of award ceremony scheduled

    }

    public void informUsers(String msg) {

        //Invoke activity that informs all users of event change
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(msg);
        System.out.println("-------------------------------------------------------------------------------------------------");
        //Invoke activity that informs all users of event change

    }

}

class AthleteSchedule extends Schedule {

    public AthleteSchedule() {

        super();

    }

}