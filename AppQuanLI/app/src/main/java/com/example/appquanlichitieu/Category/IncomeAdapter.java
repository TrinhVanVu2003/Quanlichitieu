package com.example.appquanlichitieu.Category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu.R;

import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {

    private Context mContext;
    private List<Category> mListIncome;
    private OnCateIncomeOnClickListener onCateIncomeOnClickListener;

    public IncomeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Category> list) {
        this.mListIncome = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cate, viewGroup, false);
        return new IncomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder incomeViewHolder, int i) {
        Category category = mListIncome.get(i);
        if (category == null) {
            return;
        }
        incomeViewHolder.imgCate.setImageResource(category.getCategoryIMG());
        incomeViewHolder.tvCateName.setText(category.getCategoryName());

        incomeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCateIncomeOnClickListener != null) {
                    onCateIncomeOnClickListener.onCateIncomeItemClick(category);
                }
            }
        });
    }

    public interface OnCateIncomeOnClickListener {
        void onCateIncomeItemClick(Category category);
    }

    public void setOnCateIncomeOnClickListener(OnCateIncomeOnClickListener listener) {
        this.onCateIncomeOnClickListener = listener;
    }

    @Override
    public int getItemCount() {
        if (mListIncome != null) {
            return mListIncome.size();
        }
        return 0;
    }

    public class IncomeViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCate;
        private TextView tvCateName;

        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCate = itemView.findViewById(R.id.imgCate);
            tvCateName = itemView.findViewById(R.id.tvCateName);
        }
    }
}
