package com.example.appquanlichitieu.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu.CaculatorActivity;
import com.example.appquanlichitieu.ChoseCategoryActivity;
import com.example.appquanlichitieu.R;
import com.example.appquanlichitieu.Wallet.WalletActivity;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment implements DatePickerDialog.OnDateSetListener{
    TextView tvChonCate,tvBalanceTranc , tvChonWallet, tvChonTime;
    View mView;
    private static final int REQUEST_CODE_CALCULATOR = 1;
    private static final int REQUEST_CODE_SELECT_CATEGORY = 2;
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
                startActivityForResult(intent, REQUEST_CODE_SELECT_CATEGORY);
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

        //hiển thị chọn thời gian
        tvChonTime = mView.findViewById(R.id.tvChoseTime);
        tvChonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePickerDialog();
            }
        });

        return mView;
    }
    //xử lí kết quả của Category
    private void handleCategoryActivityResult(Intent data) {
        if (data != null) {
            String CateName = data.getStringExtra("IncomeName");
            int incomeImg = data.getIntExtra("IncomeIMG", 0);

            TextView tvChonCate = mView.findViewById(R.id.tvChonCate);
            ImageView imgCate = mView.findViewById(R.id.imgCate);
            tvChonCate.setText(CateName);
            imgCate.setImageResource(incomeImg);
        }
    }
    //xử lí kết quả của Calculator
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
        }else if (requestCode == REQUEST_CODE_SELECT_CATEGORY) {
            handleCategoryActivityResult(data);
        }
    }

    private void ShowDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                AddFragment.this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Tháng trong DatePickerDialog được đánh số từ 0 đến 11, không cần cộng thêm 1
        String date = (month + 1) + "/" + dayOfMonth + "/" + year;
        tvChonTime.setText(date);
    }


}