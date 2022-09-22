package com.example.foodorderingapp.Activity;


import static com.example.foodorderingapp.R.layout.activity_home_page;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Adapter.CategoryAdapter;
import com.example.foodorderingapp.Adapter.DishAdapter;
import com.example.foodorderingapp.Data.DishRepository;
import com.example.foodorderingapp.Model.Category;
import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.Model.DishViewModel;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.Utilites.ApplicationClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    LinearLayout btn_profile;
    ConstraintLayout offers;
    TextView tv_Hi, tv_categories, tv_popular;
    RecyclerView Categories, Popular;
    EditText et_search;
    ImageView delete, user_img;
    private Bitmap bitmap;
    private DishViewModel dishViewModel;
    private FloatingActionButton fab_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_home_page);

        initializeUI();

        DishRepository dishRepository = new DishRepository(this.getApplication());


        // For Testing Purpose

        Dish dish = new Dish("Pizza", "Pepperoni, Italian Sausage, Mushrooms, Green peppers, and Anchovies.", null, 15.0);
        Dish dish1 = new Dish("Burger", "A huge single or triple burger with all the fixings, cheese, lettuce, tomato, onions and special sauce or mayonnaise!", null, 15.0);
        dishRepository.insertDish(dish1);
        dishRepository.insertDish(dish);


        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        tv_Hi.setText("Hi, " + ApplicationClass.currentUser.getName());
        SignUpActivity.loadImageFromStorage(ApplicationClass.currentUser.getFilepath(), user_img);


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

    private void initializeUI() {
        tv_Hi = findViewById(R.id.tv_Hi);
        tv_categories = findViewById(R.id.tv_categories);
        tv_popular = findViewById(R.id.tv_popular);
        offers = findViewById(R.id.OfferSection);
        delete = findViewById(R.id.delete);
        fab_cart = findViewById(R.id.fab_cart);
        btn_profile = findViewById(R.id.btn_profile);
        user_img = findViewById(R.id.home_userImage);
        et_search = findViewById(R.id.et_search);
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