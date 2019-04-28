package Source;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Athlete extends User {

	private String country, gender;
	private ArrayList<String> sport;
	private AthleteSchedule schedule;
    
    private EventSchedule eventSchedule;

	public Athlete(String name, String phoneNum, String age, String email, String country, String gender, ArrayList<String> sport) throws InputException {

		super(name, phoneNum, age, email);
		setCountry(country);
		setGender(gender);
        setSports(sport);
        
        schedule = new AthleteSchedule();
        eventSchedule = EventSchedule.getInstance();
        
	}

	public void setSports(ArrayList<String> sport) {
		
		this.sport = sport;

	}

	public void setGender(String gender) throws InputException {
		
		if (!gender.equals("M") && !gender.equals("F"))
			throw new InputException("Invalid option for gender!"); 
		this.gender = gender;

	}

	public void setCountry(String country) throws InputException {
		
	    char character = 24;
	    for(int i = 0; i < country.length(); i++)
		    character = country.charAt(i);
			if (character != ' ' && (character < 'A' || (character > 'Z' && character < 'a') || character > 'z'))
				throw new InputException("Country names do not contain numbers or special characters!");
		this.country = country;
	}

	public String getCountry() {

		return country;
	}

	public String getGender() {

		return gender;
	}

	public ArrayList<String> getSports() {

		return sport;
	}

	public ArrayList<Event> checkSchedule() {

		return schedule.getEvents();
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	public void scheduleAutograph(String name, String venue, String startDay, String startMonth, String startHr, String startMin,
								  String endDay, String endMonth, String endHr, String endMin, String year, String zone, String seats) throws InputException, TimeConflictException {
        
        Event e = new Event(name, "", "S", venue, startDay, startMonth, startHr, startMin, endDay, endMonth, endHr, endMin, year, zone, "0", seats);
        e.addParticipant(this);
        bookEvent(e);
        eventSchedule.addEvent(e);
        eventSchedule.informUsers(e.getName() + " by " + this.name + " was just schedule! Check it out!");

	}
	public void cancelAutograph(Event e) throws InputException{
        
        unbookEvent(e);
		eventSchedule.dropEvent(e);
		eventSchedule.informUsers(e.getName() + " by " + this.name + " was cancelled. We apologize for any inconvenience.");

    }
    
	@RequiresApi(api = Build.VERSION_CODES.O)
	public void updateAutograph(Event e, String venue, String startDay, String startMonth, String startHr, String startMin, String endDay, String endMonth, String endHr, String endMin, String year, String zone) {
        
        e.setVenue(venue);
		e.setDateTime(startDay, startMonth, startHr, startMin, endDay, endMonth, endHr, endMin, zone, year);

        String startDateTime = e.getStartDateTime();
        String endDateTime = e.getEndDateTime();
        eventSchedule.informUsers("Please be advised that one of our autograph sessions have changed! " + e.getName() + " is now being held at " + venue + " from " + startDateTime + " -- " + endDateTime);
        
	}
    
    @RequiresApi(api = Build.VERSION_CODES.O)
	public void bookEvent(Event e) throws InputException, TimeConflictException {
		
        schedule.addEvent(e);
        
    }
    
    public void unbookEvent(Event e) throws InputException {
    
        schedule.dropEvent(e);

	}
    
	@RequiresApi(api = Build.VERSION_CODES.O)
	public void requestFreeTicket(Event e) throws EventUnavailableException{

		if(!e.isAvailable())
			throw new EventUnavailableException(e);
		
		Ticket tic = new Ticket(e);
		ticket.add(tic);
        tic.displayTicket();
        
	}
}
