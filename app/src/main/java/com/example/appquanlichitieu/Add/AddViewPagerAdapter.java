package com.example.appquanlichitieu.Add;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.appquanlichitieu.fragment.AccountFragment;
import com.example.appquanlichitieu.fragment.AddFragment;
import com.example.appquanlichitieu.fragment.ChartFragment;
import com.example.appquanlichitieu.fragment.HistoryFragment;
import com.example.appquanlichitieu.fragment.HomeFragment;

public class AddViewPagerAdapter extends FragmentStatePagerAdapter {


    public AddViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new KhoanThuFragment();
            case 1:
                return new KhoanChiFragment();
            default:
                return new KhoanThuFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Khoản Thu";
            case 1:
                return "Khoản Chi";
            default:
                return "Khoản Thu";

        }
    }
}
