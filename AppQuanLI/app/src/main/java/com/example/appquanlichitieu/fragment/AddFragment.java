package com.example.appquanlichitieu.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appquanlichitieu.Add.AddViewPagerAdapter;
import com.example.appquanlichitieu.R;
import com.example.appquanlichitieu.widget.CustomViewPager;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {
    TabLayout tabLayout;
    CustomViewPager viewPager;
    View mView;

    public AddFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_add, container, false);
        return mView;
    }
}