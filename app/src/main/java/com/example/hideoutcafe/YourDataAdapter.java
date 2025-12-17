package com.example.hideoutcafe;
import static com.example.hideoutcafe.MainActivity.cart_tost;
import static com.example.hideoutcafe.MainActivity.tv1;
import static com.example.hideoutcafe.MainActivity.tv2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class YourDataAdapter extends RecyclerView.Adapter<YourDataAdapter.ViewHolder> {

    private List<YourDataModel> dataList;
    private LayoutInflater inflater;
    private Context context;

    public YourDataAdapter() {
    }

    public YourDataAdapter(Context context, List<YourDataModel> dataList) {
        this.inflater = LayoutInflater.from(context);
        this.dataList = dataList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_in_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        YourDataModel data = dataList.get(position);

        // Bind data to the ViewHolder
        holder.nameTextView.setText(""+data.getName());
        holder.priceTextView.setText(""+data.getPrice());
        holder.t2.setText(""+data.getQuantity());
        holder.ingredients.setText(""+data.getIngredients());
        Glide.with(context).load(data.getImage()).into(holder.cartImage);
        DBHelper dbHelper = new DBHelper(context);



        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Custom.inc(context,false, data.getName(), holder.t2);
                if (i <= 0) {
                    cart_tost.setVisibility(LinearLayout.GONE);
                    dbHelper.DeleteData(data.getName().toString());
//                    holder.cart.fetchDataFromSQLite();

                } else {
                    dbHelper.UpdateData(data.getName().toString(), i);
                }

                tv2.setText("" +dbHelper.sumOfQuantity()+" items");
                tv1.setText("₹" +dbHelper.sumOfPrice());
            }
        });

        holder.t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = Custom.inc(context,true, data.getName(), holder.t2);

                cart_tost.setVisibility(LinearLayout.VISIBLE);
                dbHelper.UpdateData(data.getName().toString(), i);

                tv2.setText("" +dbHelper.sumOfQuantity()+" items");
                tv1.setText("₹" +dbHelper.sumOfPrice());
            }
        });




        // Add bindings for other fields as needed
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView priceTextView , t2, ingredients;
        EditText customizeEditText;
        ImageView cartImage;
        MaterialButton t1,t3;
        Cart cart;

        // Add other TextViews or views for other fields

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.textView1);
            priceTextView = itemView.findViewById(R.id.textView3);
            t2 = itemView.findViewById(R.id.t2);
            ingredients = itemView.findViewById(R.id.textView2);
            customizeEditText = itemView.findViewById(R.id.customizeEditText);
            t1 = itemView.findViewById(R.id.t1);
            t3 = itemView.findViewById(R.id.t3);
            cartImage = itemView.findViewById(R.id.cartImage);
            cart = new Cart();
//            tv1 = MainActivity.tv1;
//            tv2 = MainActivity.tv2;
            // Initialize other TextViews or views for other fields
        }
    }
}