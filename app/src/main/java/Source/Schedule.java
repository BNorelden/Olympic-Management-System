package Source;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

class Schedule {

    protected ArrayList<Event> event;

    public Schedule() {

        event = new ArrayList<Event>();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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

    public ArrayList<Event> getEvents() {

        return event;
    }

    public ArrayList<Event> searchType(String type) {

        ArrayList<Event> result = new ArrayList<Event>();

        for(int i = 0; i < event.size(); i++) {
            Event e = event.get(i);
            if(e.getType().equals(type))
                result.add(e);
        }

        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Event conflict(Event first) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yyyy H:mm z");

        Event second;
        int hoursApart = 1;
        ZonedDateTime firstStartTime = ZonedDateTime.parse(first.getStartDateTime(), formatter).minusHours(hoursApart);
        ZonedDateTime firstEndTime = ZonedDateTime.parse(first.getEndDateTime(), formatter).plusHours(hoursApart);

        for(int i = 0; i < event.size(); i++) {
            second = event.get(i);
            ZonedDateTime secondStartTime = ZonedDateTime.parse(second.getStartDateTime(), formatter);
            ZonedDateTime secondEndTime = ZonedDateTime.parse(second.getEndDateTime(), formatter);
            if(firstEndTime.isAfter(secondStartTime) && firstStartTime.isBefore(secondEndTime))
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
        System.out.println("Security has been notified of " + ceremony.getName());
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