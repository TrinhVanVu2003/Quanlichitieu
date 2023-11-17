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
    TextView tvSoTienHienCo;
    private static final int REQUEST_CODE_SELECT_CURRENCY = 1;
    private static final int REQUEST_CODE_CALCULATOR = 2;

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

        tvSoTienHienCo = findViewById(R.id.tvSotienhienco);
        tvSoTienHienCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddWalletActivity.this , CaculatorActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CALCULATOR);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_SELECT_CURRENCY) {
                handleCurrencyActivityResult(data);
            } else if (requestCode == REQUEST_CODE_CALCULATOR) {
                handleCalculatorActivityResult(data);
            }
        }
    }

    private void handleCurrencyActivityResult(Intent data) {
        if (data != null) {
            String currencyName = data.getStringExtra("currencyName");
            String currencyCode = data.getStringExtra("currencyCode");

            TextView tvCurrencyNameAdd = findViewById(R.id.tvChonDonViTien);
            TextView tvCurrencyCodeAdd = findViewById(R.id.tvCurrencyCodeAdd);

            tvCurrencyNameAdd.setText(currencyName);
            tvCurrencyCodeAdd.setText(currencyCode);
        }
    }

    private void handleCalculatorActivityResult(Intent data) {
        if (data != null) {
            String calculatedResult = data.getStringExtra("calculatedResult");

            // Cập nhật TextView trong AddWalletActivity
            TextView tvBalancee = findViewById(R.id.tvBalancee);
            tvBalancee.setText(calculatedResult);
        }
    }



}