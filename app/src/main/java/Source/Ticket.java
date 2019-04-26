package Source;

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
	
	public void displayTicket() {

        //Invoke activity to pop-up ticket to user
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Ticket pops up for user! " + event.getName() + " | " + event.getCat() + " | " + event.getType() + " | " + event.getVenue() + " | " + event.getStartDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm z")) + " | " + event.getEndDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm z")) + " | " + event.getSeats() + " | " + event.getPrice());
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        //Invoke activity to pop-up ticket to user
		
	}

}