package com.example.hideoutcafe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
        public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView subjectImageView;
        public TextView subjectTextView;
        public TextView numOfLikesTextView;

        // Constructor - accepts entire row item
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Find each view by id you set up in the list_item.xml
            subjectImageView = itemView.findViewById(R.id.item);
            subjectTextView = itemView.findViewById(R.id.item);
            numOfLikesTextView = itemView.findViewById(R.id.item);
        }
    }
    ArrayList<Menu> list;

    // Constructor
    public CategoryAdapter(ArrayList<Menu> list){
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout
        View contactView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }

    // Assigning respective data for the views based on the position of the current item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the Subject based on the current position
        Menu currentItem = list.get(position);

        // Setting views with the corresponding data
        ImageView imageView = holder.subjectImageView;
        //imageView.setImageResource("currentItem.getImageId()");

        TextView subjectTextView = holder.subjectTextView;
        subjectTextView.setText("currentItem.getSubject()");

        TextView likesTextView = holder.numOfLikesTextView;
        likesTextView.setText("currentItem.getSubject()");
    }

    // Indicating how long your data is
    @Override
    public int getItemCount() {
        return list.size();
    }
}
