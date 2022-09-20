package com.example.foodorderingapp.Data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodorderingapp.Model.User;

@Dao
public interface UserDao {

    @Insert
    void addUser(User user);

    @Query("Select * from User where Email =(:Email) and Password = (:Password)")
    User getUser(String Email, String Password);

}
