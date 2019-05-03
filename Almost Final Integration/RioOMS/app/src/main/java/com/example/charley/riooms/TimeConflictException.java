package com.example.charley.riooms;

public class TimeConflictException extends Exception {

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

    public String toString() {

        return msg;
    }

}