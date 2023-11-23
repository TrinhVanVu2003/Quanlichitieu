package com.example.appquanlichitieu.Wallet;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu.MyDatabase;
import com.example.appquanlichitieu.R;

import java.text.DecimalFormat;

public class WalletActivity extends AppCompatActivity {
    ImageView imgBack ;
    TextView tvTotalBalance;
    FloatingActionButton btnAdd ;
    RecyclerView rcvWallet;
    MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        tvTotalBalance = findViewById(R.id.tvTotalBalance);
        imgBack = findViewById(R.id.imgBack);
        btnAdd = findViewById(R.id.btnAddVi);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WalletActivity.this , AddWalletActivity.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rcvWallet = findViewById(R.id.rcv_wallet);
        WalletAdapter walletAdapter = new WalletAdapter(this);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rcvWallet.setLayoutManager(linearLayoutManager);

        MyDatabase database = new MyDatabase(WalletActivity.this);
        walletAdapter.setData(database.getListWallet());
        rcvWallet.setAdapter(walletAdapter);

        database = new MyDatabase(this);
        double TotalBalance = database.getTotalBalance();
        String currencyCode = "VND"; // Chọn loại tiền tệ mặc định (VND)
        String formattedTotalBalance = formatCurrency(TotalBalance, currencyCode);
        tvTotalBalance.setText(formattedTotalBalance);


    }
    private String formatCurrency(double amount, String currencyCode) {
        DecimalFormat decimalFormat;

        if ("VND".equals(currencyCode)) {
            decimalFormat = new DecimalFormat("#,### VND");
        } else if ("USD".equals(currencyCode)) {
            decimalFormat = new DecimalFormat("#,### USD");
        } else if ("CNY".equals(currencyCode)) {
            decimalFormat = new DecimalFormat("#,### CNY");
        } else {
            // Nếu loại tiền tệ không xác định, sử dụng định dạng mặc định
            decimalFormat = new DecimalFormat("#,###");
        }

        return decimalFormat.format(amount);
    }

}