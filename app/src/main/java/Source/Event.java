package Source;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
	
    private	String name, category, type, venue, startDateTime, endDateTime, price, numSeats;
    
    private ArrayList<Athlete> participant;
    
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Event(String name, String category, String type, String venue, String startMonth, String startDay, String startHr, String startMin,
                 String endMonth, String endDay, String endHr, String endMin, String year, String zone, String price, String seats) throws InputException{
        
        setName(name);
        setCat(category);
        setType(type);
        setVenue(venue);
        setDateTime(startMonth, startDay, startHr, startMin, endMonth, endDay, endHr, endMin, zone, year);
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
    public void setDateTime(String startMonth, String startDay, String startHr, String startMin, String endMonth, String endDay, String endHr, String endMin, String zone, String year) {

        ZonedDateTime converter;

        DateTimeFormatter formatterIn = DateTimeFormatter.ofPattern("M/d/yyyy H:m z");
        DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("M/dd/yyyy H:mm z");

        String dateTime;

        dateTime = startMonth + "/" + startDay + "/" + year + " " + startHr + ":" + startMin + " " + zone;
        converter = ZonedDateTime.parse(dateTime, formatterIn);
        startDateTime = converter.format(formatterOut);

        dateTime = endMonth + "/" + endDay + "/" + year + " " + endHr + ":" + endMin + " " + zone;
        converter = ZonedDateTime.parse(dateTime, formatterIn);
        endDateTime = converter.format(formatterOut);

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
    
    public String getStartDateTime() {

        return startDateTime;
    }

    public String getEndDateTime() {
        
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