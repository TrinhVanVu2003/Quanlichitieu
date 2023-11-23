package com.example.appquanlichitieu.Wallet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appquanlichitieu.R;

import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.WalletViewHolder> {
    private Context mContext;
    private List<Wallet> mListWallet ;

    public WalletAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public void setData(List<Wallet> list){
        this.mListWallet = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public WalletViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_wallet,viewGroup,false);
        return new WalletViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletViewHolder walletViewHolder, int i) {
        Wallet wallet = mListWallet.get(i);
        if(wallet == null){
            return;
        }
        walletViewHolder.tvWalletName.setText(wallet.getWalletName());
        walletViewHolder.tvWalletBalance.setText(wallet.getBalance());
        walletViewHolder.tvWalletCurrencyCode.setText(wallet.getCurrencyCode());
    }

    @Override
    public int getItemCount() {
        if (mListWallet != null){
            return mListWallet.size();
        }
        return 0;
    }

    public class WalletViewHolder extends RecyclerView.ViewHolder{
        private TextView tvWalletName;
        private TextView tvWalletCurrencyCode;
        private TextView tvWalletBalance;


        public WalletViewHolder(@NonNull View itemView) {
            super(itemView);
            tvWalletName = itemView.findViewById(R.id.tvWalletName);
            tvWalletBalance = itemView.findViewById(R.id.tvWalletBalance);
            tvWalletCurrencyCode = itemView.findViewById(R.id.tvWalletCurrencyCode);
        }
    }
}
