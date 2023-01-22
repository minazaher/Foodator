package com.example.foodorderingapp.Data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.foodorderingapp.Model.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void addOrder(Order order);


    @Transaction
    @Query("Select * From User ")
    LiveData<List<OrderUserCrossRef>> getAllOrders();


    @Transaction
    @Query("Select * From `order` where UserID = (:ID)")
    List<Order> getUserOrders(int ID);

}
