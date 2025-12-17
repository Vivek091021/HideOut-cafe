package com.example.hideoutcafe;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BeverageAdapter extends BaseAdapter {

    private Context context;
    private Map<String, BeverageMenu.Category> categories;

    public BeverageAdapter(Context context, Map<String, BeverageMenu.Category> categories) {
        this.context = context;
        this.categories = categories;
    }
    public void setCategories(Map<String, BeverageMenu.Category> categories) {
        this.categories = categories;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    @Override
    public int getCount() {
        // Return the total number of items in your dataset
        // You may need to adjust this based on your data structure
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        // Return the data item at the specified position
        // You may need to adjust this based on your data structure

        // Check if categories is not null and position is within bounds
        if (categories != null && position >= 0 && position < categories.size()) {
            List<BeverageMenu.Product> productList = (List<BeverageMenu.Product>) new ArrayList<>(categories.values()).get(position).getProducts().values();
            return productList.toArray(new BeverageMenu.Product[0]);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        // Return the ID of the data item at the specified position
        // You may need to adjust this based on your data structure
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout for each list item
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);

        // Get the current product
        BeverageMenu.Product product = (BeverageMenu.Product) getItem(position);

        // Bind data to the views
        ImageView itemImage = view.findViewById(R.id.item_image);
        TextView bestsellerText = view.findViewById(R.id.bestseller_text);
        TextView itemNameText = view.findViewById(R.id.item_name_text);
        TextView ingredientText = view.findViewById(R.id.ingredient_text);
        TextView priceText = view.findViewById(R.id.price_text);
        //Button addButton = view.findViewById(R.id.add_button);

        // Load image using Glide library (you need to add Glide to your dependencies)
        Glide.with(context).load(product.getImage()).into(itemImage);

        bestsellerText.setVisibility(product.isBestseller() ? View.VISIBLE : View.GONE);
        itemNameText.setText(product.getName());
        ingredientText.setText(getFormattedIngredients(product.getIngredients()));
        priceText.setText(String.valueOf(product.getPrice()));

        // Add any additional logic for the 'Add' button, if needed

        return view;
    }

    private String getFormattedIngredients(String[] ingredients) {
        // Format the array of ingredients into a comma-separated string
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ingredients.length; i++) {
            builder.append(ingredients[i]);
            if (i < ingredients.length - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
