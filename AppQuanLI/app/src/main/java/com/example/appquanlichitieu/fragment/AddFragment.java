package com.example.appquanlichitieu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appquanlichitieu.CaculatorActivity;
import com.example.appquanlichitieu.ChoseCategoryActivity;
import com.example.appquanlichitieu.R;
import com.example.appquanlichitieu.WalletActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
    TextView tvChonCate,tvBalanceTranc , tvChonWallet;
    View mView;

    private static final int REQUEST_CODE_CALCULATOR = 2;
    public AddFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_add, container, false);

        // sang trang chọn số dư
        tvBalanceTranc = mView.findViewById(R.id.tvBalanceAddTranc);
        tvBalanceTranc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaculatorActivity.class);
                startActivityForResult(intent, REQUEST_CODE_CALCULATOR);

            }
        });

        // sang trang chọn danh mục
        tvChonCate = mView.findViewById(R.id.tvChonCate);
        tvChonCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChoseCategoryActivity.class);
                startActivity(intent);
            }
        });

        // sang trang chọn wallet
        tvChonWallet = mView.findViewById(R.id.tvChonWallet);
        tvChonWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent);
            }
        });

        return mView;
    }

    private void handleCalculatorActivityResult(Intent data) {
        if (data != null) {
            String calculatedResult = data.getStringExtra("calculatedResult");

            TextView tvBalancee = mView.findViewById(R.id.tvBalanceAddTranc);
            tvBalancee.setText(calculatedResult);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CALCULATOR && resultCode ==   CaculatorActivity.RESULT_OK) {
            handleCalculatorActivityResult(data);
        }
    }
}