import java.util.ArrayList;

public class Event
{
	private String name, category;
	private char type;
	private String venue;
	private int[] date = new int[3];
	private int[] time = new int[4];
	private float price;
	private int numSeats;
	private ArrayList participant = new ArrayList<Athlete>();
      
	public Event(String name, String category, char type, String venue, int month, int day, int year, 
		int startHour, int startMinute, int endHour, int endMinute, float price, int seats) throws InputException
   {
			setName(name);
			setCat(category);
			setType(type);
			setVenue(venue);
			setDate(month, day, year);
			setTime(startHour, startMinute, endHour, endMinute);
			setPrice(price);
			setSeats(seats);
   }
	
   void setName(String name) throws InputException
   {
		for(int i = 0; i < name.length(); i++)
      {
			char character = name.charAt(i);
			if(character != ' ' && (character < '0' || (character > '9' && character < 'A') ||
			(character > 'Z' && character < 'a') || character > 'z'))
         {
				throw new InputException("Event names are not allowed to contain special characters!");
         }
      }
      this.name = name;
   }
		void setCat(String category) throws InputException
      {
			for(int i = 0; i < category.length(); i++)
         {
				char character = category.charAt(i);
				if(character != ' ' && (character < 'A' || (character > 'Z' && character < 'a') || character > 'z'))
            {
					throw new InputException("Categories are not allowed to contain numbers or special characters!");
            }
         }
			this.category = category;
      }

		void setType(char type) throws InputException
      {
			if(type != 'E' && type != 'C' && type != 'S')
         {
				throw new InputException("Type must be either: competition event (E), award ceremony (C), or autograph session (S)!");
         }
			this.type = type;
      }
      
		void setVenue(String venue)
      {
			this.venue = venue;
      }

		void setDate(int month, int day, int year) throws InputException
      {
			if(month <= 0 || month > 12)
				throw new InputException("Month must be between 1 and 12!");
			else if(day <= 0 || day > 31)
				throw new InputException("Day must be between 1 and 31!");
			else if(year < 2005 || year > 2020)
				throw new InputException("Invalid Year!");
         
			date[0] = month;
			date[1] = day;
			date[2] = year;
      }
		void setTime(int startHour, int startMinute, int endHour, int endMinute) throws InputException
      {
			if(startHour < 0 || startHour > 23 || endHour < 0 || endHour > 23)
				throw new InputException("Hour must be between 0 and 23!");
			else if(startMinute < 0 || startMinute > 59 || endMinute < 0 || endMinute > 59)
				throw new InputException("Minute must be between 0 and 59!");
			time[0] = startHour;
			time[1] = startMinute;
			time[2] = endHour;
			time[3] = endMinute;
      }
		void setPrice(float price) throws InputException
      {
			if(price < 0)
				throw new InputException("Price cannot be a negative value!");
			this.price = price;
      }
      
		void setSeats(int seats) throws InputException
      {
			if(seats < 0)
				throw new InputException("Number of seats cannot be negative!");
			this.numSeats = seats;
		}
      
		void incSeats()
      {
			numSeats++;
      }
		void decSeats()
      {
			numSeats--;
      }
      
		Boolean isAvailable()
      {
			if(numSeats == 0)
				return false;
         else
			   return true;
      }
		void addParticipant(Athlete a)
      {
				participant.add(a);
      }
		void dropParticipant(Athlete a)
      {
				participant.remove(a);
      }
		String getName()
      {
			return name;
      }
		String getCat()
      {
			return category;
      }
		char getType()
      {
			return type;
      }
		String getVenue()
      {
			return venue;
      }
		int[] getDate()
      {
			return date;
      }
		int[] getTime()
      {
			return time;
      }
		float getPrice()
      {
			return price;
      }
		int getSeats()
      {
			return numSeats;
      }
		ArrayList getParticipants()
      {
			return participant;
      }
   }
