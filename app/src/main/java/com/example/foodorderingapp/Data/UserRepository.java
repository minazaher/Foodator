package com.example.foodorderingapp.Data;

import android.app.Application;

import com.example.foodorderingapp.Model.User;
import com.example.foodorderingapp.Utilites.DishDatabase;

import java.util.concurrent.atomic.AtomicReference;

public class UserRepository {
    private final UserDao userDao;

    public UserRepository(Application application) {
        DishDatabase db = DishDatabase.getInstance(application);
        userDao = db.userDao();
    }

    public void addUser(User user) {
        DishDatabase.DatabaseExcutorService.execute(() -> userDao.addUser(user));
    }

    public User getUser(String Email, String Password) {
        AtomicReference<User> user = new AtomicReference<>(new User());
        DishDatabase.DatabaseExcutorService.execute(() ->
                user.set(userDao.getUser(Email, Password)));
        return user.get();
    }
}
