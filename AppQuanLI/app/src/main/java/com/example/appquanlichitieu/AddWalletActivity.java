package com.example.appquanlichitieu;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu.Currency.CurrencyActivity;

public class AddWalletActivity extends AppCompatActivity {
    ImageView imgback;
    TextView tvChonDonViTien;
    private static final int REQUEST_CODE_SELECT_CURRENCY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);
        tvChonDonViTien =findViewById(R.id.tvChonDonViTien);
        tvChonDonViTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddWalletActivity.this, CurrencyActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_CURRENCY); // Sử dụng startActivityForResult()
            }
        });
        imgback = findViewById(R.id.imgBackadd);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SELECT_CURRENCY && resultCode == RESULT_OK) {
            if (data != null) {
                String currencyName = data.getStringExtra("currencyName");
                String currencyCode = data.getStringExtra("currencyCode");

                // Update TextViews in activity_add_wallet
                TextView tvCurrencyNameAdd = findViewById(R.id.tvChonDonViTien);
                TextView tvCurrencyCodeAdd = findViewById(R.id.tvCurrencyCodeAdd);

                tvCurrencyNameAdd.setText(currencyName);
                tvCurrencyCodeAdd.setText(currencyCode);
            }
        }
    }
}