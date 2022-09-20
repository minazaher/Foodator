package com.example.foodorderingapp.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Adapter.CategoryAdapter;
import com.example.foodorderingapp.Model.Category;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.Utilites.ApplicationClass;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    TextView tv_profileName, tv_orderDate, tv_orderPrice;
    ImageView ProfilePic;
    RecyclerView Categories;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_profileName = findViewById(R.id.tv_profileName);
        tv_orderDate = findViewById(R.id.tv_orderDate);
        tv_orderPrice = findViewById(R.id.tv_orderPrice);
        ProfilePic = findViewById(R.id.ProfilePic);
        tv_profileName.setText(ApplicationClass.currentUser.getName());
        SignUpActivity.loadImageFromStorage(ApplicationClass.currentUser.getFilepath(), ProfilePic);
/*
        int LastOrder = ApplicationClass.currentUser.getOrderHistory().size() - 1;
        ArrayList<Order> orderHistory = ApplicationClass.currentUser.getOrderHistory();



        tv_orderDate.setText(orderHistory.get(LastOrder).getOrderDate().toString());
        tv_orderPrice.setText(orderHistory.get(LastOrder).getOrderDishes().get(LastOrder).getPrice().toString());
        recyclerViewCategory();
        */
    }


    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        Categories = findViewById(R.id.fav_recyclerView);
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