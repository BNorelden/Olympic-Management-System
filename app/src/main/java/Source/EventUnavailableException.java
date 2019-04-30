package Source;

public class EventUnavailableException extends Exception {

    private static final long serialVersionUID = 1L;
    private Event unavailable;
    private String msg;

    EventUnavailableException(Event e) {

        unavailable = e;
        msg = "The event, " + unavailable.getName() + ", is sold out! Please try again later.";

    }

    public Event getUnavailable() {

        return unavailable;
    }

    public String toString() {

        return msg;
    }

}