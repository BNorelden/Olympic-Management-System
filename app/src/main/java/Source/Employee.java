package Source;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;

class Employee extends User {

    private EventSchedule eventSchedule;

    public Employee(String name, String phoneNum, String age, String email) throws InputException {

        super(name, phoneNum, age, email);
        eventSchedule = EventSchedule.getInstance();

    }

	public void registerAthlete(String name, String phoneNum, String age, String email, String password, String country,
        String gender, ArrayList<String> sport) throws InputException {
      
        Athlete a = new Athlete(name, phoneNum, age, email, country, gender, sport);
        /*Add to database*/
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Athlete " + name + " has been added!");
        System.out.println("Phone " + phoneNum + " | Age " + age + " | Email " + email + " | Country " + country + " | Gender " + gender + " | Sports " + sport);
        System.out.println("The password has been encrypted and added to " + name);
        System.out.println("-------------------------------------------------------------------------------------------------");
        /*Add to database*/
        
    }
	
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void schedule(String name, String category, String type, String venue, String startDay, String startMonth, String startHr, String startMin,
                         String endDay, String endMonth, String endHr, String endMin, String year, String zone, String price, String seats, ArrayList<Athlete> athlete) throws InputException, TimeConflictException {
    
        Event e = new Event(name, category, type, venue, startDay, startMonth, startHr, startMin, endDay, endMonth, endHr, endMin, year, zone, price, seats);
        eventSchedule.addEvent(e);
        for(int i = 0; i < athlete.size(); i++) {
            Athlete a = athlete.get(i);
            e.addParticipant(a);
            a.bookEvent(e);
        }

        String startTime = e.getStartDateTime();
        String endTime = e.getEndDateTime();

        eventSchedule.informUsers(name + " was just scheduled! It is being held at " + venue + " from " + startTime + " -- " + endTime + ". Check it out!");

        if(type.equals("C"))
            eventSchedule.notifySecurity(e);
        
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void cancelEvent(Event e) throws InputException {

        ArrayList<Athlete> participants = e.getParticipants();
        
        for(int i = 0; i < participants.size(); i++) {
				Athlete athlete = participants.get(i);
				athlete.unbookEvent(e);
        }
        eventSchedule.dropEvent(e);

        String date = e.getStartDateTime();
		eventSchedule.informUsers(e.getName() + " held on " + date + " was cancelled. We apologize for any inconvenience.");

    }
      
	@RequiresApi(api = Build.VERSION_CODES.O)
    public void update(Event e, String venue, String startDay, String startMonth, String startHr, String startMin, String endDay, String endMonth, String endHr, String endMin, String year, String zone) {

        e.setVenue(venue);
        e.setDateTime(startDay, startMonth, startHr, startMin, endDay, endMonth, endHr, endMin, zone, year);

        String startDateTime = e.getStartDateTime();
        String endDateTime = e.getEndDateTime();

        eventSchedule.informUsers("Please be advised that one of our events have changed! " + e.getName() + " is now being held at " + venue + " from " + startDateTime + " -- " + endDateTime);

    }

    public void update(Event e, Athlete oldAthlete, Athlete newAthlete) {

        e.dropParticipant(oldAthlete);
        e.addParticipant(newAthlete);

        eventSchedule.informUsers("Please be advised that one of our events have changed! The athlete " + oldAthlete.getName() + " is no longer participating in " + e.getName() + ". Instead, " + newAthlete.getName() + " will take their place in this events.");

    }
      
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void requestFreeTicket(Event e) throws EventUnavailableException {

	    if(!e.isAvailable())
                throw new EventUnavailableException(e);
                
        Ticket tic = new Ticket(e);
        tickets.add(tic);
        tic.displayTicket();

    }

}