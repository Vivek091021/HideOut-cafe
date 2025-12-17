package com.example.hideoutcafe;

//import static com.developer.kalert.KAlertDialog.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


//import com.developer.kalert.KAlertDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener, NavigationView.OnNavigationItemSelectedListener, LocationListener{


    Button logout;
    TextView user;
    FirebaseAuth mAuth;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    TextView txt_location, change;
    BottomNavigationView bottomNavigationView;

    static LinearLayout cart_tost;
    static TextView tv1,tv2;
    MaterialButton btn;

//location

    LocationManager locationManager;
    Menu menu = new Menu();
    Profile profile = new Profile();
    Cart cart = new Cart();
    Home home = new Home();
    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction transaction;
    static Fragment activeFragment;
    static Fragment fg;
    FirebaseDatabase database;
    DatabaseReference myRef;
    public static String username;
    public static String mobile;

    ProgressDialog d;

    MaterialButton btnViewCart,btnPayment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fg = menu;

        transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.flFragment, home, "Home");
        transaction.add(R.id.flFragment, cart, "Cart").hide(cart);
        transaction.add(R.id.flFragment, menu, "Menu").hide(menu);
        transaction.add(R.id.flFragment, profile, "Profile").hide(profile);
        transaction.commit();
        activeFragment = home;

        cart_tost = findViewById(R.id.cart_tost);
        tv1 = findViewById(R.id.tvPrice);
        tv2 = findViewById(R.id.tvItems);
        btnViewCart= findViewById(R.id.viewCart);
        btnPayment= findViewById(R.id.btnPayment);
        DBHelper dbHelper = new DBHelper(getApplicationContext());


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        mobile = currentUser.getPhoneNumber().substring(3);
        myRef = database.getReference("users").child(mobile);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User value = dataSnapshot.getValue(User.class);
                username = value.getName();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




        btnViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().hide(activeFragment).show(cart).commit();
                bottomNavigationView.setSelectedItemId(R.id.cart);
                cart.fetchDataFromSQLite();
                btnViewCart.setVisibility(MaterialButton.GONE);
                btnPayment.setVisibility(View.VISIBLE);

                activeFragment =  cart;
            }
        });
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,PaymentActivity.class));
                finish();
//                String upi_url = "upi://pay?pa=address@okhdfcbank&pn=Payee Name&tn=Payment Message&cu=INR";
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(upi_url));
//                MainActivity.this.onActivityResult(intent);
                // pa : Payee address, usually found in GooglePay app profile page
                // pn : Payee name
                // tn : Txn note, basically your message for the payee
                // cu : Currency
            }
        });

        //exit confirmition

//        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
//            @Override
//            public void handleOnBackPressed() {
//                new MaterialAlertDialogBuilder(MainActivity.this,R.style.RoundShapeTheme)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle("Closing Activity")
//                        .setMessage("Are you sure you want to close this activity?")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
////                                mAuth.signOut();
////                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
////                                startActivity(intent);
////                                MainActivity.this.finish();
////                                System.exit(0);
//
//
//
//
//                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                                startActivity(intent);
//                                MainActivity.this.finish();
//                                System.exit(0);
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.dismiss();
//                            }
//                        })
//                        .show();
//
//
//            }
//        });


// location Update
        d = new ProgressDialog(this);
        txt_location = findViewById(R.id.title_text);
        change = findViewById(R.id.textView27);


        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.setTitle("Location");
                d.setMessage("Getting Location....");
                d.show();
//                new MaterialAlertDialogBuilder(MainActivity.this,R.style.RoundShapeTheme)
//                        .setIcon(R.drawable.btn_done)
//                        .setTitle("Location")
//                        .setMessage("Finding Location, Please Wait")
//                        .setPositiveButton("Ok", null)
//                        .show();
//                KAlertDialog pDialog = new KAlertDialog(MainActivity.this);
//                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//                pDialog.setTitleText("Loading");
//                pDialog.setCancelable(false);
//                pDialog.show();
                getLocation();
            }
        });


//bottom navigation bar

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);


        // drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Set the toolbar as the ActionBar

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(this::onOptionsItemSelected);


        if (currentUser != null) {
            // user.setText(currentUser.getEmail());
        }
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FirebaseAuth.getInstance().signOut();
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, MainActivity.this, null);
            Toast.makeText(this, "in get Location", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            d.setTitle(e.getMessage());
            d.dismiss();
        }
    }


    public void onLocationChanged(Location location) {

        try {
            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                String city = address.getLocality(); // Get the city name
                String addressLine = address.getAddressLine(0); // Full address
                d.dismiss();
                txt_location.setText(addressLine);
                Toast.makeText(this, "City: " + city + "\nLatitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
            } else {
                txt_location.setText("Address not available");
                d.setTitle("Address not available");
                d.dismiss();
                Toast.makeText(this, "Address not available", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            d.setTitle(e.getMessage());
            d.dismiss();
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

        } finally {
            // Remove updates after receiving the first location update
            locationManager.removeUpdates(MainActivity.this);
        }

    }
    @Override
    public void onProviderEnabled(String provider) {

    }

    // bottom navigation bar


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();



        if (itemId == R.id.menu) {
            fragmentManager.beginTransaction().hide(activeFragment).show(menu).commit();
            btnViewCart.setVisibility(View.VISIBLE);
            btnPayment.setVisibility(View.GONE);
            activeFragment =  menu;

            return true;
        } else if (itemId == R.id.profile) {
//            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
//            startActivity(intent);
//            fragmentManager
//                    .beginTransaction()
//                    .setReorderingAllowed(true)
//                    .replace(R.id.flFragment, profile)
//                    .commit();
            btnViewCart.setVisibility(View.VISIBLE);
            btnPayment.setVisibility(MaterialButton.GONE);
            fragmentManager.beginTransaction().hide(activeFragment).show(profile).commit();
            activeFragment = profile;

            return true;
        } else if (itemId == R.id.cart) {
            fragmentManager.beginTransaction().hide(activeFragment).show(cart).commit();
            cart.fetchDataFromSQLite();
            btnPayment.setVisibility(MaterialButton.VISIBLE);
            btnViewCart.setVisibility(MaterialButton.GONE);
            activeFragment =  cart;
//getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,cart,"Cart").commit();

            return true;
        } else if (itemId == R.id.home) {
            btnViewCart.setVisibility(MaterialButton.VISIBLE);
            btnPayment.setVisibility(MaterialButton.GONE);
            fragmentManager.beginTransaction().hide(activeFragment).show(home).commit();
            activeFragment = home;

            return true;
        }

        return false;
    }


    // TODO: 12/21/2023 Drawer click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();
        if (id == R.id.nav_user_profile) {
            fragmentManager.beginTransaction().hide(activeFragment).show(profile).commit();
            activeFragment = profile;
            drawerLayout.closeDrawers();
            return true;
        } else if (id == R.id.nav_logout) {
            new MaterialAlertDialogBuilder(MainActivity.this,R.style.RoundShapeTheme)
                    .setIcon(R.drawable.ic_logout)
                    .setTitle("Logout")
                    .setMessage("Do you really want to Logout")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

//                            SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPref.edit();
//                            editor.putString(getString(R.string.device_id),"abc");
//                            editor.apply();
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();


        }
        return super.onOptionsItemSelected(item);
    }
}