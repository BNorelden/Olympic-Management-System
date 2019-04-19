import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

class Employee extends User {

    private EventSchedule eventSchedule;

    public Employee(String name, String phoneNum, int age, String email) {

        super(name, phoneNum, age, email);
        eventSchedule = EventSchedule.getInstance();

    }

	public void registerAthlete(String name, int phoneNum, int age, String email, String password, String country,
        char gender, ArrayList sport) throws InputException {
      
        Athlete a = new Athlete(name, phoneNum, age, email, country, gender, sport);
        /*Add to database*/
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Athlete " + name + " has been added!");
        System.out.println("The password has been encrypted and added to " + name);
        System.out.println("-------------------------------------------------------------------------------------------------");
        /*Add to database*/
        
    }
		
    public void schedule(String name, String category, char type, String venue, int startDay, int startMonth, int startHr, int startMin,
        int endDay, int endMonth, int endHr, int endMin, int year, String zone, float price, int seats, ArrayList athlete) throws TimeConflictException {
    
        Event e = new Event(name, category, type, venue, startDay, startMonth, startHr, startMin, endDay, endMonth, endHr, endMin, year, zone, price, seats);
        eventSchedule.addEvent(e);
        for(int i = 0; i < athlete.size(); i++) {
            Athlete a = athlete.get(i);
            e.addParticipant(a);
            a.bookEvent(e);
        }

        if(type == 'C')
            eventSchedule.notifySecurity(e);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy H:mm z");
        String startTime = e.getStartDate().format(format);
        String endTime = e.getStartDate().format(format);

        eventSchedule.informUsers(name + " was just scheduled! It is being held at " + venue + " from " + startTime + " -- " + endTime + ". Check it out!");
        
    }

    public void cancelEvent(Event e) throws InputException {

        ArrayList participants = e.getParticipants();
        
        for(int i = 0; i < participants.size(); i++) {
				Athlete athlete = participants.get(i);
				athlete.unbookEvent(e);
        }
        eventSchedule.dropEvent(e);

        String date = e.getStartDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy"));
		eventSchedule.informUsers(e.getName() + " held on " + date + " was cancelled. We apologize for any inconvenience.");

    }
      
	public void update(Event e, String venue, int startDay, int startMonth, int startHr, int startMin, int endDay, int endMonth, int endHr, int endMin, int year, String zone) {

        e.setVenue(venue);
        e.setStartDate(startDay, startMonth, year);
        e.setStartTime(startHr, startMin, zone);
        e.setEndDate(endDay, endMonth, year);
        e.setEndTime(endHr, endMin, zone);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy H:mm z");
        String startDateTime = e.getStartDate().format(format);
        String endDateTime = e.getEndDate().format(format);

        eventSchedule.informUsers("Please be advised that one of our events have changed! " + e.getName() + " is now being held at " + venue + " from " + startDateTime + " -- " + endDateTime);

    }

    public void update(Event e, int startDay, int startMonth, int startHr, int startMin, int endDay, int endMonth, int endHr, int endMin, int year, String zone) {

        e.setStartDate(startDay, startMonth, year);
        e.setStartTime(startHr, startMin, zone);
        e.setEndDate(endDay, endMonth, year);
        e.setEndTime(endHr, endMin, zone);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy H:mm z");
        String startDateTime = e.getStartDate().format(format);
        String endDateTime = e.getEndDate().format(format);

        eventSchedule.informUsers("Please be advised that one of our events have changed! " + e.getName() + " is now being held from " + startDateTime + " -- " + endDateTime);

    }

    public void update(Event e, int startHr, int startMin, int endHr, int endMin, String zone) {

        e.setStartTime(startHr, startMin, zone);
        e.setEndTime(endHr, endMin, zone);

        DateTimeFormatter format = DateTimeFormatter.ofPattern("H:mm z");
        String startTime = e.getStartDate().format(format);
        String endTime = e.getEndDate().format(format);

        eventSchedule.informUsers("Please be advised that one of our events have changed! " + e.getName() + " is now being held from " + startTime + " -- " + endTime);

    }

    public void update(Event e, Athlete oldAthlete, Athlete newAthlete) {

        e.dropParticipant(oldAthlete);
        e.addParticipant(newAthlete);

        eventSchedule.informUsers("Please be advised that one of our events have changed! The athlete " + oldAthlete + " is no longer participating in " + e.getName() + ". Instead, " + newAthlete + " will take their place in this event.");

    }
      
    public void requestFreeTicket(Event e) throws EventUnavailableException {

	    if(!e.isAvailable())
                throw new EventUnavailableException(e);
                
        Ticket tic = new Ticket(e);
        ticket.add(tic);
        tic.displayTicket();

    }

}