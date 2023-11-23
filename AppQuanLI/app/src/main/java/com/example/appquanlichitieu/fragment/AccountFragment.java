package com.example.appquanlichitieu.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.appquanlichitieu.LoginActivity;
import com.example.appquanlichitieu.R;
import com.example.appquanlichitieu.Wallet.WalletActivity;

public class AccountFragment extends Fragment {
    Button btnDangxuat;
    TextView tvVicuatoi;

    // Khai báo các biến liên quan
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String PREF_USERNAME = "username";
    private static final String PREF_PASSWORD = "password";
    private static final String PREF_REMEMBER_ME = "rememberMe";

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        tvVicuatoi = view.findViewById(R.id.tvVicuatoi);
        btnDangxuat = view.findViewById(R.id.btnDangxuat);

        tvVicuatoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WalletActivity.class);
                startActivity(intent);
            }
        });

        btnDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xóa thông tin đăng nhập từ SharedPreferences
                SharedPreferences.Editor editor = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
                editor.remove(PREF_USERNAME);
                editor.remove(PREF_PASSWORD);
                editor.remove(PREF_REMEMBER_ME);
                editor.apply();

                // Chuyển về màn hình đăng nhập
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish(); // Kết thúc hoạt động chứa fragment để ngăn người dùng quay lại màn hình chính bằng nút Back
            }
        });
        return view;
    }
}
