package com.flightbooking.activies;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.flightbooking.R;
import com.flightbooking.adapters.AvailableFlightsAdapter;
import com.flightbooking.api.ApiService;
import com.flightbooking.api.RetroClient;
import com.flightbooking.model.AvailableFlightsPojo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvailableFlightsActivity extends AppCompatActivity {
    List<AvailableFlightsPojo> availableFlightsPojo;
    ListView list_view;
    ProgressDialog progressDialog;
    String adult,children,classtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_flights);

        getSupportActionBar().setTitle("Available Flights");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        list_view = (ListView) findViewById(R.id.list_view);

        adult=getIntent().getStringExtra("adult");
        children=getIntent().getStringExtra("children");
        classtype=getIntent().getStringExtra("classtype");

        availableFlightsPojo = new ArrayList<>();
        getRouteInfo();
    }
    public void getRouteInfo(){
        progressDialog = new ProgressDialog(AvailableFlightsActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        ApiService service = RetroClient.getRetrofitInstance().create(ApiService.class);
        Call<List<AvailableFlightsPojo>> call = service.getAvailableFlightRoutes(getIntent().getStringExtra("source"),
                getIntent().getStringExtra("destination"),getIntent().getStringExtra("type"));
        call.enqueue(new Callback<List<AvailableFlightsPojo>>() {
            @Override
            public void onResponse(Call<List<AvailableFlightsPojo>> call, Response<List<AvailableFlightsPojo>> response) {
                progressDialog.dismiss();
                if(response.body()==null){
                    Toast.makeText(AvailableFlightsActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }
                else if (response.body().size() == 0){
                    Toast.makeText(AvailableFlightsActivity.this,"No data found",Toast.LENGTH_SHORT).show();
                }
                else {
                    availableFlightsPojo= response.body();
                    list_view.setAdapter(new AvailableFlightsAdapter(availableFlightsPojo,adult,children,classtype, AvailableFlightsActivity.this));
                }
            }
            @Override
            public void onFailure(Call<List<AvailableFlightsPojo>> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("Search flights(error):",t.getMessage());
                Toast.makeText(AvailableFlightsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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