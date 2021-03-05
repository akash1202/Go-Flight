package com.flightbooking.activies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_guest=(Button)findViewById(R.id.btn_guest);
        btn_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GuestDashboardActivity.class));


            }
        });
        Button btn_cust_login=(Button)findViewById(R.id.btn_cust_login);
        btn_cust_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomerLoginActivity.class));


            }
        });

        Button btn_admin_login=(Button)findViewById(R.id.btn_admin_login);
        btn_admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdminLoginActivity.class));


            }
        });
    }
}