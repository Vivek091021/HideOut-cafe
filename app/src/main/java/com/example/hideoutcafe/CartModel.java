package com.example.hideoutcafe;

public class CartModel {
    private String name;
    private String ingredients;
    private String price;
    private String quantity;

    public CartModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public CartModel(String name, String ingredients, String price, String quantity) {
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.quantity = quantity;
    }
}
