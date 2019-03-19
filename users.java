package com.example.charley.riooms;

import java.util.ArrayList;

public class users implements user{

    protected String name;
    protected String phoneNum;
    protected int age;
    protected String email;
    protected static int ticCount = 0;
    ArrayList<Ticket> ticket;

    public users(String name, String phoneNum, int age, String email){

        setName(name);
        setPhoneNum(phoneNum);
        setAge(age);
        setEmail(email);

    }

    @Override
    public void setName(String name) throws InputException{

        for(int i = 0; i < name.length(); i++){

            char character = name.charAt(i);

            if(character != ' ' && (character < 'A' || (character > 'Z' && character < 'a' || character > 'z'))){

                throw new InputException("Names are not allowed to contain numbers or special characters!");

            }

        }

        this.name = name;

    }

    @Override
    public void setPhoneNum(String phoneNum) throws InputException{

        if(phoneNum.length() != 10){

            throw new InputException("Phone numbers must contain 10 digits!");

        }

        for(int i = 0; i < phoneNum.length(); i++){

            char digit = phoneNum.charAt(i);

            if(digit < '0' || digit > '9'){

                throw new InputException("Invalid phone number");

            }

        }

        this.phoneNum = phoneNum;

    }

    @Override
    public void setAge(int age) throws InputException{

        if(age <= 0){

            throw new InputException("Age must be greater than zero!");

        }

        this.age = age;

    }

    @Override
    public void setEmail(String email) throws InputException{

        if(!email.matches("(.*)@(.*).com") && !email.matches("(.*)@(.*).net") && !email.matches("(.*)@(.*).org") && !email.matches("(.*)@(.*).edu") && !email.matches("(.*)@(.*).gov")){

            throw new InputException("Invalid email format!");

        }

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
    public ArrayList<Ticket> getTickets(){
        return ticket;
    }

    @Override
    public void buyTickets(Event e) throws EventUnavailableException{

        if(!e.isAvailable()){

            throw new EventUnavailableExcetion(e);

        }

        /*Payment Activity*/
        ticCount++;

        Ticket tic = new Ticket(ticCount, e);

        ticket.add(tic);

        //ticket.get(ticket.size() - 1).displayTicket();
        tic.displayTicket();

    }

    @Override
    public void cancelTicket(Ticket t){

        ticket.remove(t);

    }
}
