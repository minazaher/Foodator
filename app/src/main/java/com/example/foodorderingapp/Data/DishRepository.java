package com.example.foodorderingapp.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.Utilites.DishDatabase;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DishRepository {
    private final DishDao dishDao;
    private final LiveData<List<Dish>> allDishes;

    public DishRepository(Application application) {
        DishDatabase db = DishDatabase.getInstance(application);

        dishDao = db.dishDao();
        allDishes = dishDao.getAll();
    }

    public LiveData<List<Dish>> getAllDishes() {
        return allDishes;
    }

    public void insertDish(Dish dish) {
        DishDatabase.DatabaseExcutorService.execute(() -> dishDao.insert(dish));
    }

    public Dish getDish(String Name) {
        AtomicReference<Dish> dish = new AtomicReference<>(new Dish());
        DishDatabase.DatabaseExcutorService.execute(() -> dish.set(dishDao.getDish(Name)));
        return dish.get();
    }
}
