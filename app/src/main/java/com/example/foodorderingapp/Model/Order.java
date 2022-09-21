package com.example.foodorderingapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.foodorderingapp.Utilites.ApplicationClass;

import java.util.ArrayList;

@Entity
public class Order {

    public int UserID;
    @PrimaryKey(autoGenerate = true)
    private int ID;
    private String dishes;
    private double price;


    public Order() {
        UserID = ApplicationClass.currentUser.getID();
    }

    public Order(ArrayList<Dish> mDishes, double price) {
        UserID = ApplicationClass.currentUser.getID();
        dishes = mDishes.toString();
        this.price = price;
    }

    public String getDishes() {
        return dishes;
    }

    public void setDishes(String dishes) {
        this.dishes = dishes;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    @Override
    public String toString() {
        return "Order{" +
                "ID=" + ID +
                '}';
    }

}
