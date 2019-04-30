
package com.example.charley.riooms;

import com.google.firebase.firestore.Exclude;

public class usersTEST {

    private String Id;
    //todo private String email;
    private String pass;
    private String name;
    private String phoneNum;
    private int age;

    //todo
    //default constructor
    //check if get uses this.

    public usersTEST(){//no arg constructor needed
    }

    public usersTEST(String Id, String name, String phoneNum, int age){

        this.Id = Id;
        this.name = name;
        this.phoneNum = phoneNum;
        this.age = age;

    }

    //@Override
    public void setUserId(String Id) {

        this.Id = Id;

    }

   // @Override
    public void setPassword(String pass) {

        this.pass = pass;

    }

    //@Override
    public void setName(String name) {

        this.name = name;

    }

   // @Override
    public void setPhoneNum(String phoneNum) {

        this.phoneNum = phoneNum;

    }

    //@Override
    public void setAge(int age) {

        this.age = age;

    }

   // @Override
    public void setTickets() {

    }

  //  @Override
    //@Exclude to prevent access from firestore
    public String getUserId() {
        return Id;
    }

   // @Override
    public String getName() {
        return name;
    }

    //@Override
    public String getPhoneNum() {
        return phoneNum;
    }

   // @Override
    public int getAge() {
        return age;
    }
}

