package com.example.hideoutcafe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cart extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Cart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cart.
     */
    // TODO: Rename and change types and number of parameters
    public static Cart newInstance(String param1, String param2) {
        Cart fragment = new Cart();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Fetch data from Firebase
        fetchDataFromSQLite();


    }

DBHelper dbHelper ;
    public void fetchDataFromSQLite() {
        RecyclerView recyclerView;
        ImageView emptyCart= getView().findViewById(R.id.emptyCart);
        recyclerView = getView().findViewById(R.id.Cart1);
        dbHelper = new DBHelper(getContext());
        List<YourDataModel> dataList = dbHelper.getAllData();
        if(dataList.isEmpty()){
            emptyCart.setVisibility(View.VISIBLE);
        }else {
            emptyCart.setVisibility(View.INVISIBLE);
        }
        YourDataAdapter adapter = new YourDataAdapter(requireContext(), dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);




//        String from[] = {"name","price","quantity","ingredients"};
//        int to[] = {R.id.textView1,R.id.textView3,R.id.t2,R.id.textView2};
        //SimpleCursorAdapter ca = new SimpleCursorAdapter(requireContext(), R.layout.item_in_cart, cv,from,to);
        //listView.setAdapter(ca);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}