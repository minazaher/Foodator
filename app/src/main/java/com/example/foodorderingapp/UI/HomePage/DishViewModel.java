package com.example.foodorderingapp.UI.HomePage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.foodorderingapp.Data.DishRepository;
import com.example.foodorderingapp.Model.Dish;

import java.util.List;

public class DishViewModel extends AndroidViewModel {
    public static DishRepository repository;
    private final LiveData<List<Dish>> allDishes;

    public DishViewModel(@NonNull Application application) {
        super(application);

        repository = new DishRepository(application);
        allDishes = repository.getAllDishes();
    }

    public static void insertDish(Dish dish) {
        repository.insertDish(dish);
    }

    public LiveData<List<Dish>> getAllDishes() {
        return allDishes;
    }
}
