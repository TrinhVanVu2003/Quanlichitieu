package com.example.appquanlichitieu.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appquanlichitieu.LoginActivity;
import com.example.appquanlichitieu.MyDatabase;
import com.example.appquanlichitieu.R;


public class HomeFragment extends Fragment {
    TextView tvKhoanThu, tvKhoanChi, tvSoDu, tvUsername;
    MyDatabase database;


    public HomeFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_home, container, false);
      tvKhoanChi = view.findViewById(R.id.tvChi);
      tvKhoanThu = view.findViewById(R.id.tvThu);
      tvSoDu = view.findViewById(R.id.tvSoDu);


      database = new MyDatabase(getActivity());
      int totalKhoanThu= database.getTotalKhoanThu();
      int totalKhoanChi = database.getTotalKhoanChi();
      int totalSoDu = totalKhoanThu - totalKhoanChi;

      tvSoDu.setText(String.valueOf(totalSoDu));
      tvKhoanThu.setText(String.valueOf(totalKhoanThu));
      tvKhoanChi.setText(String.valueOf(totalKhoanChi));

      return view;
    }
}