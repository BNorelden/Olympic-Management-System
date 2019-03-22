class InputException extends Exception
{
	private String msg;
	
   public InputException(String msg)
   {
			this.msg = msg;
   }
	public String get()
   {
			return msg;
   }
     
   class TimeConflictException extends Exception
   {
	   private Event conflicting;
      private Event conflicted;
	   private String msg;
   
	   public TimeConflictException(Event conflicting, Event conflicted)
      {
	   		this.conflicting = conflicting;
	   		this.conflicted = conflicted;
	   		msg = ("Date and Times for " + this.conflicting + " is conflicting with " + this.conflicted + "! Please reschedule events.");
      }
	   Event getConflicting()
      {
	   		return conflicting;
	   }
	   
      Event getConflicted()
      {
	   		return conflicted;
      }
	   
      String get()
      {
	   		return msg;
      }
  }
  
   class EventUnavailableException extends Exception
   {  
   	private Event unavailable;
   	private String msg;
   	public EventUnavailableException(Event e)
      {
   			unavailable = e;
   			msg = ("The event, " + unavailable + " is out of seats! Please try again later.");
      }
   		Event getUnavailable()
         {
   			return unavailable;
         }
   
   		String get()
         {
   			return msg;
         }
   }
}
