package com.example.appquanlichitieu.Category;

public class Category {
    private int categoryID;
    private int categoryIMG;
    private String categoryName;
    private String categoryType;

    public Category(int categoryID, int categoryIMG, String categoryName, String categoryType) {
        this.categoryID = categoryID;
        this.categoryIMG = categoryIMG;
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getCategoryIMG() {
        return categoryIMG;
    }

    public void setCategoryIMG(int categoryIMG) {
        this.categoryIMG = categoryIMG;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }
}
