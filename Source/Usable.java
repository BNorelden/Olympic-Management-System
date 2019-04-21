import java.util.ArrayList;

public interface Usable {

    void setName(String name) throws InputException;
    void setPhoneNum(String phoneNum) throws InputException;
    void setAge(int age) throws InputException;
    void setEmail(String email) throws InputException;
    String getName();
    String getPhoneNum();
    int getAge();
    String getEmail();
    ArrayList<Ticket> getTickets();
    void buyTickets(Event e) throws EventUnavailableException;
    void cancelTicket(Ticket t);

}