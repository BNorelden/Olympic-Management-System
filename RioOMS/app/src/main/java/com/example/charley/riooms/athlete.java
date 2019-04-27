package com.example.charley.riooms;

import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class Athlete extends User {

	private String country;
	private char gender;
	private ArrayList<String> sport;
	private AthleteSchedule schedule;
    
    private EventSchedule eventSchedule;

	public Athlete(String name, String phoneNum, int age, String email, String country, char gender, ArrayList<String> sport) throws InputException {

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

	public void setGender(char gender) throws InputException {
		
		if (gender != 'M' && gender != 'F')
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

	public char getGender() {

		return gender;
	}

	public ArrayList<String> getSports() {

		return sport;
	}

	public ArrayList<Event> checkSchedule() {

		return schedule.getEvents();
	}

	public void scheduleAutograph(String name, String venue, int startDay, int startMonth, int startHr, int startMin,
        int endDay, int endMonth, int endHr, int endMin, int year, String zone, int seats) throws InputException, TimeConflictException {
        
        Event e = new Event(name, "", 'S', venue, startDay, startMonth, startHr, startMin, endDay, endMonth, endHr, endMin, year, zone, 0, seats);
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
    
	public void updateAutograph(Event e, String venue, int startDay, int startMonth, int startHr, int startMin, int endDay, int endMonth, int endHr, int endMin, int year, String zone) {
        
        e.setVenue(venue);
		e.setDateTime(startDay, startMonth, startHr, startMin, endDay, endMonth, endHr, endMin, zone, year);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm z");
        String startDateTime = e.getStartDateTime().format(formatter);
        String endDateTime = e.getEndDateTime().format(formatter);
        eventSchedule.informUsers("Please be advised that one of our autograph sessions have changed! " + e.getName() + " is now being held at " + venue + " from " + startDateTime + " -- " + endDateTime);
        
	}
	
	public void updateAutograph(Event e, int startDay, int startMonth, int startHr, int startMin, int endDay, int endMonth, int endHr, int endMin, int year, String zone) {
		
        e.setDateTime(startDay, startMonth, startHr, startMin, endDay, endMonth, endHr, endMin, zone, year);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm z");
		String startDateTime = e.getStartDateTime().format(formatter);
		String endDateTime = e.getEndDateTime().format(formatter);
        eventSchedule.informUsers("Please be advised that one of our autograph sessions have changed! " + e.getName() + " is now being held from " + startDateTime + " -- " + endDateTime);
        
    }
    
    public void updateAutograph(Event e, int startDay, int startMonth, int endDay, int endMonth, int year) {

        e.setDateTime(startDay, startMonth, endDay, endMonth, year);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy H:mm z");
		String startDateTime = e.getStartDateTime().format(formatter);
		String endDateTime = e.getEndDateTime().format(formatter);
        eventSchedule.informUsers("Please be advised that one of our autograph sessions have changed! " + e.getName() + " is now being held from " + startDateTime + " -- " + endDateTime);

    }

	public void updateAutograph(Event e, int startHr, int startMin, int endHr, int endMin, String zone) {
        
        e.setDateTime(startHr, startMin, endHr, endMin, zone);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm z");
        String startTime = e.getStartDateTime().format(formatter);
        String endTime = e.getEndDateTime().format(formatter);
        eventSchedule.informUsers("Please be advised that one of our autograph sessions have changed! " + e.getName() + " is now being held from " + startTime + " -- " + endTime + ".");
        
	}
    
    public void bookEvent(Event e) throws InputException, TimeConflictException {
		
        schedule.addEvent(e);
        
    }
    
    public void unbookEvent(Event e) throws InputException {
    
        schedule.dropEvent(e);

	}
    
	public void requestFreeTicket(Event e) throws EventUnavailableException{

		if(!e.isAvailable())
			throw new EventUnavailableException(e);
		
		Ticket tic = new Ticket(e);
		ticket.add(tic);
        tic.displayTicket();
        
	}
}
