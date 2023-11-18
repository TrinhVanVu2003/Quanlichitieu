package com.example.appquanlichitieu;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu.Add.ViewPagerAdapterBC;
import com.example.appquanlichitieu.Add.ViewPagerAdapterChoseCate;

public class ChoseCategoryActivity extends AppCompatActivity {
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_category);
        imgBack = findViewById(R.id.imgBackChoseCate);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
//    ViewPager viewPager = findViewById(R.id.viewPager);
//    ViewPagerAdapterChoseCate pagerAdapter = new ViewPagerAdapterChoseCate();
//        viewPager.set(pagerAdapter);
//
//    // Tạo TabLayout và liên kết với ViewPager
//    TabLayout tabLayout = view.findViewById(R.id.tabLayout);
//        tabLayout.setupWithViewPager(viewPager);
//
//    View tabOne = getLayoutInflater().inflate(R.layout.custom_tab, null);
//    TextView tab1 = tabOne.findViewById(R.id.tabText);
//        tab1.setText("Tuần");
//        tabLayout.getTabAt(0).setCustomView(tabOne);
//
//    // Tạo custom view cho Tab 2
//    View tabTwo = getLayoutInflater().inflate(R.layout.custom_tab, null);
//    TextView tab2 = tabTwo.findViewById(R.id.tabText);
//        tab2.setText("Tháng");
//        tabLayout.getTabAt(1).setCustomView(tabTwo);
}