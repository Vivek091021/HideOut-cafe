package com.example.hideoutcafe;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Custom {
    public static int inc(Context context, boolean x,String name, TextView t2, LinearLayout inc, MaterialButton add) {
        DBHelper dbHelper;
        dbHelper = new DBHelper(context);
        int y = Integer.parseInt(t2.getText().toString());
        if (x) {
            y++;
            dbHelper.UpdateData(name,y);
            Toast.makeText(context, "hii"+String.valueOf(dbHelper.getQuantityByName(name)), Toast.LENGTH_SHORT).show();
            t2.setText(String.valueOf(dbHelper.getQuantityByName(name)));
        } else {
            y--;
            if (y == 0) {
                dbHelper.DeleteData(name);
                inc.setVisibility(View.GONE);
                add.setVisibility(View.VISIBLE);
                t2.setText(String.valueOf(y));
            } else {
                dbHelper.UpdateData(name,y);
                t2.setText(""+dbHelper.getQuantityByName(name));
            }
        }

return y;
    }
    public static int inc(Context context, boolean x,String name, TextView t2) {
        DBHelper dbHelper;
        dbHelper = new DBHelper(context);
        int y = Integer.parseInt(t2.getText().toString());
        if (x) {
            y++;
            dbHelper.UpdateData(name,y);
            t2.setText(String.valueOf(y));
        } else {
            y--;
            if (y == 0) {
                dbHelper.DeleteData(name);
                t2.setText(String.valueOf(y));
            } else {
                dbHelper.UpdateData(name,y);
                t2.setText(String.valueOf(y));
            }
        }

        return y;
    }

    public void updateNameByMobile(String mobileNumber, String newName) {
        DatabaseReference usersReference = FirebaseDatabase.getInstance().getReference().child("users");

        // Locate the user node with the specified mobile number
        Query query = usersReference.orderByChild("mobile").equalTo(mobileNumber);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    // Update the name for each matching user
                    userSnapshot.getRef().child("name").setValue(newName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Log.e("DBHelper", "updateNameByMobile onCancelled: " + databaseError.getMessage());
            }
        });
    }

}
