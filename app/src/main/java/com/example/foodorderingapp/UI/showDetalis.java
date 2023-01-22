package com.example.foodorderingapp.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.foodorderingapp.UI.HomePage.HomePage;
import com.example.foodorderingapp.Data.DishRepository;
import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.Data.ApplicationClass;

import java.util.ArrayList;

public class showDetalis extends AppCompatActivity {
    private final ArrayList<Dish> myDishes = new ArrayList<>();
    TextView detail_dishName, detail_dishDescription, detail_dishPrice, itemCount, addToCart;
    CardView plusDish, minusDish;
    ImageView img_dish;
    private int itemCounter = 0;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detalis);

        detail_dishName = findViewById(R.id.detail_dishName);
        detail_dishDescription = findViewById(R.id.detail_dishDescription);
        detail_dishPrice = findViewById(R.id.detail_dishPrice);
        plusDish = findViewById(R.id.plusDish);
        minusDish = findViewById(R.id.minusDish);
        addToCart = findViewById(R.id.tv_addToCart);
        img_dish = findViewById(R.id.detail_img);
        itemCount = findViewById(R.id.itemCount);

        DishRepository repository = new DishRepository(this.getApplication());
        Dish dish = (Dish) getIntent().getSerializableExtra("Dish");

        detail_dishName.setText(dish.getName());
        detail_dishDescription.setText(dish.getDescription());
        detail_dishPrice.setText(dish.getPrice() + " LE");
        itemCount.setText("0");

        switch (dish.getName()) {
            case "Pizza":
                img_dish.setImageResource(R.drawable.pop_3);
                break;
            case "Burger":
                img_dish.setImageResource(R.drawable.pop_2);
                break;
            default:
                img_dish.setImageResource(R.drawable.cat_4);
        }


        plusDish.setOnClickListener(view -> {
            itemCounter++;
            itemCount.setText(String.valueOf(itemCounter));
            myDishes.add(dish);
        });


        minusDish.setOnClickListener(view -> {
            if (itemCounter == 0) {
                Toast.makeText(this, "You Have Reached The Minimum ", Toast.LENGTH_SHORT).show();
            } else {
                itemCounter--;
                itemCount.setText(String.valueOf(itemCounter));
            }

        });

        addToCart.setOnClickListener(view -> {
            ApplicationClass.cart.addAll(myDishes);
            Toast.makeText(this, "Added to Cart Succesfully", Toast.LENGTH_SHORT).show();
            ApplicationClass.cartCount += itemCounter;
            Intent intent = new Intent(showDetalis.this, HomePage.class);
            startActivity(intent);
        });

    }
}