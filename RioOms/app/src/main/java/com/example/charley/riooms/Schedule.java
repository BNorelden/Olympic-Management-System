package com.example.charley.riooms;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

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

    public ArrayList<Event> getEvents() {

        return event;
    }

    public ArrayList<Event> searchType(char type) {

        ArrayList<Event> result = new ArrayList<Event>();

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String firstStartTime = first.getStartDateTime().minusHours(hoursApart).format(formatter);
        String firstEndTime = first.getEndDateTime().plusHours(hoursApart).format(formatter);

        for(int i = 0; i < event.size(); i++) {
            second = event.get(i);
            String secondStartTime = second.getStartDateTime().format(formatter);
            String secondEndTime = second.getEndDateTime().format(formatter);
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