package com.flightbooking.activies;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.flightbooking.R;
import com.flightbooking.fragments.HomeFragment;
import com.flightbooking.fragments.HotelFragment;
import com.flightbooking.fragments.LogoutFragment;
import com.flightbooking.fragments.MyProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CustomerDasboardActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dasboard);

   /*     getSupportActionBar().setTitle("Customer Dashboard");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        bottomNavigation();

        /*TextView myprofile=(TextView)findViewById(R.id.myprofile);
        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDasboardActivity.this, MyProfileActivity.class));

            }
        });

        TextView availableflights=(TextView)findViewById(R.id.availableflights);
        availableflights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDasboardActivity.this, AvailableFlightsActivity.class));

            }
        });
        TextView vacationpackages=(TextView)findViewById(R.id.vacationpackages);
        vacationpackages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CustomerDasboardActivity.this, VacationPackageActivity.class));

            }
        });*/
    }
    private void bottomNavigation() {
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.home:
                                selectedFragment = HomeFragment.homeFragment();
                                break;

                            case R.id.profile:
                                selectedFragment = MyProfileFragment.profileFragment();
                                break;

                            case R.id.hotels:
                                selectedFragment = HotelFragment.hotelFragment();
                                break;
                            case R.id.booking:
                                selectedFragment = HotelFragment.hotelFragment();
                                break;

                            case R.id.logout:
                                selectedFragment = LogoutFragment.logoutFragment();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.homeFragment());
        transaction.commit();
    }

}