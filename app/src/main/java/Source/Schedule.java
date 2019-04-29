package Source;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

class Schedule {

    protected ArrayList<Event> events;

    public Schedule() {

        events = new ArrayList<Event>();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addEvent(Event e) throws InputException, TimeConflictException {

        if(events.contains(e))
            throw new InputException("Event already exists in schedule!");

        Event conflicted = conflict(e);
        if(conflicted != null)
            throw new TimeConflictException(e, conflicted);

        events.add(e);

    }

    public void dropEvent(Event e) throws InputException {

        if(!events.contains(e))
            throw new InputException("Event does not exist in this schedule!");

        events.remove(e);

    }

    public ArrayList<Event> getEvents() {

        return events;
    }

    public ArrayList<Event> searchType(String type) {

        ArrayList<Event> result = new ArrayList<Event>();

        for(int i = 0; i < events.size(); i++) {
            Event e = events.get(i);
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

        for(int i = 0; i < events.size(); i++) {
            second = events.get(i);
            ZonedDateTime secondStartTime = ZonedDateTime.parse(second.getStartDateTime(), formatter);
            ZonedDateTime secondEndTime = ZonedDateTime.parse(second.getEndDateTime(), formatter);
            if(firstEndTime.isAfter(secondStartTime) && firstStartTime.isBefore(secondEndTime))
                return second;
        }

        return null;
    }

}

class AthleteSchedule extends Schedule {

    public AthleteSchedule() {

        super();

    }

}