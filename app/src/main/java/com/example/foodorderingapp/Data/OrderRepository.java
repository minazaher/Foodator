package com.example.foodorderingapp.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodorderingapp.Model.Order;
import com.example.foodorderingapp.Model.OrderUserCrossRef;
import com.example.foodorderingapp.Utilites.DishDatabase;

import java.util.List;

public class OrderRepository {

    private final OrderDao orderDao;
    private final LiveData<List<OrderUserCrossRef>> allOrders;

    public OrderRepository(Application application) {
        DishDatabase db = DishDatabase.getInstance(application);
        orderDao = db.orderDao();
        allOrders = orderDao.getAllOrders();
    }

    public LiveData<List<OrderUserCrossRef>> getAllOrders() {
        return allOrders;
    }

    public void addOrder(Order order) {
        DishDatabase.DatabaseExcutorService.execute(() -> orderDao.addOrder(order));
    }


}
