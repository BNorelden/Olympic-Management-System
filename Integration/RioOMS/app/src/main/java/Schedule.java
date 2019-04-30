import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Schedule {

    protected ArrayList<Event> event;

    Schedule() {

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

    public Event searchName(String name) {

        Event result = null;

        for(int i = 0; i < event.size(); i++) {
            Event e = event.get(i);
            if(e.getName() == name) {
                result = e;
                break;
            }
        }

        return result;
    }

    public ArrayList searchCat(String category) {

        ArrayList result = new ArrayList<Event>();

        for(int i = 0; i < event.size(); i++) {
            Event e = event.get(i);
            if(e.getCat() == category)
                result.add(e);
        }

        return result;
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

    public ArrayList searchVen(String venue) {

        ArrayList result = new ArrayList<Event>();

        for(int i = 0; i < event.size(); i++) {
            Event e = event.get(i);
            if(e.getVenue() == venue)
                result.add(e);
        }

        return result;
    }

    public ArrayList searchDate(String date) {

        ArrayList result = new ArrayList<Event>();

        for(int i = 0; i < event.size(); i++) {
            Event e = event.get(i);
            ZonedDateTime dateTime = e.getStartDateTime();
            String thisDate = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if(thisDate.equals(date))
                result.add(e);
        }

        return result;
    }

    public ArrayList searchTime(String time) {

        ArrayList result = new ArrayList<Event>();

        for(int i = 0; i < event.size(); i++) {
            Event e = event.get(i);
            ZonedDateTime dateTime = e.getStartDateTime();
            String thisTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
            if(thisTime.equals(time))
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

    EventSchedule() {

        super();

    }

    public void notifySecurity(Event ceremony) {

        //Inform security of award ceremony scheduled

    }

    public void informUsers(String msg) {

        //Invoke activity that informs all users of event change

    }

}

class AthleteSchedule extends Schedule {

    AthleteSchedule() {

        super();

    }

}