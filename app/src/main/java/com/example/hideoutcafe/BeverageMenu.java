package com.example.hideoutcafe;

import java.util.Map;

public class BeverageMenu {
    public static class Product {
        private String name;
        private int price;
        private boolean isHot;
        private int popularity;
        private String image;
        private String[] ingredients;
        private boolean isBestseller;

        // Default constructor for Firebase
        public Product() {}

        public Product(String name, int price, boolean isHot, int popularity, String image, String[] ingredients, boolean isBestseller) {
            this.name = name;
            this.price = price;
            this.isHot = isHot;
            this.popularity = popularity;
            this.image = image;
            this.ingredients = ingredients;
            this.isBestseller = isBestseller = true;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public boolean isHot() {
            return isHot;
        }

        public int getPopularity() {
            return popularity;
        }

        public String getImage() {
            return image;
        }

        public String[] getIngredients() {
            return ingredients;
        }
        public boolean isBestseller() {
            return isBestseller;
        }
    }

    public static class Category {
        private Map<String, Product> products;

        // Default constructor for Firebase
        public Category() {}

        public Category(Map<String, Product> products) {
            this.products = products;
        }

        public Map<String, Product> getProducts() {
            return products;
        }
    }

    private Map<String, Category> products;

    // Default constructor for Firebase
    public BeverageMenu() {}

    public BeverageMenu(Map<String, Category> products) {
        this.products = products;
    }

    public Map<String, Category> getProducts() {
        return products;
    }
}
