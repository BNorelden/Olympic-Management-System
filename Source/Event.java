import java.util.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
	
    private	String name;
    private String category;
    private char type;
    private String venue;
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;
    private float price;
    private int numSeats;
    
    private ArrayList<Athlete> participant;
    
    public  Event(String name, String category, char type, String venue, int startDay, int startMonth, int startHr, int startMin,
        int endDay, int endMonth, int endHr, int endMin, int year, String zone, float price, int seats) throws InputException{
        
        setName(name);
        setCat(category);
        setType(type);
        setVenue(venue);
        setStartDate(startDay, startMonth, year);
        setStartTime(startHr, startMin, zone);
        setEndDate(endDay, endMonth, year);
        setEndTime(endHr, endMin, zone);
        setPrice(price);
        setSeats(seats);

        participant = new ArrayList<Athlete>();

    }
	
	public void setName(String name) throws InputException { 

		for(int i = 0; i < name.length(); i++) {
			char character = name.charAt(i);
			if(character != ' ' && (character < '0' || (character > '9' && character < 'A') ||
			(character > 'Z' && character < 'a') || character > 'z'))
				throw new InputException("Event names are not allowed to contain special characters!");
		}
        this.name = name;
        
	}
	
	public void setCat(String category) throws InputException {

		for(int i = 0; i < category.length(); i++) {
			char character = category.charAt(i);
			if(character != ' ' && (character < 'A' || (character > 'Z' && character < 'a') || character > 'z'))
				throw new InputException("Categories are not allowed to contain numbers or special characters!");
        }
        this.category = category;

    }
	
    public void setType(char type) throws InputException{

        if(type != 'E' && type != 'C' && type != 'S')
            throw new InputException("Type must be either: competition event (E), award ceremony (C), or autograph session (S)!");
        this.type = type;
    
    }
    
    public void setVenue(String venue) {

        this.venue = venue;
        
    }
		
	public void setStartDate(int startDay, int startMonth, int year) {
            
        String date = startDay + "/" + startMonth + "/" + year;
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");
			
        startDateTime = ZonedDateTime.parse(date, format);
        
    }
    
    public void setStartTime(int startHr, int startMin, String zone) {

        String time = startHr + ":" + startMin + " " + zone;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("H:mm z");

        startDateTime = ZonedDateTime.parse(time, format);

    }

	public void setEndDate(int endDay, int endMonth, int year) {
        
        String date = endDay + "/" + endMonth + "/" + year;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");

        endDateTime = ZonedDateTime.parse(date, format);
            
    }
    
    public void setEndTime(int endHr, int endMin, String zone) {

        String time = endHr + ":" + endMin + " " + zone;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("H:mm z");

        endDateTime = ZonedDateTime.parse(time, format);

    }
		
    public void setPrice(float price) throws InputException{

        if(price < 0)
            throw new InputException("Price cannot be a negative value!");
        this.price = price;

    }

    public void setSeats(int seats) throws InputException {

        if(seats < 0)
            throw new InputException("Number of seats cannot be negative!");
        this.numSeats = seats;

    }
    
    public void incSeats() {

        numSeats++;

    }

    public void decSeats() {
        
        numSeats--;

    }

    public Boolean isAvailable() {
        
        if(numSeats == 0)
            return false;
        return true;
    }
    
    public void addParticipant(Athlete newAthlete) {

        participant.add(newUser);

    }
        
    public void dropParticipant(Athlete oldAthlete) {

        participant.remove(oldAthlete);

    }
        
    public String getName() {

        return name;
    }
        
    public String getCat() {

        return category;
    }
    
    public char getType() {

        return type;
    }
    public String getVenue() {

        return venue;
    }
    
    public ZonedDateTime getStartDate() {

        return startDateTime;
    }

    public ZonedDateTime getEndDate() {
        
        return endDateTime;
    }
    
    public float getPrice() {

        return price;
    }
        
    public int getSeats() {

        return numSeats;
    }
    
    public ArrayList<String> getParticipants() {

        return participant;
    }

}