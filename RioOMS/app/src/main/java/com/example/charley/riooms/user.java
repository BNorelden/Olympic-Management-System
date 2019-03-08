package com.example.charley.riooms;

public interface user {

    void setUserId(String Id);
    void setPassword(String pass);
    void setName(String name);
    void setPhoneNum(String phoneNum);
    void setAge(int age);
    void setTickets();//List<Tickets> ticketsList);

    String getUserId();
    //String getPassword();
    String getName();
    String getPhoneNum();
    int getAge();
    //todo create class tickets...
    //List<Tickets> getTickets();

}
