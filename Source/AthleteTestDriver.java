import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class AthleteTestDriver {

    public static void main(String[] args) throws InputException, TimeConflictException, EventUnavailableException {

        Scanner scan = new Scanner(System.in);

        Event canoeEvent = new Event("Canoe Double Men", "Canoe Slalom", 'E', "Whitewater Stadium", 19, 4, 16, 40, 19, 4, 18, 40, 2016, "BRT", 30.99f, 50);
        Event judoAward = new Event("Judo Awards Women", "", 'C', "Carioca Arena 2", 5, 5, 15, 0, 5, 5, 16, 30, 2016, "BRT", 0f, 30);
        Event billSession = new Event("Bill's Autographs", "", 'S', "Olympic Stadium", 13, 6, 12, 45, 13, 6, 14, 0, 2016, "BRT", 0f, 999);
        Event naNa = new Event("Blah Blah", "Blah Cat", 'E', "Blah Venue", 1, 1, 0, 0, 1, 1, 2, 0, 2016, "BRT", 0f, 0);

        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Phone: ");
        String phone = scan.nextLine();
        System.out.print("Age: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.print("Country: ");
        String country = scan.nextLine();
        System.out.print("Gender: ");
        char gender = scan.next().charAt(0);

        ArrayList<String> sports = new ArrayList<String>();
        sports.add("Canoeing");
        sports.add("Biking");

        Athlete athlete = new Athlete(name, phone, age, email, country, gender, sports);

        char cont = 'y';
        int fun = 0;
        int choice = 0;
        while(cont == 'y') {
            System.out.println("Welcome Athlete " + athlete.getName() + " | Phone " + athlete.getPhoneNum() + " | Age " + athlete.getAge() + " | Email " + athlete.getEmail() +
                " | Country " + athlete.getCountry() + " | Gender " + athlete.getGender() + " | Sports " + athlete.getSports());
            System.out.println("Options");
            System.out.println("-----------");
            System.out.println("1 = buyTicket");
            System.out.println("2 = cancelTicket");
            System.out.println("3 = viewTickets");
            System.out.println("4 = checkSchedule");
            System.out.println("5 = scheduleAutograph");
            System.out.println("6 = updateAutograph");
            System.out.println("7 = cancelAutograph");
            System.out.println("8 = requestFreeTicket");
            System.out.print('\n');
            System.out.print("Function: ");
            fun = scan.nextInt();
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
                    athlete.buyTickets(canoeEvent);
                else if(choice == 2)
                    athlete.buyTickets(judoAward);
                else if(choice == 3)
                    athlete.buyTickets(billSession);
                break;
            case 2:
                System.out.println("-----------");
                System.out.print("Ticket Index: ");
                choice = scan.nextInt();
                athlete.cancelTicket(athlete.getTickets().get(choice));
                break;
            case 3:
                ArrayList<Ticket> tickets = athlete.getTickets();
                for(int i = 0; i < tickets.size(); i++) {
                    tickets.get(i).displayTicket();
                }
                break;
            case 4:
                ArrayList<Event> e = athlete.checkSchedule();
                EventSchedule schedule = EventSchedule.getInstance();
                ArrayList<Event> s = schedule.getEvents();
                for(int i = 0; i < e.size(); i++) {
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("AthleteSchedule: " + e.get(i).getName() + " | " + e.get(i).getCat() + " | " + e.get(i).getType() + " | " + e.get(i).getVenue() + " | " + e.get(i).getStartDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm z")) + " | " + e.get(i).getEndDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm z")) + " | " + e.get(i).getSeats() + " | " + e.get(i).getPrice());
                    System.out.println("EventSchedule: " + s.get(i).getName() + " | " + s.get(i).getCat() + " | " + s.get(i).getType() + " | " + s.get(i).getVenue() + " | " + s.get(i).getStartDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm z")) + " | " + s.get(i).getEndDateTime().format(DateTimeFormatter.ofPattern("d/M/yyyy H:mm z")) + " | " + s.get(i).getSeats() + " | " + s.get(i).getPrice());
                    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
                break;
            case 5:
                athlete.scheduleAutograph("Julian's Autographs", "Carioca Arena 2", 21, 7, 23, 0, 22, 7, 0, 30, 2016, "BRT", 999);
                break;
            case 6:
                System.out.println("-----------");
                System.out.println("1 = updateAll");
                System.out.println("2 = updateDateTime");
                System.out.println("3 = updateDateOnly");
                System.out.println("4 = updateTimeOnly");
                System.out.print('\n');
                System.out.print("Choice: ");
                choice = scan.nextInt();
                if(choice == 1)
                    athlete.updateAutograph(athlete.checkSchedule().get(0), "Olympic Stadium", 25, 7, 0, 0, 26, 7, 1, 30, 2016, "BRT");
                else if(choice == 2)
                    athlete.updateAutograph(athlete.checkSchedule().get(0), 25, 7, 0, 0, 26, 7, 1, 30, 2016, "BRT");
                else if(choice == 3)
                    athlete.updateAutograph(athlete.checkSchedule().get(0), 25, 7, 26, 7, 2016);
                else if(choice == 4)
                    athlete.updateAutograph(athlete.checkSchedule().get(0), 0, 0, 1, 30, "BRT");
                break;
            case 7:
                athlete.cancelAutograph(athlete.checkSchedule().get(0));
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
                    athlete.requestFreeTicket(canoeEvent);
                else if(choice == 2)
                    athlete.requestFreeTicket(judoAward);
                else if(choice == 3)
                    athlete.requestFreeTicket(billSession);
                else if(choice == 4)
                    athlete.requestFreeTicket(naNa);
                break;
            }
            System.out.print("Continue? ");
            cont = scan.next().charAt(0);
        }

        scan.close();

    }

}