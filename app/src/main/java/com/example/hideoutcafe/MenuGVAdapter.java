package com.example.hideoutcafe;
import static com.example.hideoutcafe.MainActivity.fg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MenuGVAdapter extends RecyclerView.Adapter<MenuGVAdapter.RecyclerViewHolder> {

    private ArrayList<MenuModel> courseDataArrayList;
    private Context mcontext;

    public MenuGVAdapter(ArrayList<MenuModel> MenuModelArrayList, Context mcontext) {
        this.courseDataArrayList = MenuModelArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate Layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview and imageview.
        MenuModel MenuModel = courseDataArrayList.get(position);
        holder.courseTV.setText(MenuModel.getTitle());

        Glide.with(mcontext).load(MenuModel.getCategoryImage()).into(holder.courseIV);


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(MainActivity.activeFragment == fg) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products").child(MenuModel.getTitle());
                    Menu.fetchDataFromFirebase(databaseReference);
                }else {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("products").child(MenuModel.getTitle());
                    Home.fetchDataFromFirebase(databaseReference);


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return courseDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView courseTV;
        private ImageView courseIV;
        private LinearLayout linearLayout;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            courseTV = itemView.findViewById(R.id.price_text);
            courseIV = itemView.findViewById(R.id.item_image);
            linearLayout = itemView.findViewById(R.id.menuLinearLayout);
        }
    }
}
