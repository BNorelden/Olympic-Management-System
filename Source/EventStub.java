import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

class Event {

    String name;
    String category;
    char type;
    String venue;
    ZonedDateTime startDateTime;
    ZonedDateTime endDateTime;

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

    ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public String toString() {
        String start = startDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm z"));
        String end = endDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm z"));
        return name + "\n" + category + "\n" + type + "\n" + venue + "\n" + start + "\n" + end + "\n\n";
    }

}