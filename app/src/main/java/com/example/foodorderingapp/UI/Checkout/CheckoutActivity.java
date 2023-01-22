package com.example.foodorderingapp.UI.Checkout;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Data.OrderRepository;
import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.Model.Order;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.Data.ApplicationClass;

public class CheckoutActivity extends AppCompatActivity {
    TextView Tax, deliveryService, totalPrice, itemsTotalPrice;
    ImageView cartDishImg;
    AppCompatButton btn_checkout;
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
        btn_checkout = findViewById(R.id.btn_checkout);


        for (Dish d : ApplicationClass.cart) {
            items_TotalPrice += d.getPrice();
        }

        total_Price = items_TotalPrice + ApplicationClass.Tax + ApplicationClass.DeliveryService;

        Tax.setText(String.valueOf(ApplicationClass.Tax));
        deliveryService.setText(String.valueOf(ApplicationClass.DeliveryService));
        itemsTotalPrice.setText(String.valueOf(items_TotalPrice));
        totalPrice.setText(String.valueOf(total_Price));


        OrderRepository orderRepository = new OrderRepository(getApplication());

        btn_checkout.setOnClickListener(view -> {
            Order order = new Order(ApplicationClass.cart, total_Price);
            orderRepository.addOrder(order);
            runOnUiThread(() ->
                    Toast.makeText(CheckoutActivity.this, "Order Added!", Toast.LENGTH_SHORT).show());
        });

        initRecView();
    }


    private void initRecView() {
        RecyclerView recyclerView = findViewById(R.id.cartRecView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new CartAdapter());
    }
}