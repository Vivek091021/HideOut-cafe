package com.example.hideoutcafe;

public class MenuModel {

    public MenuModel(String title, String categoryImage) {
        this.title = title;
        this.categoryImage = categoryImage;
    }

    private String title;
    private String categoryImage;

    public MenuModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setImgid(String categoryImage) {
        this.categoryImage = categoryImage;
    }




}
