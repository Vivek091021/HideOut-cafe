package com.example.hideoutcafe;
import java.util.List;

public class ItemModel {
    private String image;
    private Long price;
    private int popularity;
    private String name;
    private List<String> ingredients;
    private boolean isHot;
   // private String categoryImage;


    // Empty constructor (required by Firebase for data retrieval)
    public ItemModel() {
    }

    // Constructor
    public ItemModel(String image, long price, int popularity, String name, List<String> ingredients, boolean isHot) {
        this.image = image;
        this.price = price;
        this.popularity = popularity;
        this.name = name;
        this.ingredients = ingredients;
        this.isHot = isHot;


    }

    // Getter methods
    public String getImage() {
        return image;
    }

    public long getPrice() {
        return price;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public boolean isHot() {
        return isHot;
    }

}
