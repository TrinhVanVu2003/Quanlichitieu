package com.example.appquanlichitieu.Currency;

import com.example.appquanlichitieu.RecycleViewCurrency.CurrencyAdapter;
import com.example.appquanlichitieu.Currency.Currency;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu.R;

import java.util.ArrayList;
import java.util.List;

public class CurrencyActivity extends AppCompatActivity implements CurrencyAdapter.OnCurrencyItemClickListener {
    private RecyclerView rcvCurrency;
    private CurrencyAdapter currencyAdapter;
    ImageView imgback ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        imgback = findViewById(R.id.imgBackCur);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rcvCurrency = findViewById(R.id.rcv_currency);
        currencyAdapter = new CurrencyAdapter(this);
        currencyAdapter.setOnCurrencyItemClickListener(this);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        rcvCurrency.setLayoutManager(linearLayoutManager);

        currencyAdapter.setData(getListCurrency());
        rcvCurrency.setAdapter(currencyAdapter);
    }


    @Override
    public void onCurrencyItemClick(Currency currency) {
        // Xử lý khi một đơn vị tiền tệ được chọn
        Intent intent = new Intent();
        intent.putExtra("currencyName", currency.getCurrencyName());
        intent.putExtra("currencyCode", currency.getCurrencyCode());
        setResult(RESULT_OK, intent);

        // Cập nhật TextView trong activity
        TextView currencyNameTextView = findViewById(R.id.tvCurrencyName);
        currencyNameTextView.setText(currency.getCurrencyName());

        TextView currencyCodeTextView = findViewById(R.id.tvCurrencyCode);
        currencyCodeTextView.setText(currency.getCurrencyCode());

        finish();
    }


    private List<Currency> getListCurrency(){
        List<Currency> list = new ArrayList<>();
        list.add(new Currency(1,R.drawable.img_vn,"Việt Nam Đồng","VNĐ"));
        list.add(new Currency(2, R.drawable.img_usa,"US Dollar","USD"));
        list.add(new Currency(3,R.drawable.img_china,"Nhân Dân Tệ","CNY"));
        return list;
    }
}

