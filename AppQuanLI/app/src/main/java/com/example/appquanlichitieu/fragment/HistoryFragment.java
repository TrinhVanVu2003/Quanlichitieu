package com.example.appquanlichitieu.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appquanlichitieu.GiaoDich;
import com.example.appquanlichitieu.GiaoDichAdapter;
import com.example.appquanlichitieu.MyDatabase;
import com.example.appquanlichitieu.R;
import com.example.appquanlichitieu.Transacion.Transacion;
import com.example.appquanlichitieu.Transacion.TransactionAdapter;

import java.util.ArrayList;
import java.util.List;


public class HistoryFragment extends Fragment {
    private List<Transacion> transactionList;
    private RecyclerView recyclerView;
    private TransactionAdapter adapter;

    public HistoryFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_history, container, false);

        MyDatabase database = new MyDatabase(getActivity());
        transactionList = database.getListTransactions(); // Giả sử có một phương thức để lấy danh sách giao dịch

        // Khởi tạo RecyclerView và Adapter
        recyclerView = v.findViewById(R.id.rvcTransacion);
        adapter = new TransactionAdapter(transactionList);

        // Thiết lập LayoutManager và Adapter cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        return v;
    }

}







