package Source;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
	
    private	String name, category, type, venue, price, numSeats;
    private ZonedDateTime startDateTime;
    private ZonedDateTime endDateTime;
    
    private ArrayList<Athlete> participant;
    
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Event(String name, String category, String type, String venue, String startDay, String startMonth, String startHr, String startMin,
                 String endDay, String endMonth, String endHr, String endMin, String year, String zone, String price, String seats) throws InputException{
        
        setName(name);
        setCat(category);
        setType(type);
        setVenue(venue);
        setDateTime(startDay, startMonth, startHr, startMin, endDay, endMonth, endHr, endMin, zone, year);
        setPrice(price);
        setSeats(seats);

        participant = new ArrayList<Athlete>();

    }
	
	public void setName(String name) {

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
	
    public void setType(String type) throws InputException{

        if(!type.equals("E") && type.equals("C") && type.equals("S"))
            throw new InputException("Type must be either: competition event (E), award ceremony (C), or autograph session (S)!");
        this.type = type;
    
    }
    
    public void setVenue(String venue) {

        this.venue = venue;
        
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setDateTime(String startDay, String startMonth, String startHr, String startMin, String endDay, String endMonth, String endHr, String endMin, String zone, String year) {

        String dateTime;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy H:m z");
        
        dateTime = startDay + "/" + startMonth + "/" + year + " " + startHr + ":" + startMin + " " + zone;

        startDateTime = ZonedDateTime.parse(dateTime, format);

        dateTime = endDay + "/" + endMonth + "/" + year + " " + endHr + ":" + endMin + " " + zone;

        endDateTime = ZonedDateTime.parse(dateTime, format);

    }
		
    public void setPrice(String price) {

        this.price = price;

    }

    public void setSeats(String seats) {

        this.numSeats = seats;

    }
    
    public void incSeats() {

        int num = Integer.parseInt(numSeats);

        numSeats = Integer.toString(++num);

    }

    public void decSeats() {
        
        int num = Integer.parseInt(numSeats);

        numSeats = Integer.toString(--num);

    }

    public Boolean isAvailable() {
        
        if(numSeats.equals("0"))
            return false;
        return true;
    }
    
    public void addParticipant(Athlete newAthlete) {

        participant.add(newAthlete);

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
    
    public String getType() {

        return type;
    }
    public String getVenue() {

        return venue;
    }
    
    public ZonedDateTime getStartDateTime() {

        return startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        
        return endDateTime;
    }
    
    public String getPrice() {

        return price;
    }
        
    public String getSeats() {

        return numSeats;
    }
    
    public ArrayList<Athlete> getParticipants() {

        return participant;
    }

}