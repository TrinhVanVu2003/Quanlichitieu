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

public class ExpensiveAdapter extends RecyclerView.Adapter<ExpensiveAdapter.ExpensiveViewHolder> {

    private Context mContext;
    private List<Category> mListExpensive;
    private OnCateExpensiveOnClickListener onCateExpensiveOnClickListener;

    public ExpensiveAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Category> list) {
        this.mListExpensive = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExpensiveViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cate, viewGroup, false);
        return new ExpensiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensiveViewHolder ExpensiveViewHolder, int i) {
        Category category = mListExpensive.get(i);
        if (category == null) {
            return;
        }
        ExpensiveViewHolder.imgCate.setImageResource(category.getCategoryIMG());
        ExpensiveViewHolder.tvCateName.setText(category.getCategoryName());

        ExpensiveViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCateExpensiveOnClickListener != null) {
                    onCateExpensiveOnClickListener.onCateExpensiveItemClick(category);
                }
            }
        });
    }

    public interface OnCateExpensiveOnClickListener {
        void onCateExpensiveItemClick(Category category);
    }

    public void setOnCateExpensiveOnClickListener(OnCateExpensiveOnClickListener listener) {
        this.onCateExpensiveOnClickListener = listener;
    }

    @Override
    public int getItemCount() {
        if (mListExpensive != null) {
            return mListExpensive.size();
        }
        return 0;
    }

    public class ExpensiveViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgCate;
        private TextView tvCateName;

        public ExpensiveViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCate = itemView.findViewById(R.id.imgCate);
            tvCateName = itemView.findViewById(R.id.tvCateName);
        }
    }
}