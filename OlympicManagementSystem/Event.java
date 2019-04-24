package OlympicManagementSystem;


import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Event {
	
	
		private	String name;
		private String category;
		private char type;
		private String venue;
		ZonedDateTime startDateTime;
		ZonedDateTime endDateTime;
		private float price;
		private int numSeats;
		
		ArrayList<String> participant = new ArrayList<String>();
		
		
		
		public  Event(String name, String category, char type, String venue, int startDay, int startMonth, int startHr, int startMin,
					  int endDay, int endMonth, int endHr, int endMin, int year, String zone, float price, int seats) 
							  throws InputException {
			
		setName(name);
		setCat(category);
		setType(type);
		setVenue(venue);
		setStartDate(startDay, startMonth, year, startHr, startMin, zone);
		setEndDate(endDay, endMonth, year, endHr, endMin, zone);
		setPrice(price);
		setSeats(seats);
	
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
	
	public void setCat(String category) throws InputException{ //InputException
		for(int i = 0; i < category.length(); i++) {
			char character = category.charAt(i);
			if(character != ' ' && (character < 'A' || (character > 'Z' && character < 'a') || character > 'z'))
				throw new InputException("Categories are not allowed to contain numbers or special characters!");
		
		} this.category = category; }
		
	
		public void setType(char type) throws InputException{
			if(type != 'E' && type != 'C' && type != 'S')
				throw new InputException("Type must be either: competition event (E), award ceremony (C), or autograph session (S)!");
			this.type = type;
		
		}
		
		public void setVenue(String venue) {
			this.venue = venue;
	}
		
		
		
		void setStartDate(int startDay, int startMonth, int year, int startHr, int startMin, String zone) {
			String date = startDay + "/" + startMonth + "/" + year + " " + startHr + ":" + startMin + " " + zone;
			DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy H:m z");
			
			startDateTime = ZonedDateTime.parse(date, format);
		}


		void setEndDate(int endDay, int endMonth, int year, int endHr, int endMin, String zone) {
			String date = endDay + "/" + endMonth + "/" + year + " " + endHr + ":" + endMin + " " + zone;
			DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy H:m z");
			endDateTime = ZonedDateTime.parse(date, format);
		}
		
		
		void setStartTime(int hr, int min, String zone) {
			String time = hr + ":" + min + " " + zone;
			DateTimeFormatter format = DateTimeFormatter.ofPattern("H:m z");
			startDateTime = ZonedDateTime.parse(time, format);
		}
		
				
	/*	
		void setStartDate(int day, int month, int year) {
			String date = day + "/" + month + "/" + year;
			DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");
			startDateTime = ZonedDateTime.parse(date, format);
		}
			
		void setStartTime(int hr, int min, String zone) {
			String time = hr + ":" + min + " " + zone;
			DateTimeFormatter format = DateTimeFormatter.ofPattern("H:m z");
			startDateTime = ZonedDateTime.parse(time, format);
		}

		void setEndDate(int day, int month, int year) {
			String date = day + "/" + month + "/" + year;
			DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy");
			endDateTime = ZonedDateTime.parse(date, format);
		}
			
		void setEndTime(int hr, int min, String zone) {
			String time = hr + ":" + min + " " + zone;
			DateTimeFormatter format = DateTimeFormatter.ofPattern("H:m z");
			endDateTime = ZonedDateTime.parse(time, format);

		} */
		
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

		Boolean isAvailable() {
			if(numSeats == 0)
				return false;
			return true;
		}
		
		public void addParticipant(String a) {
			participant.add(a);
		}
			
		public void dropParticipant(String a) {
			participant.remove(a);
		}
			
		String getName() {
			return name;
		}
			
		String getCat() {
			return category;
		}
		
		char getType() {
			return type;
		}
		String getVenue() {
			return venue;
		}
		
	
		ZonedDateTime getStartDate() {
			String GSD = startDateTime.getMonth()+"/"+startDateTime.getDayOfMonth()+"/"+startDateTime.getYear(); //incase we need to call it
			System.out.print(startDateTime.getMonth()+"/"+startDateTime.getDayOfMonth()+"/"+startDateTime.getYear()+" ");
			return startDateTime;
			}

	
		ZonedDateTime getEndDate() {
			String GED = endDateTime.getMonth()+"/"+endDateTime.getDayOfMonth()+"/"+endDateTime.getYear(); //incase we need to call it
			System.out.print(endDateTime.getMonth()+"/"+endDateTime.getDayOfMonth()+"/"+endDateTime.getYear()+" ");
			return endDateTime;
		}
		
	
		ZonedDateTime getStartDateTime() {
			System.out.print(startDateTime.getHour()+":"+startDateTime.getMinute()+" "+startDateTime.getZone()+" ");
			return startDateTime;
		}
		
		
		ZonedDateTime getEndDateTime() {
			System.out.print(endDateTime.getHour()+":"+endDateTime.getMinute()+" "+endDateTime.getZone()+" ");
			return startDateTime;
		}
		

		
		float getPrice() {
			return price;
		}
			
		int getSeats() {
			return numSeats;
		}
		
		
		ArrayList<String> getParticipants() {
			return participant;
		}
		

	public static void main(String[] args) throws InputException {



		//String name, String category, char type, String venue, int startDay, int startMonth, int startHr, int startMin,
		// int endDay, int endMonth, int endHr, int endMin, int year, String zone, float price, int seats)
		
		Event e1= new Event("testevent", "diving", 'E', "Loca", 3, 5, 14, 45, 17, 6, 2, 15, 2008,
				"EDT",122.22f,100); //works
		
		
		e1.addParticipant("gee");
		e1.addParticipant("eee");
		e1.addParticipant("ree");
		
		System.out.println();
		System.out.print("Start Date andTime: ");
		e1.getStartDate();
		e1.getStartDateTime();
		
		System.out.println();
		System.out.print("End Date and Time: ");
		e1.getEndDate();
		e1.getEndDateTime();
		
		System.out.println();
		System.out.println(e1.getParticipants());
		
		System.out.println("Name of Event: "+e1.getName());
		System.out.println("Type: "+e1.getType());
		System.out.println("Location at:  "+e1.getVenue());
		System.out.println("Price: "+e1.getPrice());
		System.out.println("Seats Remaining: "+e1.getSeats());
		e1.decSeats();
		System.out.println("Seats Remaining!! : "+e1.getSeats());
		
		
	}

}


	


