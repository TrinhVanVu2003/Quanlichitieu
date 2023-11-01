package com.example.appquanlichitieu;

public class GiaoDich {
    private int id;
    private String category;
    private String GhiChu;
    private int amount;
    private int isKhoanThu; // nếu 1 thì là khoản thu, false là khoản chi
    private String date;

    public GiaoDich() {

    }

    public GiaoDich(int id, String category, String ghiChu, int amount, int isKhoanThu, String date) {
        this.id = id;
        this.category = category;
        GhiChu = ghiChu;
        this.amount = amount;
        this.isKhoanThu = isKhoanThu;
        this.date = date;
    }

    public int getIsKhoanThu() {
        return isKhoanThu;
    }

    public void setIsKhoanThu(int isKhoanThu) {
        this.isKhoanThu = isKhoanThu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
