package com.example.appquanlichitieu.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appquanlichitieu.Add.ViewPagerAdapterBC;
import com.example.appquanlichitieu.LoginActivity;
import com.example.appquanlichitieu.MyDatabase;
import com.example.appquanlichitieu.R;
import com.example.appquanlichitieu.WalletActivity;
import com.example.appquanlichitieu.widget.CustomViewPager;


public class HomeFragment extends Fragment {
    TabLayout tabLayout;
    CustomViewPager viewPager;
    View mView;
    TextView tvXemvi;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_home, container, false);

        tvXemvi = view.findViewById(R.id.tvXemVi);
        tvXemvi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent);
            }
        });

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
}