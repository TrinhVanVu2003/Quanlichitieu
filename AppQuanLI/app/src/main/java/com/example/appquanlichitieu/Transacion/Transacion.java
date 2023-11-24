package com.example.appquanlichitieu.Transacion;

import com.example.appquanlichitieu.Category.Category;

public class Transacion {
    private int TransacionID;
    private String Date ;
    private double Amount;
    private String Note;
    private String TransCategoryType;
    private String CategoryName;
    private String WalletName;
    private int TransWalletID;
    private int CateID;
    private int UserID;
    public Transacion(int transacionID, String date, double amount, String note,String categoryName, String transCategoryType,String walletName, int transWalletID, int cateID, int userID) {
        TransacionID = transacionID;
        Date = date;
        Amount = amount;
        Note = note;
        CategoryName = categoryName;
        TransCategoryType = transCategoryType;
        WalletName = walletName;
        TransWalletID = transWalletID;
        CateID = cateID;
        UserID = userID;
    }

    public String getWalletName() {
        return WalletName;
    }

    public void setWalletName(String walletName) {
        WalletName = walletName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public int getTransacionID() {
        return TransacionID;
    }

    public void setTransacionID(int transacionID) {
        TransacionID = transacionID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getTransCategoryType() {
        return TransCategoryType;
    }

    public void setTransCategoryType(String transCategoryType) {
        TransCategoryType = transCategoryType;
    }

    public int getTransWalletID() {
        return TransWalletID;
    }

    public void setTransWalletID(int transWalletID) {
        TransWalletID = transWalletID;
    }

    public int getCateID() {
        return CateID;
    }

    public void setCateID(int cateID) {
        CateID = cateID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }
}
