package com.example.hideoutcafe;

import static com.example.hideoutcafe.MainActivity.*;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
LinearLayout linearLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView name = view.findViewById(R.id.username);
        TextView mobilen = view.findViewById(R.id.mobile);
        TextView edit = view.findViewById(R.id.edit);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                name.setText(username);
                mobilen.setText(mobile);
            }
        }, 2000);


       edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            startActivity(new Intent(getContext(),ProfileActivity.class).putExtra("username",username).putExtra("mobile",mobile));
           }
       });



        TextView call = view.findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ContextCompat.checkSelfPermission(getContext(),android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) getContext(), new
                            String[]{android.Manifest.permission.CALL_PHONE}, 0);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9313973847"));
                    startActivity(intent);
                }

            }
        });

        TextView derections = view.findViewById(R.id.derections);
        derections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://maps.app.goo.gl/Enr2zva7Jayva13AA"));
                startActivity(intent);

            }
        });

        LinearLayout instagram = view.findViewById(R.id.instagram);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/vivek____.001?igsh=eTR1N2lpcXhtMGw1&utm_source=ig_contact_invite"));
                startActivity(intent);

            }
        });

        LinearLayout facebook = view.findViewById(R.id.facebook);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/vivek___ramesh_bhai?igsh=eTR1N2lpcXhtMGw1"));
                startActivity(intent);

            }
        });

        LinearLayout share = view.findViewById(R.id.shareThisApp);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(Intent.ACTION_VIEW,
//                        Uri.parse("https://play.google.com/store/apps/details?id=com.whazappstatus.whasappstatus"));
//                startActivity(intent);

                try {
                    Intent appStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.whazappstatus.whasappstatus"));
                    appStoreIntent.setPackage("com.whazappstatus.whasappstatus");

                    startActivity(appStoreIntent);
                } catch (android.content.ActivityNotFoundException exception) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whazappstatus.whasappstatus")));
                }
            }
        });

        LinearLayout rate = view.findViewById(R.id.rateUs);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
// The intent does not have a URI, so declare the "text/plain" MIME type
                //emailIntent.setType(HTTP.PLAIN_TEXT_TYPE);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"ranavayarb@gmail.com"}); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Email subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message text");
                emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"));
                startActivity(emailIntent);

            }
        });

        LinearLayout faq = view.findViewById(R.id.faq);
        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/vivek___ramesh_bhai?igsh=eTR1N2lpcXhtMGw1"));
                startActivity(intent);

            }
        });

        LinearLayout refund = view.findViewById(R.id.refund);
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.instagram.com/vivek___ramesh_bhai?igsh=eTR1N2lpcXhtMGw1"));
                startActivity(intent);

            }
        });












        LinearLayout manageOrder = view.findViewById(R.id.manageOrder);
        manageOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),OrderHistory.class));
            }
        });






    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}