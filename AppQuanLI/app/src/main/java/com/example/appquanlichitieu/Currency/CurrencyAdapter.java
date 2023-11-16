package com.example.appquanlichitieu.RecycleViewCurrency;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlichitieu.Currency.Currency;
import com.example.appquanlichitieu.R;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {
    private OnCurrencyItemClickListener onCurrencyItemClickListener;

    private Context mContext;
    private List<Currency> mListCurrency ;

    public CurrencyAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Currency> list){
        this.mListCurrency = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_currency,viewGroup,false);
        return new CurrencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder currencyViewHolder, int i) {
        Currency currency = mListCurrency.get(i);
        if(currency == null){
            return;
        }
        currencyViewHolder.imgNation.setImageResource(currency.getCurrencyIMG());
        currencyViewHolder.tvCurrency.setText(currency.getCurrencyName());
        currencyViewHolder.tvCurrencyCode.setText(currency.getCurrencyCode());
        currencyViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCurrencyItemClickListener != null) {
                    onCurrencyItemClickListener.onCurrencyItemClick(currency);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListCurrency != null){
            return mListCurrency.size();
        }
        return 0;
    }
    public interface OnCurrencyItemClickListener {
        void onCurrencyItemClick(Currency currency);
    }

    public void setOnCurrencyItemClickListener(OnCurrencyItemClickListener listener) {
        this.onCurrencyItemClickListener = listener;
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgNation ;
        private TextView tvCurrency;
        private TextView tvCurrencyCode;
        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNation = itemView.findViewById(R.id.imgNation);
            tvCurrency = itemView.findViewById(R.id.tvCurrencyName);
            tvCurrencyCode = itemView.findViewById(R.id.tvCurrencyCode);
        }
    }
}