package com.flightbooking.activies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.adapters.FlightBookingAdapter;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.BookingsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightBookingActivity extends AppCompatActivity {
    List<BookingsPojo> bookingsPojos;
    ListView list_view;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_flights);

        getSupportActionBar().setTitle("Flight Booking");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view = (ListView) findViewById(R.id.list_view);

        bookingsPojos = new ArrayList<>();
        getBookings();
    }
    public void getBookings(){
        progressDialog = new ProgressDialog(FlightBookingActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<BookingsPojo>> call = service.getbooking();
        call.enqueue(new Callback<List<BookingsPojo>>() {
            @Override
            public void onResponse(Call<List<BookingsPojo>> call, Response<List<BookingsPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(FlightBookingActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }
                else if (response.body().size() == 0){
                    Toast.makeText(FlightBookingActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }
                else {
                    bookingsPojos= response.body();
                    list_view.setAdapter(new FlightBookingAdapter(bookingsPojos, FlightBookingActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<BookingsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(FlightBookingActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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