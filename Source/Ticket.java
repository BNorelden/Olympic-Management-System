public class Ticket {

    private Event event;
    
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
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Ticket pops up for user!");
        System.out.println("-------------------------------------------------------------------------------------------------");
        //Invoke activity to pop-up ticket to user
		
	}

}