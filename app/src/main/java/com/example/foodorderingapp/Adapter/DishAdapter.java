package com.example.foodorderingapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Activity.HomePage;
import com.example.foodorderingapp.Activity.showDetalis;

import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.viewholder> {
    public List<Dish> dishes = new ArrayList<Dish>();
    Context context;

    public DishAdapter(Context context) {
        this.context = context;
    }

    public DishAdapter(List<Dish> dishes, Context context) {
        this.dishes = dishes;
        this.context = context;
    }

    public void setDishes(List<Dish> dishes) {

        this.dishes = dishes;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_dishes_card, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_dishName.setText(dishes.get(position).getName());
        holder.tv_dishPrice.setText(dishes.get(position).getPrice() + " L.E");
        holder.img_dish.setImageResource(R.drawable.pop_3);

        switch (dishes.get(position).getName()) {
            case "Pizza":
                holder.img_dish.setImageResource(R.drawable.pop_3);
                break;
            case "Burger":
                holder.img_dish.setImageResource(R.drawable.pop_2);
                break;
            default:
                holder.img_dish.setImageResource(R.drawable.cat_4);
        }

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), showDetalis.class);
                intent.putExtra("Dish", dishes.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    protected class viewholder extends RecyclerView.ViewHolder {
        TextView tv_dishName, tv_dishPrice, add;
        ImageView img_dish, delete;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            tv_dishName = itemView.findViewById(R.id.tv_dishName);
            tv_dishPrice = itemView.findViewById(R.id.tv_dishPrice);
            img_dish = itemView.findViewById(R.id.img_dish);
            add = itemView.findViewById(R.id.add);
            delete = itemView.findViewById(R.id.delete);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String dishName = tv_dishName.getText().toString();
                    Double dishPrice = 15.0;
                    Dish dish = new Dish(dishName, null, dishPrice);
                    HomePage.cart.add(dish);
                    HomePage.cartCount++;
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String dishName = tv_dishName.getText().toString();
                    for (Dish d : dishes) {
                        if (d.getName() == dishName)
                            dishes.remove(d);
                        notifyDataSetChanged();
                    }
                }
            });
        }
    }
}
