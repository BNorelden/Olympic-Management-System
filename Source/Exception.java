class InputException extends Exception {

    private static final long serialVersionUID = 1L;
    private String msg;

    InputException(String msg) {

        this.msg = msg;

    }

    public String get() {
        
        return msg;
    }

}

class TimeConflictException extends Exception {

    private static final long serialVersionUID = 1L;
    private Event conflicting, conflicted;
    private String msg;

    TimeConflictException(Event conflicting, Event conflicted) {

        this.conflicting = conflicting;
        this.conflicted = conflicted;
        msg = "Date and Times for " + this.conflicting.getName() + " is conflicting with " + this.conflicted.getName() + "! Please reschedule events.";

    }

    public Event getConflicting() {

        return conflicting;
    }

    public Event getConflicted() {

        return conflicted;
    }

    public String get() {

        return msg;
    }

}

class EventUnavailableException extends Exception {

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

    public String get() {

        return msg;
    }

}