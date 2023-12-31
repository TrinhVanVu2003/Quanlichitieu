package com.example.appquanlichitieu.Add;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appquanlichitieu.R;
import com.example.appquanlichitieu.fragment.ThangFragment;
import com.example.appquanlichitieu.fragment.TuanFragment;


public class ViewPagerAdapterBC extends FragmentPagerAdapter {


    public ViewPagerAdapterBC(FragmentManager fm) {
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
                return new TuanFragment();
            case 1:
                return new ThangFragment();
            default:
                return null;
        }
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Tuần";
            case 1:
                return "Tháng";
            default:
                return "Tuần";

        }
    }
}