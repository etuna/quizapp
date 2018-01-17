package com.tunahantuna.etuna.quizapp3;

import java.io.Serializable;

/**
 * Created by etuna on 7/20/17.
 */
public class User implements Serializable{


    //Variables----------------
    private int points ;
    public int id;
    public String username, email, password, name, surname;
    //-------------------------

    //Constructor--------------
    public User(String username,int points)
    {
        this.username= username;
        this.points = points;
    }
    //--------------------------



    public User(int id, String username, String email, String password, String name, String surname){
        this.id = id;
        this.username= username ;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }





    //Getters, Setters--------
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String newNick)
    {
        username = newNick;
    }
    public int getPoints()
    {
        return points;
    }
    public void setPoints(int point)
    {
        points = point;
    }
    //------------------------


}
