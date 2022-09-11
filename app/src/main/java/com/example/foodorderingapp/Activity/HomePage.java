package com.example.foodorderingapp.Activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodorderingapp.Adapter.CategoryAdapter;
import com.example.foodorderingapp.Adapter.DishAdapter;
import com.example.foodorderingapp.Data.DishRepository;
import com.example.foodorderingapp.Model.Category;
import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.Model.DishViewModel;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.Utilites.DishDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    public static ArrayList<Dish> cart = new ArrayList<>();
    public static int cartCount = 0;
    public static Double Tax = 10.0, DeliveryService = 15.0;
    LinearLayout btn_profile;
    TextView tv_Hi;
    RecyclerView Categories, Popular;
    ImageView delete;
    private DishViewModel dishViewModel;
    private FloatingActionButton fab_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        tv_Hi = findViewById(R.id.tv_Hi);
        delete = findViewById(R.id.delete);
        fab_cart = findViewById(R.id.fab_cart);
        btn_profile = findViewById(R.id.btn_profile);
        DishRepository dishRepository = new DishRepository(this.getApplication());


        /* For Testing Purpose
        Dish dish = new Dish("Pizza", "Pepperoni, Italian Sausage, Mushrooms, Green peppers, and Anchovies.", null, 15.0);
        Dish dish1 = new Dish("Burger", "A huge single or triple burger with all the fixings, cheese, lettuce, tomato, onions and special sauce or mayonnaise!", null, 15.0);
        dishRepository.insertDish(dish1);
        dishRepository.insertDish(dish);
        */

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        tv_Hi.setText("Hi, " + MainActivity.currentUser);
        recyclerViewCategory();
        recyclerViewPopular();

        fab_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, CheckoutActivity.class);
                startActivity(intent);
            }
        });

    }

    private void recyclerViewPopular() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        Popular = findViewById(R.id.pop_recview);
        Popular.setLayoutManager(linearLayoutManager);

        DishAdapter dishAdapter = new DishAdapter(this);

        dishViewModel = ViewModelProviders.of(this).get(DishViewModel.class);
        dishViewModel.getAllDishes().observe(this,
                dishes -> dishAdapter.setDishes(dishes)
        );
        Popular.setAdapter(dishAdapter);
    }

    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        Categories = findViewById(R.id.recyclerView);
        Categories.setLayoutManager(linearLayoutManager);

        ArrayList<Category> category = new ArrayList<>();
        category.add(new Category("Pizza", "cat_1"));
        category.add(new Category("Burger", "cat_2"));
        category.add(new Category("HotDog", "cat_3"));
        category.add(new Category("Drink", "cat_4"));
        category.add(new Category("Donut", "cat_5"));

        CategoryAdapter categoryAdapter = new CategoryAdapter();
        categoryAdapter.categories = category;
        Categories.setAdapter(categoryAdapter);
    }


}