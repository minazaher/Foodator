package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.foodorderingapp.Adapter.CategoryAdapter;
import com.example.foodorderingapp.Model.Category;
import com.example.foodorderingapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    TextView tv_profileName;
    RecyclerView Categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_profileName = findViewById(R.id.tv_profileName);


        tv_profileName.setText(MainActivity.currentUser);
        recyclerViewCategory();
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