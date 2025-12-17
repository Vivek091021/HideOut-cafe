package com.example.hideoutcafe;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CartData {
    private String name;
    private String price;
    private int quantity;
    private String dateTime;

    public CartData() {
        // Default constructor required for calls to DataSnapshot.getValue(CartData.class)
    }

    public CartData(String name, String price, int quantity, String dateTime) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    // Utility method to get current date-time in a desired format
    public static String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return dateFormat.format(new Date());
    }
}
