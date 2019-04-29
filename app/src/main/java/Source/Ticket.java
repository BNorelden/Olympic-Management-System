package Source;

import android.os.Build;
import android.support.annotation.RequiresApi;

public class Ticket {

    public Event event;
    
	public Ticket(Event event) {

		set(event);
		
	}
	
	public void set(Event event) {

		this.event = event;
        event.decSeats();
        
    }

    public Event getEvent() {

	    return event;
    }
    
    public void destroy() {

        event.incSeats();
        
	}
	
	@RequiresApi(api = Build.VERSION_CODES.O)
    public void displayTicket() {

        //Invoke activity to pop-up tickets to user
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Ticket pops up for user! " + event.getName() + " | " + event.getCategory() + " | " + event.getType() + " | " + event.getVenue() + " | " + event.getStartDateTime() + " | " + event.getEndDateTime() + " | " + event.getSeats() + " | " + event.getPrice());
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        //Invoke activity to pop-up tickets to user
		
	}

}