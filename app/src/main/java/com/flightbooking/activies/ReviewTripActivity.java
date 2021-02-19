package com.flightbooking.activies;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;

public class ReviewTripActivity extends AppCompatActivity {
    TextView tvSourceDest,tvTimings,tvDay,tvAirways,tvPrice,tvCabineconomy,tvCancle,tvAdults,tvChildrens;
    Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_trip);

        getSupportActionBar().setTitle("Booking Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvSourceDest=(TextView)findViewById(R.id.tvSourceDest);
        tvTimings=(TextView)findViewById(R.id.tvTimings);
        tvDay=(TextView)findViewById(R.id.tvDay);
        tvAirways=(TextView)findViewById(R.id.tvAirways);
        tvPrice=(TextView)findViewById(R.id.tvPrice);
        tvCabineconomy=(TextView)findViewById(R.id.tvCabineconomy);
        tvCancle=(TextView)findViewById(R.id.tvCancle);
        tvAdults=(TextView)findViewById(R.id.tvAdults);
        tvChildrens=(TextView)findViewById(R.id.tvChildrens);

        tvSourceDest.setText(getIntent().getStringExtra("sourcedest"));
        tvTimings.setText(getIntent().getStringExtra("timings"));
        tvDay.setText(getIntent().getStringExtra("day"));
        tvAirways.setText(getIntent().getStringExtra("airways"));
        tvPrice.setText(getIntent().getStringExtra("price")+"$");
        tvCabineconomy.setText("Economy Type:  "+getIntent().getStringExtra("classtype"));
        tvAdults.setText("Adult:  "+getIntent().getStringExtra("adult"));
        tvChildrens.setText("Children:  "+getIntent().getStringExtra("children"));

        btnCheckout=(Button)findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReviewTripActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}