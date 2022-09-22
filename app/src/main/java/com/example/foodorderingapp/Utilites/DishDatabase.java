package com.example.foodorderingapp.Utilites;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.foodorderingapp.Data.DishDao;
import com.example.foodorderingapp.Data.OrderDao;
import com.example.foodorderingapp.Data.UserDao;
import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.Model.Order;
import com.example.foodorderingapp.Model.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Dish.class, User.class, Order.class}, version = 9, exportSchema = false)
public abstract class DishDatabase extends RoomDatabase {

    public static final int NO_OF_THREADS = 6;
    public static ExecutorService DatabaseExcutorService = Executors.newFixedThreadPool(4);
    public static DishDatabase instance;
    public static RoomDatabase.Callback myCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            DatabaseExcutorService.execute(new Runnable() {
                @Override
                public void run() {
                    DishDao dishDao = instance.dishDao();
                    UserDao userDao = instance.userDao();
                    //   OrderDao orderDao = instance.orderDao();
                }
            });
        }
    };

    public static synchronized DishDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, DishDatabase.class, "DishDatabase").fallbackToDestructiveMigration().addCallback(myCallback).build();
        }
        return instance;
    }

    public abstract DishDao dishDao();

    public abstract UserDao userDao();

    public abstract OrderDao orderDao();


}
