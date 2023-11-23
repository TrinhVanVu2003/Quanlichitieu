package com.example.appquanlichitieu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appquanlichitieu.Add.ViewPagerAdapterBC;
import com.example.appquanlichitieu.MyDatabase;
import com.example.appquanlichitieu.R;
import com.example.appquanlichitieu.Wallet.WalletActivity;
import com.example.appquanlichitieu.widget.CustomViewPager;

import java.text.DecimalFormat;


public class HomeFragment extends Fragment  {
    TabLayout tabLayout;
    CustomViewPager viewPager;
    View mView;
    TextView tvXemvi, tvSodu;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_home, container, false);
        tvSodu = view.findViewById(R.id.tvSoDu);

        tvXemvi = view.findViewById(R.id.tvXemVi);
        tvXemvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent);
            }
        });
        MyDatabase database = new MyDatabase(getActivity());
        double TotalBalance = database.getTotalBalance();
        String currencyCode = "VND"; // Chọn loại tiền tệ mặc định (VND)
        String formattedTotalBalance = formatCurrency(TotalBalance, currencyCode);
        tvSodu.setText(formattedTotalBalance);

        // Tạo ViewPager
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        ViewPagerAdapterBC pagerAdapter = new ViewPagerAdapterBC(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        // Tạo TabLayout và liên kết với ViewPager
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        View tabOne = getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tab1 = tabOne.findViewById(R.id.tabText);
        tab1.setText("Tuần");
        tabLayout.getTabAt(0).setCustomView(tabOne);

        // Tạo custom view cho Tab 2
        View tabTwo = getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tab2 = tabTwo.findViewById(R.id.tabText);
        tab2.setText("Tháng");
        tabLayout.getTabAt(1).setCustomView(tabTwo);


//      tvKhoanChi = view.findViewById(R.id.tvChi);
//      tvKhoanThu = view.findViewById(R.id.tvThu);
//      tvSoDu = view.findViewById(R.id.tvSoDu);
//
//
//      database = new MyDatabase(getActivity());
//      int totalKhoanThu= database.getTotalKhoanThu();
//      int totalKhoanChi = database.getTotalKhoanChi();
//      int totalSoDu = totalKhoanThu - totalKhoanChi;
//
//      tvSoDu.setText(String.valueOf(totalSoDu));
//      tvKhoanThu.setText(String.valueOf(totalKhoanThu));
//      tvKhoanChi.setText(String.valueOf(totalKhoanChi));

      return view;
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
    // chọn thời gian

}