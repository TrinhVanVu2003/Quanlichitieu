package com.example.appquanlichitieu.Category;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu.Category.ViewPagerAdapterChoseCate;
import com.example.appquanlichitieu.R;

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

        ViewPager viewPager = findViewById(R.id.viewPagerChoseCate);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        ViewPagerAdapterChoseCate pagerAdapter = new ViewPagerAdapterChoseCate(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        // Tạo custom view cho Tab 1
        View tabOne = getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tab1 = tabOne.findViewById(R.id.tabText);
        tab1.setText("Khoản thu");
        tab1.setTextColor(getResources().getColor(R.color.black)); // Màu chữ khi chưa được chọn
        tabLayout.getTabAt(0).setCustomView(tabOne);

        // Tạo custom view cho Tab 2
        View tabTwo = getLayoutInflater().inflate(R.layout.custom_tab, null);
        TextView tab2 = tabTwo.findViewById(R.id.tabText);
        tab2.setText("Khoản chi");
        tab2.setTextColor(getResources().getColor(R.color.black)); // Màu chữ khi chưa được chọn
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        // Thiết lập sự kiện để thay đổi màu sắc khi chọn tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TextView selectedTab = tab.getCustomView().findViewById(R.id.tabText);
                selectedTab.setTextColor(getResources().getColor(R.color.white)); // Màu chữ khi được chọn
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                TextView unselectedTab = tab.getCustomView().findViewById(R.id.tabText);
                unselectedTab.setTextColor(getResources().getColor(R.color.black)); // Màu chữ khi không được chọn
                tab.getCustomView().setBackgroundResource(0); // Xóa background khi không được chọn
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Được gọi khi tab được chọn lại
            }
        });
    }
}


