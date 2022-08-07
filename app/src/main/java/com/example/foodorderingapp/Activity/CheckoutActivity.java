package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodorderingapp.Adapter.CartAdapter;
import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.R;

public class CheckoutActivity extends AppCompatActivity {
    TextView Tax, deliveryService, totalPrice, itemsTotalPrice;
    ImageView cartDishImg;
    double total_Price, items_TotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Tax = findViewById(R.id.Tax);
        deliveryService = findViewById(R.id.deliveryServices);
        totalPrice = findViewById(R.id.TotalPrice);
        itemsTotalPrice = findViewById(R.id.itemsTotalPrice);
        cartDishImg = findViewById(R.id.cartDishImg);


        for (Dish d : HomePage.cart) {
            items_TotalPrice += d.getPrice();
        }

        total_Price = items_TotalPrice + HomePage.Tax + HomePage.DeliveryService;

        Tax.setText(String.valueOf(HomePage.Tax));
        deliveryService.setText(String.valueOf(HomePage.DeliveryService));
        itemsTotalPrice.setText(String.valueOf(items_TotalPrice));
        totalPrice.setText(String.valueOf(total_Price));

        initRecView();
    }


    private void initRecView() {
        RecyclerView recyclerView = findViewById(R.id.cartRecView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(new CartAdapter());

    }
}