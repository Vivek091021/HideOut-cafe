package com.example.hideoutcafe;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

import com.github.leandroborgesferreira.loadingbutton.customViews.CircularProgressButton;

public class ButtonAnimation {
    public void stopAnimation(Activity act, CircularProgressButton btn, boolean isSuccessful) {

        int resourceId;
        int fillColor;
        Bitmap bitmap;
        Resources resources = act.getResources(); // Get a reference to your app's resources


        if (!isSuccessful) {

            resourceId = resources.getIdentifier("btn_warning", "drawable", act.getPackageName()); // Assuming "my_image" is the name of your image
            bitmap = BitmapFactory.decodeResource(act.getResources(), resourceId);
            fillColor = Color.RED;

        } else {

            resourceId = resources.getIdentifier("btn_done", "drawable", act.getPackageName()); // Assuming "my_image" is the name of your image
            bitmap = BitmapFactory.decodeResource(act.getResources(), resourceId);
            fillColor = Color.parseColor("#32359d");

        }

        btn.doneLoadingAnimation(fillColor, bitmap);

    }
}
