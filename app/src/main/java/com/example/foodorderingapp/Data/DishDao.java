package com.example.foodorderingapp.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodorderingapp.Model.Dish;

import java.util.List;

@Dao
public interface DishDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Dish dish);

    @Query("Delete from dish_table")
    void DeleteAll();

    @Query("Select * From dish_table")
    LiveData<List<Dish>> getAll();

    @Query("Select * from dish_table where  Name =(:Name) ")
    Dish getDish(String Name);

}

