package com.example.hideoutcafe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    static String tableName = "Cart";
    static String databaseName ="TheistCafe";
    private DatabaseReference firebaseDatabaseReference;
    public DBHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
        firebaseDatabaseReference = FirebaseDatabase.getInstance().getReference("cart_data");
        usersReference = FirebaseDatabase.getInstance().getReference("users");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+tableName+"(_id INTEGER PRIMARY KEY AUTOINCREMENT ,name TEXT,price TEXT,quantity INTEGER,ingredients TEXT,image TEXT,date TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void InsertData(String name,String price,int quantity,String ingredients,String image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("price",price);
        cv.put("quantity",quantity);
        cv.put("ingredients",ingredients);
        cv.put("image",image);
        db.insert(tableName,null,cv);
    }
    public void UpdateData(String name,int quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("quantity",quantity);
        db.update(tableName,cv,"name = ?",new String[]{name});
    }
    public void DeleteData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName,"name = ?",new String[]{name});
    }
    public Cursor SelectData(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM "+tableName+" WHERE name='"+name+"'",null);

    }

    public List<YourDataModel> getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();


        List<YourDataModel> dataList = new ArrayList<>();
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Extract data from the cursor
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String price = cursor.getString(cursor.getColumnIndex("price"));
                int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                String ingredients = cursor.getString(cursor.getColumnIndex("ingredients"));
                String image = cursor.getString(cursor.getColumnIndex("image"));
                String date = cursor.getString(cursor.getColumnIndex("date"));

                // Create a YourDataModel object and add it to the list
                YourDataModel data = new YourDataModel(id, name, price, quantity, ingredients, image, date);
                dataList.add(data);

            } while (cursor.moveToNext());

            // Close the cursor
            cursor.close();
        }

        return dataList;

        //return db.rawQuery("SELECT * FROM "+tableName+" ",null);

    }

    public Cursor getDataById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+tableName+" WHERE _id = ?";
        return db.rawQuery(query, new String[]{id});
    }
    public int getQuantityByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        int quantity = 0;
        String query = "SELECT quantity FROM " + tableName + " WHERE name = ?";
        Cursor cursor = db.rawQuery(query, new String[]{name});
        if (cursor != null && cursor.moveToFirst()) {
            quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
            cursor.close();
        }
        return quantity;
    }
    public int sumOfQuantity() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM(quantity) FROM " + tableName;
        Cursor cursor = db.rawQuery(query, null);

        int sum = 0;
        if (cursor != null && cursor.moveToFirst()) {
            sum = cursor.getInt(0);
            cursor.close();
        }

        return sum;
    }

    public double sumOfPrice() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT SUM(price * quantity) FROM " + tableName;
        Cursor cursor = db.rawQuery(query, null);

        double sum = 0;
        if (cursor != null && cursor.moveToFirst()) {
            sum = cursor.getDouble(0);
            cursor.close();
        }

        return sum;
    }
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tableName, null, null);
        db.close();
    }
    private DatabaseReference usersReference;
    public void addToFirebase(String userId) {

        List<YourDataModel> dataList = getAllData();

        for (YourDataModel data : dataList) {

            // Assuming you have a 'CartData' class that matches the structure of your database entries
            CartData cartData = new CartData(data.getName(), data.getPrice(), data.getQuantity(),CartData.getCurrentDateTime());
            DatabaseReference userCartReference = usersReference.child(userId).child("cart");
            userCartReference.push().setValue(cartData);
            // Push the data to Firebase

        }
    }
    public void getAllData(String userId, CartDataValueEventListener valueEventListener) {
        DatabaseReference userCartReference = usersReference.child(userId).child("cart");

        // Retrieve all cart data for the specific user
        userCartReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<CartData> cartDataList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Extract cart item data
                    String itemId = snapshot.getKey();
                    String name = snapshot.child("name").getValue(String.class);
                    String price = snapshot.child("price").getValue(String.class);
                    int quantity = snapshot.child("quantity").getValue(Integer.class);
                    String dateTime = snapshot.child("dateTime").getValue(String.class);

                    // Create CartData object
                    CartData cartData = new CartData(name, price, quantity, dateTime);
                    cartDataList.add(cartData);
                }

                // Pass the list of cart data to the listener
                valueEventListener.onDataLoaded(cartDataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                valueEventListener.onCancelled(databaseError);
            }
        });
    }
    public boolean isEmpty(String tableName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + tableName, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }
}
