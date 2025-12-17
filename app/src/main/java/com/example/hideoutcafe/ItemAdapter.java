package com.example.hideoutcafe;

import static androidx.core.content.ContextCompat.startActivity;

import static com.example.hideoutcafe.MainActivity.activeFragment;
import static com.example.hideoutcafe.MainActivity.tv1;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<ItemModel> itemList;
    private Context context;

    public ItemAdapter() {
    }

    public ItemAdapter(Context context, List<ItemModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel currentItem = itemList.get(position);

        // Set data to views
        // You can use a library like Picasso or Glide for efficient image loading from URLs
        // For simplicity, I'm assuming you have a drawable resource named 'ic_home' for the image
        //holder.itemImage.setImageResource(R.drawable.ic_home);

        // Customize based on your actual data
        holder.bestsellerText.setText("BestSeller");
        holder.itemNameText.setText(currentItem.getName());

        // Convert the list of ingredients to a string and set it to the TextView
        holder.ingredientText.setText(listToString(currentItem.getIngredients()));

        holder.priceText.setText("₹" + currentItem.getPrice());

        // Handle button click or any other events if needed
//        holder.addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Implement your logic when the "Add" button is clicked
//                // For example, you might want to add the item to a cart or perform some other action
//                Toast.makeText(context, "Item added to cart: " + currentItem.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, "Item added to cart: " + currentItem.getName(), Toast.LENGTH_SHORT).show();

                // Create a BottomSheetDialogFragment instance


                // Pass data to the BottomSheetDialogFragment
                Bundle bundle = new Bundle();
                bundle.putString("image", currentItem.getImage());
                bundle.putString("name", currentItem.getName());
                bundle.putString("price", "₹" + currentItem.getPrice());
                bundle.putString("popularity", String.valueOf(currentItem.getPopularity()));
                bundle.putString("ingredients", listToString(currentItem.getIngredients()));


                // Show the BottomSheetDialogFragment
                Home home = (Home) activeFragment;
                if (home != null) {
                    ItemDetailsBottomSheetFragment bottomSheetFragment = new ItemDetailsBottomSheetFragment();
                    bottomSheetFragment.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomBottomSheetDialogTheme);
                    //bottomSheetFragment.show(home.getChildFragmentManager(), bottomSheetFragment.getTag());

                    home.showBottomSheet(bundle);
                }

            }
        });

        Glide.with(context).load(currentItem.getImage()).into(holder.itemImage);


        //add button
        LinearLayout cart_tost;
        TextView tvPrice, tvItems;
        cart_tost = MainActivity.cart_tost;
        tvPrice = MainActivity.tv1;
        tvItems = MainActivity.tv2;

        DBHelper dbHelper = new DBHelper(context);
        holder.t2.setText(String.valueOf(dbHelper.getQuantityByName(currentItem.getName().toString())));

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.add.getText().toString().equals("Add")) {
                    dbHelper.DeleteData(currentItem.getName());
                    holder.add.setVisibility(View.GONE);
                    int qt =dbHelper.getQuantityByName(currentItem.getName().toString());
                    if (qt !=0){
                        dbHelper.UpdateData(currentItem.getName().toString(), 1);
                    }else{
                        dbHelper.InsertData(currentItem.getName().toString(), String.valueOf(currentItem.getPrice()), 1, currentItem.getIngredients().toString(), currentItem.getImage());
                    }
                    holder.inc.setVisibility(View.VISIBLE);
                    int i = Custom.inc(context,true,currentItem.getName(), holder.t2, holder.inc, holder.add);

                    tvItems.setText("" +dbHelper.sumOfQuantity()+" items");
                    tv1.setText("₹" +dbHelper.sumOfPrice());

                    cart_tost.setVisibility(LinearLayout.VISIBLE);
                } else {
//                    dbHelper.DeleteData(currentItem.getName().toString());
                    holder.inc.setVisibility(View.GONE);
                    holder.add.setVisibility(View.VISIBLE);
                }
                if(!dbHelper.getAllData().isEmpty()){
                    cart_tost.setVisibility(View.VISIBLE);
                }
            }
        });
        tvItems.setText("" +dbHelper.sumOfQuantity()+" items" );
        tv1.setText("₹" +dbHelper.sumOfPrice());
        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Custom.inc(context,false, currentItem.getName(), holder.t2, holder.inc, holder.add);
                if (dbHelper.sumOfQuantity() == 0) {
                    cart_tost.setVisibility(LinearLayout.GONE);
                    //dbHelper.DeleteData(currentItem.getName().toString());

                } else {
                    //dbHelper.UpdateData(currentItem.getName().toString(), i);
                }


                tvItems.setText("" +dbHelper.sumOfQuantity()+" items");
                tv1.setText("₹" +dbHelper.sumOfPrice());
            }
        });

        holder.t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Custom.inc(context,true, currentItem.getName(), holder.t2, holder.inc, holder.add);

                cart_tost.setVisibility(LinearLayout.VISIBLE);
                //dbHelper.UpdateData(currentItem.getName().toString(), i);

                tvItems.setText("" +dbHelper.sumOfQuantity()+" items");
                tv1.setText("₹" +dbHelper.sumOfPrice());
            }
        });


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void clear() {
        itemList.clear();
        notifyDataSetChanged();
    }

    public void add(ItemModel item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    // Helper method to convert List<String> to a formatted string
    private String listToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : list) {
            stringBuilder.append(item).append(", ");
        }

        // Remove the trailing comma and space
        if (stringBuilder.length() > 2) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }

        return stringBuilder.toString();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        TextView bestsellerText;
        TextView itemNameText;
        TextView ingredientText;
        TextView priceText;
        Button addButton;
        RelativeLayout relativeLayout;

        MaterialButton add;

        LinearLayout inc, cart_tost;

        TextView  t2;
        MaterialButton t1,t3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_image);
            bestsellerText = itemView.findViewById(R.id.bestseller_text);
            itemNameText = itemView.findViewById(R.id.item_name_text);
            ingredientText = itemView.findViewById(R.id.ingredient_text);
            priceText = itemView.findViewById(R.id.price_text);
//            addButton = itemView.findViewById(R.id.add_button);
            relativeLayout = itemView.findViewById(R.id.RelativeLayout);


            add = itemView.findViewById(R.id.add);
            inc = itemView.findViewById(R.id.inc);
            t1 = itemView.findViewById(R.id.t1);
            t2 = itemView.findViewById(R.id.t2);
            t3 = itemView.findViewById(R.id.t3);

        }

    }
}
