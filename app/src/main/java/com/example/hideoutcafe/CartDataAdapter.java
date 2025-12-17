package com.example.hideoutcafe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartDataAdapter extends RecyclerView.Adapter<CartDataAdapter.CartViewHolder> {
    private List<CartData> cartDataList;

    public CartDataAdapter(List<CartData> cartDataList) {
        this.cartDataList = cartDataList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_history, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartData cartData = cartDataList.get(position);
        holder.bind(cartData);
    }

    @Override
    public int getItemCount() {
        return cartDataList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView priceTextView;
        private TextView quantityTextView;
        private TextView dateTimeTextView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            dateTimeTextView = itemView.findViewById(R.id.dateTimeTextView);
        }

        public void bind(CartData cartData) {
            nameTextView.setText(cartData.getName());
            priceTextView.setText(cartData.getPrice());
            quantityTextView.setText(String.valueOf(cartData.getQuantity()));
            dateTimeTextView.setText(cartData.getDateTime());
        }
    }
}