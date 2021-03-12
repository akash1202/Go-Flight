package com.flightbooking.activies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.HotelInfoPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelInfoActivity extends AppCompatActivity {
    List<HotelInfoPojo> hotelInfo;
    ListView list_view;
    ProgressDialog progressDialog;
    Button btnAddHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_info);

        getSupportActionBar().setTitle("Hotel Information");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAddHotel = (Button) findViewById(R.id.btnAddHotel);
        btnAddHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HotelInfoActivity.this, AddHotelActivity.class);
                startActivity(intent);
            }
        });

        list_view = (ListView) findViewById(R.id.list_view);

        hotelInfo = new ArrayList<>();
        getHotelInfo();

    }

    public void getHotelInfo() {
        progressDialog = new ProgressDialog(HotelInfoActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<HotelInfoPojo>> call = service.gethotel();
        call.enqueue(new Callback<List<HotelInfoPojo>>() {
            @Override
            public void onResponse(Call<List<HotelInfoPojo>> call, Response<List<HotelInfoPojo>> response) {
                progressDialog.dismiss();
                if (response.body() == null) {
                    Toast.makeText(HotelInfoActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                } else {
                    hotelInfo = response.body();
                    list_view.setAdapter(new HotelInfoAdapter(hotelInfo, HotelInfoActivity.this));

                }
            }

            @Override
            public void onFailure(Call<List<HotelInfoPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(HotelInfoActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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