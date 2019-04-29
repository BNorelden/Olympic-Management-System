package Source;

public class EventSchedule extends Schedule {

    private static EventSchedule schedule = new EventSchedule();

    EventSchedule() {

        super();

    }

    public static EventSchedule getInstance() {

        return schedule;
    }

    public void notifySecurity(Event ceremony) {

        //Inform security of award ceremony scheduled
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println("Security has been notified of " + ceremony.getName());
        System.out.println("-------------------------------------------------------------------------------------------------");
        //Inform security of award ceremony scheduled

    }

    public void informUsers(String msg) {

        //Invoke activity that informs all users of events change
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(msg);
        System.out.println("-------------------------------------------------------------------------------------------------");
        //Invoke activity that informs all users of events change

    }

}
