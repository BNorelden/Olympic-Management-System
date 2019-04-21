import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class EmployeeTestDriver {

    public static void main(String[] args) throws InputException, TimeConflictException, EventUnavailableException {

        Scanner scan = new Scanner(System.in);

        EventSchedule schedule = EventSchedule.getInstance();

        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Phone: ");
        String phone = scan.nextLine();
        System.out.print("Age: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();

        Employee employee = new Employee(name, phone, age, email);

        char cont = 'y';
        int fun = 0;
        int choice = 0;
        while(cont == 'y') {
            System.out.println("Welcome Employee " + employee.getName() + " | Phone " + employee.getPhoneNum() + " | Age " + employee.getAge() + " | Email " + employee.getEmail());
            System.out.println("Options");
            System.out.println("-----------");
            System.out.println("1 = buyTicket");
            System.out.println("2 = cancelTicket");
            System.out.println("3 = viewTickets");
            System.out.println("4 = registerAthlete");
            System.out.println("5 = schedule");
            System.out.println("6 = update");
            System.out.println("7 = cancelEvent");
            System.out.println("8 = requestFreeTicket");
            System.out.print('\n');
            System.out.print("Function: ");
            fun = scan.nextInt();
            ArrayList<String> sport = new ArrayList<String>();
            sport.add("Hiking");
            sport.add("Volleyball");
            sport.add("Track");
            switch(fun) {
            case 1:
                System.out.println("-----------");
                System.out.println("1 = canoeEvent");
                System.out.println("2 = judoAward");
                System.out.println("3 = billSession");
                System.out.print('\n');
                System.out.print("Choice: ");
                choice = scan.nextInt();
                if(choice == 1)
                    employee.buyTickets(schedule.getEvents().get(0));
                else if(choice == 2)
                    employee.buyTickets(schedule.getEvents().get(1));
                else if(choice == 3)
                    employee.buyTickets(schedule.getEvents().get(2));
                break;
            case 2:
                System.out.println("-----------");
                System.out.print("Ticket Index: ");
                choice = scan.nextInt();
                employee.cancelTicket(employee.getTickets().get(choice));
                break;
            case 3:
                ArrayList<Ticket> tickets = employee.getTickets();
                for(int i = 0; i < tickets.size(); i++) {
                    tickets.get(i).displayTicket();
                }
                break;
            case 4:
                employee.registerAthlete("Bob Burger", "2018654345", 25, "burgerb@optimum.net", "Kuwait", "United States", 'M', sport);
                break;
            case 5:
                ArrayList<Athlete> athlete = new ArrayList<Athlete>();
                athlete.add(new Athlete("Bob Burger", "2018654345", 25, "burgerb@optimum.net", "United States", 'M', sport));
                athlete.add(new Athlete("Jade Little", "2014459988", 22, "littlej@mount.edu", "Germany", 'F', sport));
                athlete.add(new Athlete("Amber Miles", "2012226666", 23, "milesa@accer.gov", "France", 'F', sport));
                employee.schedule("Canoe Double Men", "Canoe Slalom", 'E', "Whitewater Stadium", 19, 4, 16, 40, 19, 4, 18, 40, 2016, "BRT", 30.99f, 50, athlete);
                employee.schedule("Judo Awards Women", "", 'C', "Carioca Arena 2", 5, 5, 15, 0, 5, 5, 16, 30, 2016, "BRT", 0f, 30, athlete);
                employee.schedule("Bill's Autographs", "", 'S', "Olympic Stadium", 13, 6, 12, 45, 13, 6, 14, 0, 2016, "BRT", 0f, 999, athlete);
                employee.schedule("Blah Blah", "Blah Cat", 'E', "Blah Venue", 1, 1, 0, 0, 1, 1, 2, 0, 2016, "BRT", 0f, 0, athlete);
                break;
            case 6:
                System.out.println("-----------");
                System.out.println("1 = updateAll");
                System.out.println("2 = updateDateTime");
                System.out.println("3 = updateDateOnly");
                System.out.println("4 = updateTimeOnly");
                System.out.println("5 = updateAthlete");
                System.out.print('\n');
                System.out.print("Choice: ");
                choice = scan.nextInt();
                if(choice == 1)
                    employee.update(schedule.getEvents().get(0), "Olympic Stadium", 1, 5, 17, 0, 1, 5, 18, 30, 2016, "BRT");
                else if(choice == 2)
                    employee.update(schedule.getEvents().get(0), 1, 5, 17, 0, 1, 5, 18, 30, 2016, "BRT");
                else if(choice == 3)
                    employee.update(schedule.getEvents().get(0), 1, 5, 1, 5, 2016);
                else if(choice == 4)
                    employee.update(schedule.getEvents().get(0), 17, 0, 18, 30, "BRT");
                else if(choice == 5)
                    employee.update(schedule.getEvents().get(0), schedule.getEvents().get(0).getParticipants().get(0), new Athlete("Alex Brown", "2016677789", 30, "browna4@chrome.org", "Spain", 'F', sport));
                break;
            case 7:
                employee.cancelEvent(schedule.getEvents().get(0));
                employee.cancelEvent(schedule.getEvents().get(0));
                employee.cancelEvent(schedule.getEvents().get(0));
                employee.cancelEvent(schedule.getEvents().get(0));
                break;
            case 8:
                System.out.println("-----------");
                System.out.println("1 = canoeEvent");
                System.out.println("2 = judoAward");
                System.out.println("3 = billSession");
                System.out.println("4 = naNa");
                System.out.print('\n');
                System.out.print("Choice: ");
                choice = scan.nextInt();
                if(choice == 1)
                    employee.requestFreeTicket(schedule.getEvents().get(0));
                else if(choice == 2)
                    employee.requestFreeTicket(schedule.getEvents().get(1));
                else if(choice == 3)
                    employee.requestFreeTicket(schedule.getEvents().get(2));
                else if(choice == 4)
                    employee.requestFreeTicket(schedule.getEvents().get(3));
                break;
            }
            System.out.print("Continue? ");
            cont = scan.next().charAt(0);
        }

        scan.close();

    }

}