package com.example.hideoutcafe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartData> cartDataList;

    public CartAdapter(List<CartData> cartDataList) {
        this.cartDataList = cartDataList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_in_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartData cartData = cartDataList.get(position);
        holder.itemNameTextView.setText(cartData.getName());
        holder.itemPriceTextView.setText(cartData.getPrice());
        holder.itemQuantityTextView.setText(String.valueOf(cartData.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return cartDataList.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView, itemPriceTextView, itemQuantityTextView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.textView1);
            itemPriceTextView = itemView.findViewById(R.id.textView3);
            itemQuantityTextView = itemView.findViewById(R.id.t2);
        }
    }
}
