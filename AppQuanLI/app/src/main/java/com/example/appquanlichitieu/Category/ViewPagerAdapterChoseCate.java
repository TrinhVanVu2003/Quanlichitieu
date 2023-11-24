package com.example.appquanlichitieu.Category;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.appquanlichitieu.Category.ExpensiveFragment;
import com.example.appquanlichitieu.Category.IcomeFragment;


public class ViewPagerAdapterChoseCate extends FragmentPagerAdapter {


    public ViewPagerAdapterChoseCate(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }


    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new IcomeFragment(); // Tạo fragment cho Tab 1
            case 1:
                return new ExpensiveFragment(); // Tạo fragment cho Tab 2
            default:
                return null;
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Khoản thu";
            case 1:
                return "Khoản chi";
            default:
                return "Khoản thu";

        }
    }
}