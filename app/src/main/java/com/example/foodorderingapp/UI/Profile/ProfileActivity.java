package com.example.foodorderingapp.UI.Profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.UI.HomePage.CategoryAdapter;
import com.example.foodorderingapp.UI.SignUpActivity;
import com.example.foodorderingapp.Data.OrderDao;
import com.example.foodorderingapp.Model.Category;
import com.example.foodorderingapp.Model.Order;
import com.example.foodorderingapp.R;
import com.example.foodorderingapp.Data.ApplicationClass;
import com.example.foodorderingapp.Data.DishDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    TextView tv_profileName, tv_orderDate, tv_orderPrice, btn_updateOrders;
    ImageView ProfilePic;
    RecyclerView Categories, Orders;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_profileName = findViewById(R.id.tv_profileName);
        ProfilePic = findViewById(R.id.ProfilePic);
        btn_updateOrders = findViewById(R.id.btn_updateOrders);
        tv_profileName.setText(ApplicationClass.currentUser.getName());


        SignUpActivity.loadImageFromStorage(ApplicationClass.currentUser.getFilepath(), ProfilePic);

        OrderDao dao = DishDatabase.instance.orderDao();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Order> orders = dao.getUserOrders(ApplicationClass.currentUser.getID());
                Order LastOrder = orders.get(orders.size() - 1);
            }
        }).start();

        recyclerViewCategory();
        new Thread(() -> {
            RecycleViewOrderHistory();
            btn_updateOrders.setOnClickListener(view -> Orders.getAdapter().notifyDataSetChanged());
        }).start();

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

    private void RecycleViewOrderHistory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        Orders = findViewById(R.id.orders_recylerview);
        Orders.setLayoutManager(linearLayoutManager);
        OrderAdapter orderAdapter = new OrderAdapter();
        Orders.setAdapter(orderAdapter);
    }
}