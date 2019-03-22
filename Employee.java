import java.util.ArrayList;

class Employee extends User
{
	public void registerAthlete(String name, int phoneNum, int age, String email, String password, String country,
		char gender, ArrayList sport)throws InputException
   {
      Athlete a = new Athlete(name, phoneNum, age, email, country, gender, sport);  
      /*Add Athlete a to database*/
		/*Add password to Athlete a in database*/
   }
		
   public void schedule(String name, String category, String venue, int month, int day, int year, int startHour, int startMinute,
		int endHour, int endMinute, float price, int seats, ArrayList athlete) throws TimeConflictException
   {
      Event e = new Event(name, category, 'E', venue, month, day, year, startHour, startMinute, endHour, endMinute, price, seats);
			for(int i = 0; i < athlete.size(); i++)
         {
				Athlete a = athlete.get(i);
				e.addParticipant(a);
				a.bookEvent(e);
         }
			eventSchedule.addEvent(e);
			eventSchedule.informUsers(e.getName() + " was just scheduled! It is being held at " + e.getVenue() + ". Check it out!");
   }

	public void schedule(String name, String venue, int month, int day, int year, int startHour, int startMinute,
		int endHour, int endMinute, float price, int seats, ArrayList athlete) throws TimeConflictException
      {
			Event e = new Event(name, null, 'C', venue, month, day, year, startHour, startMinute, endHour, endMinute, price, seats);
			for(int i = 0; i < athlete.size(); i++)
         {
				Athlete a = athlete.get(i);
				e.addParticipant(a);
				a.bookEvent(e);
         }
			eventSchedule.addEvent(e);
			eventSchedule.notifySecurity(e);
			eventSchedule.informUsers(e.getName() + " was just scheduled! It is being held on " + month + "/" + day + "/" + year + ". Check it out!");
      }
		public void cancelEvent(Event e)
      {
			for(int i = 0; i < e.participant.size(); i++)
         {
				Athlete athlete = e.participant.get(i);
				athlete.unbookEvent(e);
         }
			eventSchedule.dropEvent(e);
			int[] date = e.getDate();
			eventSchedule.informUsers(e.getName() + " on " + date[0] + "/" + date[1] + "/" + date[2] + " was cancelled. We apologize for any inconvenience.");
      }
      
		public void update(Event e, String venue, int month, int day, int year, int startHour, int startMinute,
		int endHour, int endMinute)
      {
			e.setVenue(venue);
			e.setDate(month, day, year);
			e.setTime(startHour, startMinute, endHour, endMinute);
			eventSchedule.informUsers("Please be advised that one of our events have changed! " + e.getName() + " is now being held at " + venue + " on " + month + "/" + day + "/" + year + " from " + startHour + ":" + startMinute + " -- " + endHour + ":" + endMinute + ".");
      }
      
		public void update(Event e, int month, int day, int year, int startHour, int startMinute, int endHour, int endMinute)
      {
			e.setDate(month, day, year);
			e.setTime(startHour, startMinute, endHour, endMinute);
			eventSchedule.informUsers("Please be advised that one of our events have changed! " + e.getName() + " is now being held on " + month + "/" + day + "/" + year + " from " + startHour + ":" + startMinute + " -- " + endHour + ":" + endMinute + ".");
      }
      
		public void update(Event e, int startHour, int startMinute, int endHour, int endMinute)
      {
			e.setTime(startHour, startMinute, endHour, endMinute);
			eventSchedule.informUsers("Please be advised that one of our events have changed! " + e.getName() + " is now being held from " + startHour + ":" + startMinute + " -- " + endHour + ":" + endMinute + ".");
      }
      
		public void update(Event e, Athlete old, Athlete current)
      {
			e.dropParticipant(old);
			e.addParticipant(current);
			eventSchedule.informUsers("Please be advised that one of our events have changed! The athlete " + old + " is no longer participating in " + e.getName() + ". Instead, " + current + " will take their place in this event.");
      }
      
		public void requestFreeTicket(Event e) throws EventUnavailableException
      {
			if(!e.isAvailable())
         {
				throw new EventUnavailableException(e);
         }
			ticCount++;
			Ticket tic = new Ticket(ticCount, e);
			ticket.add(tic);
			ticket.get(ticket.size() - 1).displayTicket();
      }
}