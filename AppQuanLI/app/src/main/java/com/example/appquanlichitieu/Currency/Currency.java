package com.example.appquanlichitieu.Currency;

public class Currency {
    private int currencyID;
    private int currencyIMG;
    private String currencyName;
    private String currencyCode;

    public Currency(int currencyID, int currencyIMG, String currencyName, String currencyCode) {
        this.currencyID = currencyID;
        this.currencyIMG = currencyIMG;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
    }

    public int getCurrencyID() {
        return currencyID;
    }

    public void setCurrencyID(int currencyID) {
        this.currencyID = currencyID;
    }

    public int getCurrencyIMG() {
        return currencyIMG;
    }

    public void setCurrencyIMG(int currencyIMG) {
        this.currencyIMG = currencyIMG;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
