package com.example.goflight;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView customerImage,bookingImage,hotelImage,routeImage;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customerImage = findViewById(R.id.customerimage);
        bookingImage = findViewById(R.id.bookingimage);
        hotelImage = findViewById(R.id.hotelimage);
        routeImage = findViewById(R.id.routeimage);
        customerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,admin_CustomerList.class);
                startActivity(intent);

            }
        });
        bookingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,BookingList.class);
                startActivity(intent);

            }
        });
        hotelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,admin_HotelList.class);
                startActivity(intent);

            }
        });
        routeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this,admin_route_list.class);
                startActivity(intent);

            }
        });

    }
}