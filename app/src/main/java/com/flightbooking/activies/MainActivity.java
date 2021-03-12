package com.flightbooking.activies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(Utils.SHREF, Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("isAdmin",Boolean.FALSE)){
            Toast.makeText(this, "Admin Logged in Already", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, AdminDashboardActivity.class));
            finish();
        }else if(sharedPreferences.getBoolean("isCustomer",Boolean.FALSE)){
            Toast.makeText(this, "Customer Logged in Already", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, CustomerDasboardActivity.class));
            finish();
        }else {
         //   Toast.makeText(this, "Need to Log in", Toast.LENGTH_SHORT).show();
        }
        Button btn_guest=(Button)findViewById(R.id.btn_guest);
        btn_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GuestDashboardActivity.class));
                finish();
            }
        });
        Button btn_cust_login=(Button)findViewById(R.id.btn_cust_login);
        btn_cust_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomerLoginActivity.class));
                finish();
            }
        });

        Button btn_admin_login=(Button)findViewById(R.id.btn_admin_login);
        btn_admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminLoginActivity.class));
                finish();
            }
        });
    }
}