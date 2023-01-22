package com.example.foodorderingapp.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.foodorderingapp.Data.ApplicationClass;

import java.util.ArrayList;

@Entity
public class Order {

    public int UserID;
    @PrimaryKey(autoGenerate = true)
    private int orderID;
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

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }


    @Override
    public String toString() {
        return "Order{" +
                "ID=" + orderID +
                '}';
    }

}
