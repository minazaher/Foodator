package com.example.foodorderingapp.Utilites;

import android.app.Application;

import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.Model.User;

import java.util.ArrayList;

public class ApplicationClass extends Application {
    public static User currentUser;
    public static ArrayList<Dish> cart = new ArrayList<>();
    public static int cartCount = 0;
    public static Double Tax = 10.0, DeliveryService = 15.0;
}
