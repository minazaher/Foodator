package com.example.foodorderingapp.Model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "dish_table")
public class Dish implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int dishID;
    private String Name;
    private String Description;
    private String pic;
    private Double price;


    public Dish() {

    }


    public Dish(String name, String description, String pic, Double price) {
        Name = name;
        Description = description;
        this.pic = pic;
        this.price = price;
    }

    public Dish(@NonNull String title, String pic, @NonNull Double price) {
        this.Name = title;
        this.pic = pic;
        this.price = price;
    }

    public int getDishID() {
        return dishID;
    }

    public void setDishID(int dishID) {
        this.dishID = dishID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "Dish Name " + Name + " || " +
                ", Price=" + price;
    }
}
