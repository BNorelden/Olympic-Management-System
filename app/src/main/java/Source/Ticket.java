package Source;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.format.DateTimeFormatter;

public class Ticket {

    public Event event;
    
	public Ticket(Event event) {

		set(event);
		
	}
	
	public void set(Event event) {

		this.event = event;
        event.decSeats();
        
    }
    
    public void destroy() {

        event.incSeats();
        
	}
	
	@RequiresApi(api = Build.VERSION_CODES.O)
    public void displayTicket() {

        //Invoke activity to pop-up ticket to user
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Ticket pops up for user! " + event.getName() + " | " + event.getCat() + " | " + event.getType() + " | " + event.getVenue() + " | " + event.getStartDateTime() + " | " + event.getEndDateTime() + " | " + event.getSeats() + " | " + event.getPrice());
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        //Invoke activity to pop-up ticket to user
		
	}

}