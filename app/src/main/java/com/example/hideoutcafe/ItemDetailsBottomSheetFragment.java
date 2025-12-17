package com.example.hideoutcafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ItemDetailsBottomSheetFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet, container, false);
        // Set peek height

        getDialog().setOnShowListener(dialog -> {
            BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) dialog;
            FrameLayout bottomSheet = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
            view.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.bottom_sheet_background));
            Bundle bundle = getArguments();
            if (bottomSheet != null) {
                if (bundle != null) {
                    // Retrieve individual data fields
                    String image = bundle.getString("image");
                    String name = bundle.getString("name");
                    String price = bundle.getString("price");
                    String popularity = bundle.getString("popularity");
                    String ingredients = bundle.getString("ingredients");

                    ImageView imageView = view.findViewById(R.id.imageViewItem);


                    TextView textViewName = view.findViewById(R.id.textViewItemName);
                    textViewName.setText(name);

                    TextView textViewPrice = view.findViewById(R.id.textViewItemPrice);
                    textViewPrice.setText(price);

                    TextView textViewPopularity = view.findViewById(R.id.textViewItemPopularity);
                    textViewPopularity.setText(popularity);

                    TextView textViewIngredients = view.findViewById(R.id.textViewItemIngredients);
                    textViewIngredients.setText(ingredients);


                    Glide.with(getContext()).load(getArguments().getString("image")).into(imageView);
                }
                BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);
                // Set the desired peek height here (e.g., 50% of screen height)
                behavior.setPeekHeight((int) (getResources().getDisplayMetrics().heightPixels * 0.5));
            }
        });
        return view;
    }
}
