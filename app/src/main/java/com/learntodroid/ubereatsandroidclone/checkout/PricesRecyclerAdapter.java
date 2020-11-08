package com.learntodroid.ubereatsandroidclone.checkout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.learntodroid.ubereatsandroidclone.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class PricesRecyclerAdapter extends RecyclerView.Adapter<PricesRecyclerAdapter.PriceItemViewHolder> {
    private List<PriceItem> priceItems;

    public PricesRecyclerAdapter() {
        this.priceItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public PriceItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_priceitem, parent, false);
        return new PriceItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceItemViewHolder holder, int position) {
        holder.bind(priceItems.get(position));
    }

    @Override
    public int getItemCount() {
        return priceItems.size();
    }

    public void setPriceItems(List<PriceItem> priceItems) {
        this.priceItems = priceItems;
        notifyDataSetChanged();
    }

    class PriceItemViewHolder extends RecyclerView.ViewHolder {
        private TextView label, price;

        public PriceItemViewHolder(@NonNull View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.item_priceitem_label);
            price = itemView.findViewById(R.id.item_priceitem_price);
        }

        public void bind(PriceItem priceItem) {
            label.setText(priceItem.getLabel());
            price.setText(NumberFormat.getCurrencyInstance().format(priceItem.getPrice()));
        }
    }
}
