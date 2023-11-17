package com.example.appquanlichitieu;

public class Wallet {
    private String walletName;
    private String currencyCode;
    private double balance;

    public Wallet(String walletName, String currencyCode, double balance) {
        this.walletName = walletName;
        this.currencyCode = currencyCode;
        this.balance = balance;
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
