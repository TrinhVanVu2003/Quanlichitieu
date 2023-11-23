package com.example.appquanlichitieu.Category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appquanlichitieu.R;

import java.util.ArrayList;
import java.util.List;


public class ExpensiveFragment extends Fragment {

    private RecyclerView rcvExpensive;
    private ExpensiveAdapter expensiveAdapter;

    public ExpensiveFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expensive, container, false);

        rcvExpensive = view.findViewById(R.id.rcv_khoanchi);
        expensiveAdapter = new ExpensiveAdapter(getActivity());
        expensiveAdapter.setOnCateExpensiveOnClickListener(this::onCateExpensiveItemClick);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvExpensive.setLayoutManager(linearLayoutManager);

        expensiveAdapter.setData(getListExpensiveCate());
        rcvExpensive.setAdapter(expensiveAdapter);
        return view;

    }
    public void onCateExpensiveItemClick(Category category){
        // Xử lý khi một khoản thu được chọn
        Intent resultIntent = new Intent();
        resultIntent.putExtra("IcomeName", category.getCategoryName());
        resultIntent.putExtra("IcomeIMG", category.getCategoryIMG());

        // Đặt kết quả và kết thúc Fragment
        getActivity().setResult(Activity.RESULT_OK, resultIntent);
        getActivity().finish();
    }

    private List<Category> getListExpensiveCate(){
        List<Category> list = new ArrayList<>();
        list.add(new Category(0,R.drawable.img_food,"Đồ ăn","Khoản chi"));
        list.add(new Category(0,R.drawable.img_sell,"Mua xe","Khoản chi"));
        return list;
    }
}