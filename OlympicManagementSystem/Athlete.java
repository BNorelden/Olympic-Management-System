package OlympicManagementSystem;
import java.util.ArrayList;


public class Athlete extends users {
	private String country;
	private char gender;
	private ArrayList<String> sport;
	AtheleteSchedule schedule;
	private EventSchedule eventSchedule;
	private String name;

	public Athlete(String name, String phoneNum, int age, String email, String country, char gender, ArrayList<String> sport) throws InputException {
		super(name, phoneNum, age, email);
		setCountry(country);
		setGender(gender);
		setSports(sport);
		schedule = new AtheleteSchedule();
	}

	public void setSports(ArrayList<String> sport) {
		// TODO Auto-generated method stub
		this.sport = sport;

	}

	 public void setGender(char gender) throws InputException {
		// TODO Auto-generated method stub
		if (gender != 'M' && gender != 'F')
			throw new InputException("Invalid option for gender!"); 
		this.gender = gender;

	}

	public void setCountry(String country) throws InputException {
		// TODO Auto-generated method stub
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

	public char getGender() {
		return gender;
	}

	 public ArrayList<String> getSports() {
		return sport;
	}

	public AtheleteSchedule checkSchedule() {
		return schedule;
	}

	public void scheduleAutograph(String name, String venue, int startDay, int startMonth, int startHr, int startMin,
			int endDay, int endMonth, int endHr, int endMin, int year, String zone, int seats)
			throws InputException, TimeConflictException {
		Event e = new Event(name, null, 'S', venue, startDay, startMonth, startHr, startMin, endDay, endMonth, endHr,
				endMin, year, zone, 0, seats);
		//e.addParticipant(this);
		bookEvent(e);
		eventSchedule.addEvent(e);
		eventSchedule.informUsers(e.getName() + " by " + this.name + " was just schedule! Check it out!");
	}
	void cancelAutograph(Event e) throws InputException{
		unbookEvent(e);
		eventSchedule.dropEvent(e);
		eventSchedule.informUsers(e.getName() + " by " + this.name + " was cancelled. We apologize for any inconvenience.");

	}
	void updateAutograph(Event e, String venue, int startDay, int startMonth, int startHr, int startMin, int endDay, int endMonth, int endHr, int endMin, int year, String zone) {
		e.setVenue(venue);
		
		e.setStartDate(startDay, startMonth, year, startHr, startMin, zone);
		e.setEndDate(endDay, endMonth, year, endHr, endMin, zone);
		//e.setStartDate(startDay, startMonth, year);
		//e.setStartTime(startHr, startMin, zone);
		//e.setEndDate(endDay, endMonth, year);
		//e.setEndTime(endHr, endMin, zone);
		String startDateTime = ( e.getStartDate()+ " " + e.getStartDateTime());
		String endDateTime = e.getEndDate()+ " " + e.getEndDate();
		eventSchedule.informUsers("Please be advised that one of our autograph sessions have changed! " + e.getName() + " is now being held at " + venue + " from " + startDateTime + " -- " + endDateTime);
	}
	
	void updateAutograph(Event e, int startDay, int startMonth, int startHr, int startMin, int endDay, int endMonth, int endHr, int endMin, int year, String zone) {
		//e.setStartDate(startDay, startMonth, year);
		//e.setStartTime(startHr, startMin, zone);
		//e.setEndDate(endDay, endMonth, year);
		//e.setEndTime(endHr, endMin, zone);
		e.setStartDate(startDay, startMonth, year, startHr, startMin, zone);
		e.setEndDate(endDay, endMonth, year, endHr, endMin, zone);
		String startDateTime = ( e.getStartDate()+ " " + e.getStartDateTime());
		String endDateTime = e.getEndDate()+ " " + e.getEndDate();
		//String startDateTime = e.getStartDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy h:mm z"));
		//String endDateTime = e.getEndDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy h:mm z"));
		eventSchedule.informUsers("Please be advised that one of our autograph sessions have changed! " + e.getName() + " is now being held from " + startDateTime + " -- " + endDateTime);
	}
/*
	void updateAutograph(Event e, int startHr, int startMin, int endHr, int endMin, String zone) {
		/*e.setStartTime(startHr, startMin, zone);
		e.setEndTime(endHr, endMin, zone);
		String startTime = e.getStartDateTime().format(DateTimeFormatter.ofPattern("h:mm z"));
		String endTime = e.getEndDateTime().format(DateTimeFormatter.ofPattern("h:mm z"));
		e.setStartDate(startDay, startMonth, year, startHr, startMin, zone);
		e.setEndDate(endDay, endMonth, year, endHr, endMin, zone);
		String startDateTime = ( e.getStartDate()+ " " + e.getStartDateTime());
		String endDateTime = e.getEndDate()+ " " + e.getEndDate();
		
		eventSchedule.informUsers("Please be advised that one of our autograph sessions have changed! " + e.getName() + " is now being held from " + startTime + " -- " + endTime + ".");
	} */
		private void unbookEvent(Event e) throws InputException {
		// TODO Auto-generated method stub
		schedule.dropEvent(e);
	}

	private void bookEvent(Event e) throws InputException, TimeConflictException {
		// TODO Auto-generated method stub
		schedule.addEvent(e);
	}
	void requestFreeTicket(Event e) throws EventUnavailableException{
		if(!e.isAvailable())
			throw new EventUnavailableException(e);// CAUSING The constructor EventUnavailableException(Event) is undefined
		
		ticCount++;
		Ticket tic = new Ticket(ticCount, e);
		ticket.add(tic);
		tic.displayTicket();
	}
}
