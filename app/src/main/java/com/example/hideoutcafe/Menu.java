package com.example.hideoutcafe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Menu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Menu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseReference databaseReference;
    static ItemAdapter itemAdapter;

    public Menu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Menu.
     */
    // TODO: Rename and change types and number of parameters
    public static Menu newInstance(String param1, String param2) {
        Menu fragment = new Menu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);



        // Inflate the layout for this fragment
        return view;
    }
    RecyclerView menuRecyclerView1,itemRecyclerView;
    ArrayList<MenuModel> recyclerDataArrayList;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        //categories menu
        FirebaseApp.initializeApp(requireContext());



        // Fetch Catogary from Firebase
        menuRecyclerView1 = view.findViewById(R.id.menu_grid);

        recyclerDataArrayList=new ArrayList<>();

        fetchCategoryFromFirebase();





        FirebaseApp.initializeApp(requireContext());


        // Reference to your Firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference("product").child("Tea");

        // Initialize RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.item);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize adapter with an empty ArrayList
        itemAdapter = new ItemAdapter(requireContext(), new ArrayList<>());
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
//        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(itemAdapter);



        // Fetch data from Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("products").child("Tea");
        fetchDataFromFirebase(databaseReference);

    }
    public static void fetchDataFromFirebase(DatabaseReference databaseReference) {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if the adapter is not null
                if (itemAdapter != null) {
                    // Clear the existing data in the adapter
                    itemAdapter.clear();

                    // Iterate through the Firebase data
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Create an ItemModel object from the Firebase data
                        ItemModel item = snapshot.getValue(ItemModel.class);

                        // Add the item to the adapter
                        if (item != null) {
                            itemAdapter.add(item);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
                //Toast.makeText(requireContext(), "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fetchCategoryFromFirebase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("category");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if the adapter is not null
                //if (itemAdapter != null) {
                // Clear the existing data in the adapter
                //itemAdapter.clear();

                // Iterate through the Firebase data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Create an ItemModel object from the Firebase data
                    String item = snapshot.getKey();

                    // Add the item to the adapter
                    if (item != null) {
                        recyclerDataArrayList.add(new MenuModel(snapshot.getKey(),snapshot.getValue().toString()));
//                            Toast.makeText(getContext(), "category"+item, Toast.LENGTH_SHORT).show();
//                            itemAdapter.add(item);
                    }
                }
                MenuGVAdapter adapter2=new MenuGVAdapter(recyclerDataArrayList, getContext().getApplicationContext());

                // setting grid layout manager to implement grid view.
                // in this method '2' represents number of columns to be displayed in grid view.
                GridLayoutManager layoutManager=new GridLayoutManager(getContext().getApplicationContext(),3);

                // at last set adapter to recycler view.
                menuRecyclerView1.setLayoutManager(layoutManager);
                menuRecyclerView1.setNestedScrollingEnabled(false);
                menuRecyclerView1.setAdapter(adapter2);
                //}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
                Toast.makeText(requireContext(), "Failed to load data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}