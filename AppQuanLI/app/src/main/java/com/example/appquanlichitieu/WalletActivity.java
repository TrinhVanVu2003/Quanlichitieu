package com.example.appquanlichitieu;

import android.content.Intent;
import android.media.Image;
import android.service.controls.actions.FloatAction;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class WalletActivity extends AppCompatActivity {
    ImageView imgBack ;
    FloatingActionButton btnAdd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
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

    }
}