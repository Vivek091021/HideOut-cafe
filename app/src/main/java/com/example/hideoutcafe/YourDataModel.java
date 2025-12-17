package com.example.hideoutcafe;

public class YourDataModel {
    private int id;
    private String name;
    private String price;
    private int quantity;
    private String ingredients;
    private String image;
    private String date;


    public YourDataModel(int id, String name, String price, int quantity, String ingredients, String image, String date) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.ingredients = ingredients;
        this.image = image;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
