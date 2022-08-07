package com.example.foodorderingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Model.Category;
import com.example.foodorderingapp.Model.Dish;
import com.example.foodorderingapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewholder> {
    public ArrayList<Category> categories;

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_card, parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.categoryName.setText(categories.get(position).getTitle());

        switch (position) {
            case 0:
                holder.categoryPic.setImageResource(R.drawable.cat_1);
                break;
            case 1:
                holder.categoryPic.setImageResource(R.drawable.cat_2);
                break;
            case 2:
                holder.categoryPic.setImageResource(R.drawable.cat_3);
                break;
            case 3:
                holder.categoryPic.setImageResource(R.drawable.cat_4);
                break;
            case 4:
                holder.categoryPic.setImageResource(R.drawable.cat_5);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    protected class viewholder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryPic;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            categoryPic = itemView.findViewById(R.id.img_cat);
            categoryName = itemView.findViewById(R.id.tv_catName);
        }
    }
}
