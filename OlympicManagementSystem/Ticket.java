package OlympicManagementSystem;

public class Ticket  {
	int ticketID;
	Event event;
	public Ticket (int ticketID, Event event) {
		set(ticketID, event);
		
	}
	
	public Ticket() {
		event.incSeats();
	}
	
	public void set(int ticketID, Event event) {
		this.ticketID = ticketID;
		this.event = event;
		event.decSeats();
	}
	
	public void displayTicket() {
		// TODO Auto-generated method stub
		
	}

}
