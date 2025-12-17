package com.example.hideoutcafe;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {

    private String category;


    public Product() {
        // Default constructor required for calls to DataSnapshot.getValue(Product.class)
    }

    public Product(String category) {
        this.category = category;

    }

    public String getCategory() {
        return category;
    }


}