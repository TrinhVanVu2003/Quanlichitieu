package com.example.appquanlichitieu;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appquanlichitieu.fragment.AddFragment;
import com.example.appquanlichitieu.fragment.ChartFragment;
import com.example.appquanlichitieu.fragment.HomeFragment;
import com.example.appquanlichitieu.fragment.ViewPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         navigationView = findViewById(R.id.bottom_nav_bar);
         viewPager = findViewById(R.id.view_pager);
         ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
         viewPager.setAdapter(adapter);
         viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
             @Override
             public void onPageScrolled(int i, float v, int i1) {

             }

             @Override
             public void onPageSelected(int i) {
                 switch (i){
                     case 0:
                         navigationView.getMenu().findItem(R.id.home).setChecked(true);
                         break;
                     case 1:
                         navigationView.getMenu().findItem(R.id.chart).setChecked(true);
                         break;
                     case 2:
                         navigationView.getMenu().findItem(R.id.add).setChecked(true);
                         break;
                     case 3:
                         navigationView.getMenu().findItem(R.id.history).setChecked(true);
                         break;
                     case 4:
                         navigationView.getMenu().findItem(R.id.account).setChecked(true);
                         break;
                 }
             }

             @Override
             public void onPageScrollStateChanged(int i) {

             }
         });
         navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                 switch (menuItem.getItemId()){
                     case R.id.home:
                         viewPager.setCurrentItem(0);
                         break;
                     case R.id.chart:
                         viewPager.setCurrentItem(1);
                         break;
                     case R.id.add:
                         viewPager.setCurrentItem(2);
                         break;
                     case R.id.history:
                         viewPager.setCurrentItem(3);
                         break;
                     case R.id.account:
                         viewPager.setCurrentItem(4);
                         break;
                 }
                 return false;
             }
         });
    }

}