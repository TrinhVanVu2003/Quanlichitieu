package com.example.appquanlichitieu.Wallet;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlichitieu.CaculatorActivity;
import com.example.appquanlichitieu.Currency.CurrencyActivity;
import com.example.appquanlichitieu.MyDatabase;
import com.example.appquanlichitieu.R;
import com.example.appquanlichitieu.UserManager;

public class AddWalletActivity extends AppCompatActivity {
    ImageView imgback;
    TextView tvChonDonViTien;
    EditText edtWalletName;
    TextView tvCurrencyCode;
    TextView tvBalance;
    TextView tvSoTienHienCo;
    TextView tvLuu;
    private UserManager userManager;

    private static int id = 1;

    private static final int REQUEST_CODE_SELECT_CURRENCY = 1;
    private static final int REQUEST_CODE_CALCULATOR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wallet);
        userManager = new UserManager(this);

        MyDatabase database = new MyDatabase();
        edtWalletName = findViewById(R.id.edtWalletName);
        tvChonDonViTien = findViewById(R.id.tvChonDonViTien);
        tvBalance = findViewById(R.id.tvBalanceAdd);
        tvCurrencyCode = findViewById(R.id.tvCurrencyCodeAdd);


        //Vào layout đơn vị tiền
        tvChonDonViTien =findViewById(R.id.tvChonDonViTien);
        tvChonDonViTien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddWalletActivity.this, CurrencyActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_CURRENCY); // Sử dụng startActivityForResult()
            }
        });

        // trở về layout trước đó
        imgback = findViewById(R.id.imgBackadd);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Vào layout nhập số tiền
        tvSoTienHienCo = findViewById(R.id.tvSotienhienco);
        tvSoTienHienCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddWalletActivity.this, CaculatorActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CALCULATOR);
            }
        });
        // Inside onCreate method
        tvLuu = findViewById(R.id.tvLuu);
        tvLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (areAllFieldsFilled()) {
                    Wallet wallet = getDataFromAddWallet();

                    MyDatabase database = new MyDatabase(AddWalletActivity.this);

                    database.addWalletToDatabase(wallet);
                    finish();
                } else {
                    Toast.makeText(AddWalletActivity.this, "Điền đầy đủ thông tin đi bro", Toast.LENGTH_SHORT).show();
                }
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
            TextView tvBalancee = findViewById(R.id.tvBalanceAdd);
            tvBalancee.setText(calculatedResult);
        }
    }

    private Wallet getDataFromAddWallet() {
        String walletName = edtWalletName.getText().toString().trim();
        String currency = tvChonDonViTien.getText().toString().trim();
        String currencyCode = tvCurrencyCode.getText().toString().trim();

        String balanceString = tvBalance.getText().toString().trim();
        balanceString = balanceString.replace(",", "");

        // Kiểm tra xem balanceString có thể chuyển đổi thành double không
        double balance = 0.0;
        try {
            balance = Double.parseDouble(balanceString);
        } catch (NumberFormatException e) {
            // Xử lý khi có lỗi chuyển đổi
            e.printStackTrace();
            // Hiển thị thông báo lỗi hoặc thực hiện các xử lý khác
        }

        int userID = userManager.getCurrentUserID();
        //id sẽ tăng lên khi thêm ví mới
        id++;
        return new Wallet(id, walletName, currency, currencyCode, balance, userID);
    }



    private boolean areAllFieldsFilled() {
        String walletName = edtWalletName.getText().toString().trim();
        String currency = tvChonDonViTien.getText().toString().trim();
        String balance = tvBalance.getText().toString().trim();

        return !walletName.isEmpty() && !currency.isEmpty() && !balance.isEmpty();
    }

}