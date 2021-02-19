package com.flightbooking.activies;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.flightbooking.R;

public class VacationDetailsActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tvname,tvCityName,tvPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacation_details);


        getSupportActionBar().setTitle("Hotel Details");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        imageView=(ImageView)findViewById(R.id.imageView);

        Glide.with(VacationDetailsActivity.this).load(getIntent().getStringExtra("photo")).into(imageView);

        tvname=(TextView)findViewById(R.id.tvname);
        tvCityName=(TextView)findViewById(R.id.tvCityName);
        tvPrice=(TextView)findViewById(R.id.tvPrice);
        tvname.setText(getIntent().getStringExtra("hotelname"));
        tvCityName.setText(getIntent().getStringExtra("city"));
        tvPrice.setText(getIntent().getStringExtra("price")+"$");

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