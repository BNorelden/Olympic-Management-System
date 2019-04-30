package com.example.charley.riooms;

import java.util.ArrayList;

public class User implements Usable {

    protected String name;
    protected String phoneNum;
    protected int age;
    protected String email;
    protected ArrayList<Ticket> ticket;

    public User(String name, String phoneNum, int age, String email) throws InputException {

        setName(name);
        setPhoneNum(phoneNum);
        setAge(age);
        setEmail(email);

        ticket = new ArrayList<Ticket>();

    }

    @Override
    public void setName(String name) throws InputException {

        for(int i = 0; i < name.length(); i++) {
            char character = name.charAt(i);

            if(character != ' ' && (character < 'A' || (character > 'Z' && character < 'a' || character > 'z')))
                throw new InputException("Names are not allowed to contain numbers or special characters!");

        }
        this.name = name;

    }

    @Override
    public void setPhoneNum(String phoneNum) throws InputException {

        if(phoneNum.length() != 10)
            throw new InputException("Phone numbers must contain 10 digits!");

        for(int i = 0; i < phoneNum.length(); i++) {
            char digit = phoneNum.charAt(i);

            if(digit < '0' || digit > '9')
                throw new InputException("Invalid phone number");
        }
        this.phoneNum = phoneNum;

    }

    @Override
    public void setAge(int age) throws InputException {

        if(age <= 0)
            throw new InputException("Age must be greater than zero!");
        this.age = age;

    }

    @Override
    public void setEmail(String email) throws InputException {

        if(!email.matches("(.*)@(.*).com") && !email.matches("(.*)@(.*).net") && !email.matches("(.*)@(.*).org") && 
            !email.matches("(.*)@(.*).edu") && !email.matches("(.*)@(.*).gov"))
            throw new InputException("Invalid email format!");
        this.email = email;

    }

    @Override
    public String getName() {

        return name;
    }

    @Override
    public String getPhoneNum() {

        return phoneNum;
    }

    @Override
    public int getAge() {

        return age;
    }

    @Override
    public String getEmail() {

        return email;
    }

    @Override
    public ArrayList<Ticket> getTickets() {

        return ticket;
    }

    @Override
    public void buyTickets(Event e) throws EventUnavailableException {

        if(!e.isAvailable())
            throw new EventUnavailableException(e);

        /*Payment Activity*/
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Payment Processed!");
        System.out.println("-----------------------------------------------------------------------------------------------");
        /*Payment Activity*/

        Ticket tic = new Ticket(e);
        ticket.add(tic);
        tic.displayTicket();

    }

    @Override
    public void cancelTicket(Ticket t){

        ticket.remove(t);
        t.destroy();

    }
}