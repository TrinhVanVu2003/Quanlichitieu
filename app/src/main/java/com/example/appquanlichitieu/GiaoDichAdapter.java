package com.example.appquanlichitieu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GiaoDichAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    public static ArrayList<GiaoDich> giaoDichs;
    Context context;


    public GiaoDichAdapter(Context context, ArrayList<GiaoDich> giaoDichs) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.giaoDichs = giaoDichs;
    }

    @Override
    public int getCount() {
        return giaoDichs.size();
    }

    @Override
    public Object getItem(int position) {
        return giaoDichs.get(position);
    }

    @Override
    public long getItemId(int position) {

        return giaoDichs.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_lisviewgiaodich, parent, false);
        }

        // Lấy các view con từ convertView
        TextView tvType = convertView.findViewById(R.id.tvType);
        TextView tvAmount = convertView.findViewById(R.id.tvAmout);
        TextView tvcate = convertView.findViewById(R.id.tvcate);
        TextView tvnote = convertView.findViewById(R.id.tvnote);
        TextView  tvDate = convertView.findViewById(R.id.tvngay);

        // Lấy đối tượng GiaoDich ở vị trí position trong danh sách
        GiaoDich giaoDich = this.giaoDichs.get(position);

        // Hiển thị thông tin giao dịch lên các view con
        tvcate.setText(giaoDich.getCategory());
        tvDate.setText(giaoDich.getDate());
        tvAmount.setText(String.valueOf(giaoDich.getAmount()));
        tvnote.setText(giaoDich.getGhiChu());
        if (giaoDich.getIsKhoanThu() == 1){
            tvType.setText("Thu");
            tvType.setTextColor(Color.GREEN);
        } else {
            tvType.setText("Chi");
            tvType.setTextColor(Color.RED);
        }
        return convertView;
    }

}



