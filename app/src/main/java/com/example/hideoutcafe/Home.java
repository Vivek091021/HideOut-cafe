package com.example.hideoutcafe;

import static com.example.hideoutcafe.MainActivity.mobile;
import static com.example.hideoutcafe.MainActivity.username;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView listView;
    private static ItemAdapter itemAdapter;
    private DatabaseReference databaseReference;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView menuRecyclerView1,itemRecyclerView;
    ArrayList<MenuModel> recyclerDataArrayList;
    TextView welcome_text;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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

    final int duration = 10;
    final int pixelsToMove = 30;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                welcome_text = view.findViewById(R.id.welcome_text);
                welcome_text.setText("Welcome "+username);
            }
        }, 3000);


        recyclerView = view.findViewById(R.id.recycler);
        ArrayList<String> arrayList = new ArrayList<>();

        //Add multiple images to arraylist.
        arrayList.add("https://i.postimg.cc/xCHfdKxs/IMG-20240205-WA0001.jpg");
        arrayList.add("https://i.postimg.cc/3wsVMswX/Hide-Out-Cafe-1.png");
        arrayList.add("https://i.postimg.cc/QM3dXyV3/Hide-Out-Cafe.png");
        arrayList.add("https://i.postimg.cc/MZ7Z2mjH/IMG-20240205-WA0004.jpg");
        arrayList.add("https://i.postimg.cc/rFg1R3vq/Hide-Out-Cafe-2.png");
        arrayList.add("https://i.postimg.cc/Xqknfn0d/IMG-20240205-WA0006.jpg");
        arrayList.add("https://i.postimg.cc/PqHdGV58/Hide-Out-Cafe-3.png");


        ImageAdapter adapter = new ImageAdapter(getContext(), arrayList);


        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {
            @Override
            public void onClick(ImageView imageView, String url) {
                startActivity(new Intent(getContext() , ImageViewActivity.class).putExtra("image",url), ActivityOptions.makeSceneTransitionAnimation(getActivity(),imageView,"image").toBundle());
            }
        });
        recyclerView.setAdapter(adapter);
        // or  (ImageView) view.findViewById(R.id.foo);



        //Explore Menu GridView
//        recyclerView = view.findViewById(R.id.menu_grid);
//        ViewCompat.setNestedScrollingEnabled(view, true);
//        ArrayList<MenuModel> MenuModelArrayList = new ArrayList<MenuModel>();
//
//        MenuModelArrayList.add(new MenuModel("DSA", R.drawable.ic_home));
//        MenuModelArrayList.add(new MenuModel("JAVA", R.drawable.ic_home));
//        MenuModelArrayList.add(new MenuModel("C++", R.drawable.ic_home));
//        MenuModelArrayList.add(new MenuModel("Python", R.drawable.ic_home));
//        MenuModelArrayList.add(new MenuModel("Javascript", R.drawable.ic_home));
//        MenuModelArrayList.add(new MenuModel("DSB", R.drawable.ic_home));
//
//        MenuGVAdapter adapter1 = new MenuGVAdapter(this, MenuModelArrayList);
//        recyclerView.setAdapter(adapter1);


//categories menu




        //Item best seller
          itemRecyclerView = view.findViewById(R.id.item);
//        ArrayList<ItemModel> recyclerDataArrayList1;
//        recyclerDataArrayList1=new ArrayList<>();
//
//        DatabaseReference databaseReference;
//        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("products").child("tea");
//
//        // Read data from Firebase
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {
//                    Product product = productSnapshot.getValue(Product.class);
//                    if (product != null) {
//                        // Handle each product
//                        Log.d("Product", product.getName() + ", Price: " + product.getPrice());
//                        recyclerDataArrayList1.add(new ItemModel(product.getName(), R.drawable.ic_home));
//                        Toast.makeText(getContext(), "P : "+product.getName(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//                // added data from arraylist to adapter class.
//                ItemAdapter adapter3=new ItemAdapter(recyclerDataArrayList1, getContext().getApplicationContext());
//
//                // setting grid layout manager to implement grid view.
//                // in this method '2' represents number of columns to be displayed in grid view.
//                GridLayoutManager layoutManager1=new GridLayoutManager(getContext().getApplicationContext(),1);
//
//                // at last set adapter to recycler view.
//                itemRecyclerView.setLayoutManager(layoutManager1);
//                itemRecyclerView.setNestedScrollingEnabled(false);
//                itemRecyclerView.setAdapter(adapter3);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.e("Firebase", "Error reading data", databaseError.toException());
//                Toast.makeText(getContext(), "error in fetching", Toast.LENGTH_SHORT).show();
//            }
//        });



        // Initialize Firebase Database
        FirebaseApp.initializeApp(requireContext());



        // Fetch Catogary from Firebase
        menuRecyclerView1 = view.findViewById(R.id.menu_grid);

        recyclerDataArrayList=new ArrayList<>();

        fetchCategoryFromFirebase();
        // added data from arraylist to adapter class.







        // Reference to your Firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference("product").child("tea");

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.item);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize adapter with an empty ArrayList
        itemAdapter = new ItemAdapter(requireContext(), new ArrayList<>());
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(false);
        recyclerView.setAdapter(itemAdapter);




        // Fetch data from Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("products").child("Hot Coffee");
        fetchDataFromFirebase(databaseReference);







        // added data to array list
//        recyclerDataArrayList.add(new MenuModel("DSA",R.drawable.ic_home));
//        recyclerDataArrayList.add(new MenuModel("JAVA",R.drawable.ic_home));
//        recyclerDataArrayList.add(new MenuModel("C++",R.drawable.ic_home));
//        recyclerDataArrayList.add(new MenuModel("Python",R.drawable.ic_home));
//
//        recyclerDataArrayList.add(new MenuModel("Node Js",R.drawable.ic_home));






        // added data to array list
//        recyclerDataArrayList1.add(new ItemModel("DSA",R.drawable.ic_home));
//        recyclerDataArrayList1.add(new ItemModel("JAVA",R.drawable.ic_home));
//        recyclerDataArrayList1.add(new ItemModel("C++",R.drawable.ic_home));
//        recyclerDataArrayList1.add(new ItemModel("Python",R.drawable.ic_home));
//        recyclerDataArrayList1.add(new ItemModel("Node Js",R.drawable.ic_home));
//        recyclerDataArrayList1.add(new ItemModel("Node Js",R.drawable.ic_home));



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
                        //String item = snapshot.getValue(String);

                        // Add the item to the adapter
                        if (snapshot != null) {

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
    public void showBottomSheet(Bundle bundle) {
        ItemDetailsBottomSheetFragment bottomSheetFragment = new ItemDetailsBottomSheetFragment();
        // Set arguments
        bottomSheetFragment.setArguments(bundle);
        bottomSheetFragment.show(getChildFragmentManager(), bottomSheetFragment.getTag());
    }
}