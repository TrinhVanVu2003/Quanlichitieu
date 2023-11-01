package com.example.appquanlichitieu.Add;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.appquanlichitieu.R;

import java.util.List;

public class DanhMucAdapter extends ArrayAdapter<DanhMuc> {

    private FragmentActivity mActivity;

    public DanhMucAdapter(@NonNull Context context, int resource, @NonNull List<DanhMuc> objects, FragmentActivity mActivity) {
        super(context, resource, objects);
        this.mActivity = mActivity;
    }

    @NonNull
      @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_selected, parent, false);
        TextView tvSelected = convertView.findViewById(R.id.tvSelected);
        DanhMuc danhMuc = this.getItem(position);
        if (danhMuc != null) {
            tvSelected.setText(danhMuc.get_tendanhmuc());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(mActivity).inflate(R.layout.item_danhmuc, parent, false);
        TextView tvDanhMuc = convertView.findViewById(R.id.tvDanhMuc);
        DanhMuc danhMuc = this.getItem(position);
        if (danhMuc != null) {
            tvDanhMuc.setText(danhMuc.get_tendanhmuc());
        }
        return convertView;
    }
}


