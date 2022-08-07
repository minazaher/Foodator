package com.example.foodorderingapp.Adapter;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Activity.HomePage;
import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewholder> {
    private final ArrayList<Dish> cartDishes = HomePage.cart;

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartAdapter.viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.dishName.setText(cartDishes.get(position).getName());
        holder.DishPrice.setText(String.valueOf(cartDishes.get(position).getPrice()));
        switch (cartDishes.get(position).getName()) {
            case "Pizza":
                holder.img_dish.setImageResource(R.drawable.pop_3);
                break;
            case "Burger":
                holder.img_dish.setImageResource(R.drawable.pop_2);
                break;
            default:
                holder.img_dish.setImageResource(R.drawable.cat_4);
        }
    }


    @Override
    public int getItemCount() {
        return HomePage.cart.size();
    }

    protected class viewholder extends RecyclerView.ViewHolder {
        TextView dishName, DishPrice, DishTotalPrice;
        ImageView img_dish;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.cartDishName);
            DishPrice = itemView.findViewById(R.id.cartDishPrice);
            img_dish = itemView.findViewById(R.id.cartDishImg);
            DishTotalPrice = itemView.findViewById(R.id.cartDishTotalPrice);
        }
    }
}
