import java.util.ArrayList;
import java.util.Scanner;

public class UserTestDriver {

    public static void main(String[] args) throws InputException, TimeConflictException, EventUnavailableException {

        Scanner scan = new Scanner(System.in);

        Event canoeEvent = new Event("Canoe Double Men", "Canoe Slalom", 'E', "Whitewater Stadium", 19, 4, 16, 40, 19, 4, 18, 40, 2016, "BRT", 30.99f, 50);
        Event judoAward = new Event("Judo Awards Women", "", 'C', "Carioca Arena 2", 5, 5, 15, 0, 5, 5, 16, 30, 2016, "BRT", 0f, 30);
        Event billSession = new Event("Bill's Autographs", "", 'S', "Olympic Stadium", 13, 6, 12, 45, 13, 6, 14, 0, 2016, "BRT", 0f, 999);

        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Phone: ");
        String phone = scan.nextLine();
        System.out.print("Age: ");
        int age = scan.nextInt();
        scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();

        User user = new User(name, phone, age, email);

        char cont = 'y';
        int fun = 0;
        int choice = 0;
        while(cont == 'y') {
            System.out.println("Welcome " + user.getName() + " | Phone " + user.getPhoneNum() + " | Age " + user.getAge() + " | Email " + user.getEmail());
            System.out.println("Options");
            System.out.println("-----------");
            System.out.println("1 = buyTicket");
            System.out.println("2 = cancelTicket");
            System.out.println("3 = viewTickets");
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
                    user.buyTickets(canoeEvent);
                else if(choice == 2)
                    user.buyTickets(judoAward);
                else if(choice == 3)
                    user.buyTickets(billSession);
                break;
            case 2:
                System.out.println("-----------");
                System.out.print("Ticket Index: ");
                choice = scan.nextInt();
                user.cancelTicket(user.getTickets().get(choice));
                break;
            case 3:
                ArrayList<Ticket> tickets = user.getTickets();
                for(int i = 0; i < tickets.size(); i++) {
                    tickets.get(i).displayTicket();
                }
                break;
            }
            System.out.print("Continue? ");
            cont = scan.next().charAt(0);
        }

        scan.close();

    }

}