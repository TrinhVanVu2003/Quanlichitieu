package com.example.appquanlichitieu.Transacion;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu.R;

import java.text.DecimalFormat;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {
    private List<Transacion> transactionList;

    public TransactionAdapter(List<Transacion> transactionList) {
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transacion, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        Transacion transaction = transactionList.get(position);

        // Hiển thị thông tin giao dịch trong ViewHolder
        holder.tvCategory.setText(transaction.getCategoryName());
        holder.tvBalance.setText(formatCurrency(transaction.getAmount()));
        holder.tvDate.setText(transaction.getDate());
//        holder.imgCate.setImageResource(transaction.get);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvBalance;
        TextView tvCategory;
        ImageView imgCate;


        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDateTrans);
            tvBalance = itemView.findViewById(R.id.tvBalanceChangeBot);
            tvCategory = itemView.findViewById(R.id.tvCateNameTrans);
            imgCate = itemView.findViewById(R.id.imgCateTrans);
        }
    }

    private String formatCurrency(double amount) {
        DecimalFormat decimalFormat;
            decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(amount);
    }
}
