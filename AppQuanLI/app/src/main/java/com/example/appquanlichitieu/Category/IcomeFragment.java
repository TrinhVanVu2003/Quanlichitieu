package com.example.appquanlichitieu.Category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appquanlichitieu.MyDatabase;
import com.example.appquanlichitieu.R;

import java.util.ArrayList;
import java.util.List;

public class IcomeFragment extends Fragment {

    private RecyclerView rcvIncome;
    private IncomeAdapter incomeAdapter;
    MyDatabase database;
    public IcomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        rcvIncome = view.findViewById(R.id.rcv_khoanthu);
        incomeAdapter = new IncomeAdapter(getActivity());
        incomeAdapter.setOnCateIncomeOnClickListener(this::onCateIncomeItemClick);

        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getActivity());
        rcvIncome.setLayoutManager(linearLayoutManager);

        incomeAdapter.setData(getListIncomeCate());
        rcvIncome.setAdapter(incomeAdapter);
        return view;
    }


    public void onCateIncomeItemClick(Category category){
        // Xử lý khi một khoản thu được chọn
        Intent resultIntent = new Intent();
        resultIntent.putExtra("selectedCategoryID", category.getCategoryID());
        resultIntent.putExtra("IncomeName", category.getCategoryName());
        resultIntent.putExtra("IncomeIMG", category.getCategoryIMG());
        resultIntent.putExtra("categoryType", category.getCategoryType());

        // Đặt kết quả và kết thúc Fragment
        getActivity().setResult(Activity.RESULT_OK, resultIntent);
        getActivity().finish();

    }

    private List<Category> getListIncomeCate() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1, R.drawable.img_salary, "Lương", "Khoản thu"));
        categories.add(new Category(2, R.drawable.img_sell, "Bán xe", "Khoản thu"));

        database = new MyDatabase(getActivity());

        // Log để kiểm tra dữ liệu trong categories
        for (Category category : categories) {
            Log.d("CategoryData", "Category ID: " + category.getCategoryID() +
                    ", Name: " + category.getCategoryName() +
                    ", Type: " + category.getCategoryType());
        }

        // Kiểm tra xem có lỗi gì khi thêm danh mục vào cơ sở dữ liệu không
        if (database != null) {
            database.addCategories(categories);
        } else {
            Log.e("DatabaseError", "Database is null");
        }

        return categories;
    }


}