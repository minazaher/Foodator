package com.example.foodorderingapp.Model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String Email;
    private String Name;
    private String Password;
    private String filepath;
    @Embedded
    private ArrayList<Order> OrderHistory;


    public User() {
    }

    public User(String email, String name, String password, String filepath) {
        Email = email;
        Name = name;
        Password = password;
        this.filepath = filepath;
    }

    public User(String email, String name, String password) {
        Email = email;
        Name = name;
        Password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Order> getOrderHistory() {
        return OrderHistory;
    }

    public void setOrderHistory(ArrayList<Order> orderHistory) {
        OrderHistory = orderHistory;
    }
}
