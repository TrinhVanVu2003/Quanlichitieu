package com.example.appquanlichitieu.Wallet;

public class Wallet {
    private int walletID;
    private String walletCurrency ;
    private String walletName;
    private String currencyCode;
    private double balance;
    private int UserID;
    public Wallet(int walletID,String walletName,String walletCurrency, String currencyCode, Double balance, int UserID) {

        this.walletID = walletID;
        this.walletName = walletName;
        this.walletCurrency =walletCurrency;
        this.currencyCode = currencyCode;
        this.balance = balance;
        this.UserID = UserID;
    }

    public String getWalletCurrency() {
        return walletCurrency;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public void setWalletCurrency(String walletCurrency) {
        this.walletCurrency = walletCurrency;
    }

    public int getWalletID() {
        return walletID;
    }

    public void setWalletID(int walletID) {
        this.walletID = walletID;
    }

    public String getWalletName() {
        return walletName;
    }

    public void setWalletName(String walletName) {
        this.walletName = walletName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
