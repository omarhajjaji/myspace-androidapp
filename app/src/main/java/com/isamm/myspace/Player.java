package com.isamm.myspace;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class Player {
    private String name;
    private String lastName;
    private String login;
    private String password;
    private int age;
    private int score;
    private int niveau;
    private DatabaseManager mDatabaseManager;
    //TODO: make updateShared() method preferences with each update

    public Player(Context context, String name, String lastname,String login,String password, int age){
        //constructor for the database
        mDatabaseManager = new DatabaseManager(context);
        mDatabaseManager.insertNewPlayer(name,lastname,age,login,password);
        mDatabaseManager.close();

    }
    public Player(String name, String lastname,String login,String password, int age, int score, int niveau) {
        this.name = name;
        this.lastName = lastname;
        this.login = login;
        this.password = password;
        this.age = age;
        this.score = score;
        this.niveau = niveau;
    }

    public void updateSharedPref(Context context){
        Gson gson = new Gson();
        //transform player object to "Json" so that we can store it as a string in the sharedPref
        String json = gson.toJson(this);
        SharedPreferences.Editor prefsEditor = context.getSharedPreferences("com.isamm.myspace", Context.MODE_PRIVATE).edit();
        prefsEditor.putString("playerObject", json);
        prefsEditor.commit();
    }
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getScore() {
        return score;
    }
    public int getAge(){
        return age;
    }
    public void setNiveau(int niveau){
        this.niveau = niveau;
    }
    public int getNiveau() {
        return niveau;
    }

    public void addScore(int score){
        this.score+= score;
    }


    public String toString(){
        return"Name: "+name+", Lastname: "+lastName+", Login: "+login+
                ", Password: "+password+", Age: "+age+", Score: "+score+", Niveau: "+niveau;
    }
}
